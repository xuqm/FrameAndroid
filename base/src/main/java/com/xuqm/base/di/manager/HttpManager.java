package com.xuqm.base.di.manager;

import com.xuqm.base.App;
import com.xuqm.base.di.component.AppComponent;
import com.xuqm.base.di.component.DaggerAppComponent;
import com.xuqm.base.di.module.NetworkModule;

import java.util.HashMap;
import java.util.Map;

public class HttpManager {

    private static Map<String, Object> apis = new HashMap<>();

    public static <T> T getApi(final Class<T> service) {
        return getApi(App.getInstance().appComponent, service);
    }

    public static <T> T getApi(AppComponent appComponent, final Class<T> service) {
        if (!apis.containsKey(service.getCanonicalName()))
            apis.put(service.getCanonicalName(), appComponent.retrofit().create(service));

        return (T) apis.get(service.getCanonicalName());
    }

    public static AppComponent getAppComponent(String baseUrl) {
        return DaggerAppComponent.builder().networkModule(new NetworkModule(baseUrl)).build();
    }
}
