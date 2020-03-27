package com.xuqm.base.adapter.callback;

import android.view.View;

/**
 * item设置点击事件的监听
 * @param <T>
 */
public interface AdapterItemClickListener<T> {
    void onClick(View view, T item, int position);
}
