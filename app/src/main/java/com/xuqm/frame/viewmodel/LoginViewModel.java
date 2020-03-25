package com.xuqm.frame.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.di.manager.HttpManager;
import com.xuqm.base.viewmodel.BaseListViewModel;
import com.xuqm.base.viewmodel.callback.Response;
import com.xuqm.frame.model.User;
import com.xuqm.frame.repository.Service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseListViewModel<User> {

    @Override
    public int pageSize() {
        return 20;
    }

    @Override
    public void loadData(int page, Response<User> onResponse) {

        Disposable d = HttpManager.getApi(Service.class)
                .getList(page)
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

    public MutableLiveData<String> liveData = new MutableLiveData<>();

    public void get() {

        Disposable d = HttpManager.getAppComponent("https://wwww.baidu.com")
                .retrofit().create(Service.class)
                .getList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userHttpResult -> {
                    LogHelper.e("=====", userHttpResult);
                }, throwable -> {
                    LogHelper.e("=====", throwable);
                });
        add(d);
    }


}
