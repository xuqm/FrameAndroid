package com.xuqm.frame.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.http.Http;
import com.xuqm.base.viewmodel.BaseListViewModel;
import com.xuqm.base.viewmodel.callback.Response;
import com.xuqm.frame.model.User;
import com.xuqm.frame.repository.Service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseListViewModel<User> {

    @Override
    public void loadData(int page, Response<User> onResponse) {

        Disposable d = Http.getApi(Service.class).getList(page)
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

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        super.onCreate(owner);
        LogHelper.e("================>");
    }


}
