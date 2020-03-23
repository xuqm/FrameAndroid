package com.xuqm.base;

import android.app.Application;

import com.xuqm.base.common.xlog.LogConfiguration;
import com.xuqm.base.common.xlog.LogLevel;
import com.xuqm.base.common.xlog.XLog;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .t()
                .st(0)
                .b()
                .build();
        XLog.init(config);
    }

    public static app getInstance() {
        return AppHolder.INSTANCE;
    }

    private static class AppHolder {
        private static app INSTANCE = new app();
    }
}
