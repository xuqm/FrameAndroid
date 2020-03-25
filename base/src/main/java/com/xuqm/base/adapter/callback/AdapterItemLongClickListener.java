package com.xuqm.base.adapter.callback;

import android.view.View;

public interface AdapterItemLongClickListener<T> {
    boolean onClick(View view, T item, int position);
}
