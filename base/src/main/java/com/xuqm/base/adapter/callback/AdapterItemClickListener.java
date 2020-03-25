package com.xuqm.base.adapter.callback;

import android.view.View;

public interface AdapterItemClickListener<T> {
    void onClick(View view, T item, int position);
}
