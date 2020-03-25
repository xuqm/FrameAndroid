package com.xuqm.base.viewmodel.callback;

import androidx.paging.PagedList;

import com.xuqm.base.common.RefreshResult;

public interface DataObserverCallback<T> {
    void data(PagedList<T> data);

    void refreshResult(RefreshResult refreshResult);

    void loadMoreResult(RefreshResult refreshResult);
}
