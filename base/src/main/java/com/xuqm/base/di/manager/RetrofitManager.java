package com.xuqm.base.di.manager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class RetrofitManager {
    private static Map<String, Retrofit> retrofitMap = new HashMap<>();

    public static Retrofit getRetrofit(String url) {
        return retrofitMap.get(url);
    }

    public static void putRetrofit(String url, Retrofit retrofit) {
        retrofitMap.put(url, retrofit);
    }
}
