package com.xuqm.frame.ui;

import android.os.Bundle;

import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class WelcomeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onDenied(data -> MainActivity.startActivity(mContext))
                .onGranted(data -> {
                    MainActivity.startActivity(mContext);
                    finish();
                }).start();
    }
}
