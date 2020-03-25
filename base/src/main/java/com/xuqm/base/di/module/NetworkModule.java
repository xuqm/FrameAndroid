package com.xuqm.base.di.module;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.GsonBuilder;
import com.socks.library.KLog;
import com.xuqm.base.App;
import com.xuqm.base.di.manager.RetrofitManager;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private String BaseUrl = "https://www.wanandroid.com";///hbdc/s/air/sddc/aqi24hour?cityId=1&monitorIds=1

    public NetworkModule() {
    }

    public NetworkModule(String baseUrl) {
        BaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = RetrofitManager.getRetrofit(BaseUrl);
        if (null == retrofit) retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, PersistentCookieJar persistentCookieJar) {
        return new OkHttpClient.Builder()
                .cookieJar(persistentCookieJar)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> KLog.json("json____", message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    PersistentCookieJar providePersistentCookieJar(SharedPrefsCookiePersistor sharedPrefsCookiePersistor) {
        return new PersistentCookieJar(new SetCookieCache(), sharedPrefsCookiePersistor);
    }

    @Provides
    @Singleton
    SharedPrefsCookiePersistor provideSharedPrefsCookiePersistor() {
        return new SharedPrefsCookiePersistor(App.getInstance());
    }

    @Provides
    @Singleton
    List<Cookie> provideCookies(SharedPrefsCookiePersistor sharedPrefsCookiePersistor) {
        return sharedPrefsCookiePersistor.loadAll();
    }

}
