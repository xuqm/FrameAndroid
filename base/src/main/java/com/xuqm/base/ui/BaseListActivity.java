package com.xuqm.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xuqm.base.R;
import com.xuqm.base.adapter.BaseItem;
import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.common.RefreshResult;
import com.xuqm.base.common.ToolsHelper;
import com.xuqm.base.databinding.ActivityBaseListBinding;
import com.xuqm.base.view.enu.Status;
import com.xuqm.base.viewmodel.BaseListViewModel;
import com.xuqm.base.viewmodel.callback.AdapterObserverCallback;
import com.xuqm.base.viewmodel.callback.DataObserverCallback;

import java.lang.reflect.ParameterizedType;

public abstract class BaseListActivity<T extends BaseItem, VM extends BaseListViewModel<T>>
        extends BaseActivity<ActivityBaseListBinding> {

    private ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    private Class<VM> cal = (Class<VM>) parameterizedType.getActualTypeArguments()[1];
    private VM viewModel;

    private BasePagedAdapter<T> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(cal);
        adapter = adapter();
        adapter.setItemClickListener(this::itemClicked);
        getBinding().baseRecyclerView.setAdapter(adapter);
        getBinding().baseRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getBinding().baseRefreshLayout.setOnRefreshListener(() -> viewModel.invalidate());
    }

    @Override
    public void initData() {

        viewModel.observeDataObserver(this, new DataObserverCallback<T>() {
            @Override
            public void data(PagedList<T> data) {
                adapter.submitList(data);
            }

            @Override
            public void refreshResult(RefreshResult refreshResult) {
                refreshFinished(refreshResult);
            }

            @Override
            public void loadMoreResult(RefreshResult refreshResult) {
                loadMoreFinished(refreshResult);
            }
        });
        viewModel.observeAdapterObserver(this, new AdapterObserverCallback() {
            @Override
            public void notifyItem(int position, Object payload) {
                adapter.notifyItemChanged(position, payload);
            }

            @Override
            public void removeItem(int position) {
                adapter.notifyItemRemoved(position);
            }
        });

    }

    public void itemClicked(View view, T item, int position) {

    }

    public void refreshFinished(RefreshResult result) {
        getBinding().baseRefreshLayout.setRefreshing(false);

        if (result == RefreshResult.SUCCEED)
            getBinding().baseEmptyView.setStatus(Status.DISMISS);
        else if (result == RefreshResult.FAILED)
            getBinding().baseEmptyView.setStatus(Status.LOAD_FAILED);
        else if (result == RefreshResult.NO_DATA)
            getBinding().baseEmptyView.setStatus(Status.NO_DATA);
        else if (result == RefreshResult.NO_MORE) {
            getBinding().baseEmptyView.setStatus(Status.DISMISS);
            ToolsHelper.snack(getBinding().baseEmptyView, "全部加载完成");
        }
    }

    public void loadMoreFinished(RefreshResult result) {


//        if (result == RefreshResult.SUCCEED) {
//        } else if (result == RefreshResult.FAILED) {
//        } else
        if (result == RefreshResult.NO_MORE) {
            ToolsHelper.snack(getBinding().baseEmptyView, "全部加载完成");
        }

    }

    public abstract BasePagedAdapter<T> adapter();
}
