package com.xuqm.base.di.manager;

import com.xuqm.base.App;
import com.xuqm.base.di.component.AppComponent;
import com.xuqm.base.di.component.DaggerAppComponent;
import com.xuqm.base.di.module.NetworkModule;

import java.util.HashMap;
import java.util.Map;

public class HttpManager {

    private static Map<String, Object> apis = new HashMap<>();
    private static Map<String, AppComponent> appComponentMap = new HashMap<>();

    public static <T> T getApi(final Class<T> service) {
        return getApi(App.getInstance().appComponent, service);
    }

    public static <T> T getApi(AppComponent appComponent, final Class<T> service) {
        String key = appComponent.hashCode() + service.getCanonicalName();
        if (!apis.containsKey(key))
            synchronized (HttpManager.class) {
                if (!apis.containsKey(key))
                    apis.put(key, appComponent.retrofit().create(service));
            }

        return (T) apis.get(key);
    }

    public static AppComponent getAppComponent(String baseUrl) {
        if (!appComponentMap.containsKey(baseUrl))
            synchronized (HttpManager.class) {
                if (!appComponentMap.containsKey(baseUrl))
                    appComponentMap.put(baseUrl, DaggerAppComponent.builder().networkModule(new NetworkModule(baseUrl)).build());
            }
        return appComponentMap.get(baseUrl);
    }
}
