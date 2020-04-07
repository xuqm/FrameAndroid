package com.xuqm.frame.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.xuqm.base.di.manager.HttpManager;
import com.xuqm.base.viewmodel.BaseViewModel;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.repository.Service;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    public MutableLiveData<ArrayList<AD>> adLiveData = new MutableLiveData<>();

    public void initData() {
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
