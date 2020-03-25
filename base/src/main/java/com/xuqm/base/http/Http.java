package com.xuqm.base.http;

import com.xuqm.base.App;

import java.util.HashMap;
import java.util.Map;

public class Http {

    private static Map<String, Object> apis = new HashMap<>();

    public static <T> T getApi(final Class<T> service) {
        if (!apis.containsKey(service.getCanonicalName()))
            apis.put(service.getCanonicalName(), App.getInstance().appComponent.retrofit().create(service));

        return (T) apis.get(service.getCanonicalName());
    }
}
