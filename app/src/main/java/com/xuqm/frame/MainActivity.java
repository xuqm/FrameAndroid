package com.xuqm.frame;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.xuqm.base.App;
import com.xuqm.base.common.LogHelper;
import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.databinding.ActivityMainBinding;
import com.xuqm.frame.repository.Service;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        Service api = App.getInstance().appComponent.retrofit().create(Service.class);

        setTitleText("Main Activity");
        setTextColor(0xffFF4444);
        setIconTintColor(0xffFF4444);
        backBtnPressed(() -> Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show());
        getBinding().hello.setOnClickListener(v -> {
            LogHelper.d(TAG, "Hello World");

            Disposable d = api.getAd()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userHttpResult -> Snackbar.make(getBinding().hello, userHttpResult.toString(), Snackbar.LENGTH_SHORT).show(), throwable -> LogHelper.e("=====", throwable));


        });
    }

    @Override
    public void initData() {

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onDenied(data -> {

                })
                .onGranted(data -> {

                }).start();
    }

}
