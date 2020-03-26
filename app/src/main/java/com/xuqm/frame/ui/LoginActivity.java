package com.xuqm.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.R;

public class LoginActivity extends BaseActivity {
    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
