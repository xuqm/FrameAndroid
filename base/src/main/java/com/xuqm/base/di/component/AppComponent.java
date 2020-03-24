package com.xuqm.base.di.component;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.xuqm.base.di.module.ApplicationModule;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface AppComponent {
    Retrofit retrofit();

    OkHttpClient okHttpClient();

    PersistentCookieJar persistentCookieJar();

    List<Cookie> cookies();
}
