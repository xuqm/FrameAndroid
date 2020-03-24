package com.xuqm.base;

import android.app.Application;

import com.socks.library.KLog;
import com.xuqm.base.di.component.AppComponent;
import com.xuqm.base.di.component.DaggerAppComponent;
import com.xuqm.base.di.module.NetworkModule;

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
        appComponent = DaggerAppComponent.builder().networkModule(new NetworkModule()).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.DEBUG);
    }

}
