package com.xuqm.base;

import android.app.Application;

import com.socks.library.KLog;
import com.xuqm.base.di.component.AppComponent;
import com.xuqm.base.di.manager.HttpManager;

public class App extends Application {

    public AppComponent appComponent;


    private static App instance;

    public static App getInstance() {
        if (null == instance) {
            synchronized (App.class) {
                if (null == instance)
                    instance = new App();
            }
        }
        return instance;
    }

    public App() {
        instance = this;
        appComponent = HttpManager.getAppComponent("https://www.wanandroid.com");
    }
//https://www.wanandroid.com/wxarticle/list/408/1/json
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
    }

}
