package com.xuqm.base.di.manager;

import com.xuqm.base.App;
import com.xuqm.base.di.component.AppComponent;
import com.xuqm.base.di.component.DaggerAppComponent;
import com.xuqm.base.di.module.NetworkModule;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络访问的管理类，
 */
public class HttpManager {

    private static Map<String, Object> apis = new HashMap<>();
    private static Map<String, AppComponent> appComponentMap = new HashMap<>();

    /**
     * 使用默认的appComponent和给定的service获取一个service实例
     * appComponent 在{@link App #appComponent}定义
     * service 可以参照retrofit的使用方法
     * <p>
     * appComponent {@link #getAppComponent(String)}
     *
     * @param service service
     * @param <T>     service实例class类型
     * @return service实例
     */
    public static <T> T getApi(final Class<T> service) {
        return getApi(App.getInstance().appComponent, service);
    }

    /**
     * 根据给定的appComponent和service获取一个service实例
     * appComponent可以使用{@link #getAppComponent(String)} 方法获得
     * service 可以参照retrofit的使用方法
     *
     * @param appComponent {@link #getAppComponent(String)}
     * @param service      service
     * @param <T>          service实例class类型
     * @return service实例
     */
    public static <T> T getApi(AppComponent appComponent, final Class<T> service) {
        String key = appComponent.hashCode() + service.getCanonicalName();
        if (!apis.containsKey(key))
            synchronized (HttpManager.class) {
                if (!apis.containsKey(key))
                    apis.put(key, appComponent.retrofit().create(service));
            }

        return (T) apis.get(key);
    }

    /**
     * 根据指定的baseUrl 获取一个{@link AppComponent}  用来做后续事件
     *
     * @param baseUrl 换地地址  例如
     * @return AppComponent
     */
    public static AppComponent getAppComponent(String baseUrl) {
        if (!appComponentMap.containsKey(baseUrl))
            synchronized (HttpManager.class) {
                if (!appComponentMap.containsKey(baseUrl))
                    appComponentMap.put(baseUrl, DaggerAppComponent.builder().networkModule(new NetworkModule(baseUrl)).build());
            }
        return appComponentMap.get(baseUrl);
    }
}
