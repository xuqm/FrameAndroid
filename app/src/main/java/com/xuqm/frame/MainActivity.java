package com.xuqm.frame;

import android.os.Bundle;
import android.widget.Toast;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        setTitleText("Main Activity");
        setTextColor(0xffFF4444);
        setIconTintColor(0xffFF4444);
        backBtnPressed(() -> Toast.makeText(mContext, "Hello", Toast.LENGTH_SHORT).show());
        getBinding().hello.setOnClickListener(v -> {
            LogHelper.d(TAG, "Hello World");
        });
    }

    @Override
    public void initData() {

    }

}
