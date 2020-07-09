package com.xuqm.base.ui.callback;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

public interface UiCallback {
    @LayoutRes
    int getLayoutId();

    void setContentView();

    void initView(Bundle savedInstanceState);

    void initData();

    int showStatus();

    boolean transparentStatusBar();

    boolean fullscreen();

}
