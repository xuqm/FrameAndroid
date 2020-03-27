package com.xuqm.base.adapter.callback;

import android.view.View;

/**
 * item设置长按事件的监听
 * @param <T>
 */
public interface AdapterItemLongClickListener<T> {
    boolean onClick(View view, T item, int position);
}
