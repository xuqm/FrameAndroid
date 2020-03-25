package com.xuqm.base.common;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ToolsHelper {
    public static void snack(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }
}
