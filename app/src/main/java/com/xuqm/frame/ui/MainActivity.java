package com.xuqm.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getBinding().mainBottomNav.setItemIconTintList(null);
    }

    @Override
    public void initData() {

    }
}
