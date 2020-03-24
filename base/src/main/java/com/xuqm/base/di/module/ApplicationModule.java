package com.xuqm.base.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
