package com.xuqm.frame;

import com.xuqm.base.App;
import com.xuqm.base.di.manager.HttpManager;

public class MyApplication extends App {
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = HttpManager.getAppComponent("https://www.wanandroid.com");
    }
}
