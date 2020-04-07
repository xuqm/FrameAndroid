package com.xuqm.frame.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.di.manager.HttpManager;
import com.xuqm.base.viewmodel.BaseListViewModel;
import com.xuqm.base.viewmodel.callback.Response;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.model.Article;
import com.xuqm.frame.repository.Service;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeListViewModel extends BaseListViewModel<Article> {

    @Override
    public int pageSize() {
        return 6;
    }

    @Override
    public void loadData(int page, Response<Article> onResponse) {

        Disposable d = HttpManager.getApi(Service.class)
                .getHome(page-1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userHttpResult -> {
                    onResponse.onResponse(userHttpResult.getData().getDatas());
                }, throwable -> {
                    onResponse.onResponse(null);
                    LogHelper.e("=====", throwable);
                });
        add(d);
    }

    public MutableLiveData<ArrayList<AD>> adLiveData = new MutableLiveData<>();

    public void get() {
        Disposable d = HttpManager.getApi(Service.class)
                .getAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpResult -> {
                    adLiveData.postValue(httpResult.getData());
                });
        add(d);
    }


}
