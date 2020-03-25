package com.xuqm.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.common.RefreshResult;
import com.xuqm.base.datasource.DataSourceFactory;
import com.xuqm.base.datasource.PagedDataLoader;
import com.xuqm.base.viewmodel.callback.AdapterObserverCallback;
import com.xuqm.base.viewmodel.callback.DataObserverCallback;
import com.xuqm.base.viewmodel.callback.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseListViewModel<T> extends BaseViewModel implements PagedDataLoader<T> {

    public int pageSize() {
        return 10;
    }

    private List<T> data = new ArrayList<>();

    private DataSourceFactory<T> dataSourceFactory = new DataSourceFactory<>(this);
    private LiveData<PagedList<T>> loadLiveData = new LivePagedListBuilder<>(dataSourceFactory, this.pageSize()).build();
    private MutableLiveData<RefreshResult> refreshLiveData = new MutableLiveData<>();
    private MutableLiveData<RefreshResult> loadMoreLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<Integer, Object>> notifyItemLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> removeItemLiveData = new MutableLiveData<>();

    public void invalidate() {
        Objects.requireNonNull(dataSourceFactory.sourceLiveData.getValue()).invalidate();
    }

    public void observeDataObserver(@NonNull LifecycleOwner owner, DataObserverCallback<T> dataObserverCallback) {
        loadLiveData.observe(owner, dataObserverCallback::data);
        refreshLiveData.observe(owner, dataObserverCallback::refreshResult);
        loadMoreLiveData.observe(owner, dataObserverCallback::loadMoreResult);
    }

    public void observeAdapterObserver(@NonNull LifecycleOwner owner, AdapterObserverCallback observerCallback) {
        notifyItemLiveData.observe(owner, integerObjectPair -> observerCallback.notifyItem(integerObjectPair.first, integerObjectPair.second));
        removeItemLiveData.observe(owner, observerCallback::removeItem);
    }

    @Override
    public void loadInitial(PageKeyedDataSource.LoadInitialParams<Integer> params, PageKeyedDataSource.LoadInitialCallback<Integer, T> callback) {
        refresh();
        data.clear();
        loadData(1, onResponse -> {
            if (null == onResponse) {
                refreshLiveData.postValue(RefreshResult.FAILED);
            } else if (onResponse.isEmpty()) {
                refreshLiveData.postValue(RefreshResult.NO_DATA);
            } else if (onResponse.size() < pageSize()) {
                data.addAll(onResponse);
                callback.onResult(onResponse, null, null);
                refreshLiveData.postValue(RefreshResult.NO_MORE);
            } else {
                data.addAll(onResponse);
                callback.onResult(onResponse, null, 2);
                refreshLiveData.postValue(RefreshResult.SUCCEED);
            }

        });
    }

    @Override
    public void loadAfter(PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, T> callback) {
        loadMore();
        loadData(params.key, onResponse -> {
            if (null == onResponse) loadMoreLiveData.postValue(RefreshResult.FAILED);
            else if (onResponse.size() < pageSize()) {
                data.addAll(onResponse);
                callback.onResult(onResponse, null);
                loadMoreLiveData.postValue(RefreshResult.NO_MORE);
            } else {
                data.addAll(onResponse);
                callback.onResult(onResponse, params.key + 1);
                loadMoreLiveData.postValue(RefreshResult.SUCCEED);
            }

        });
    }

    public void notifyItem(int position) {
        notifyItemLiveData.postValue(new Pair<>(position, null));
    }

    public void notifyItem(int position, Object payload) {
        notifyItemLiveData.postValue(new Pair<>(position, payload));
    }

    public void removeItem(int position) {
        removeItemLiveData.postValue(position);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    public abstract void loadData(int page, Response<T> onResponse);

}


