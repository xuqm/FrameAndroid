package com.xuqm.base.datasource;

import androidx.paging.PageKeyedDataSource;

public interface PagedDataLoader<T> {
    void loadInitial(PageKeyedDataSource.LoadInitialParams<Integer> params, PageKeyedDataSource.LoadInitialCallback<Integer, T> callback);

    void loadAfter(PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, T> callback);

    void refresh();

    void loadMore();
}
