package com.xuqm.frame;

import android.os.Bundle;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        findViewById(R.id.hello).setOnClickListener(v -> {
            LogHelper.d(TAG, "Hello World");
        });
    }

    @Override
    public void initData() {

    }

}
