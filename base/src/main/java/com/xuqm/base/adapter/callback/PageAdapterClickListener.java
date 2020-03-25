package com.xuqm.base.adapter.callback;

import android.view.View;

public interface PageAdapterClickListener<T> {
    void onClick(View view, T item, int position);
}
