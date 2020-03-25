package com.xuqm.base.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

public class PagedDataSource<T> extends PageKeyedDataSource<Integer, T> {
    private PagedDataLoader<T> dataLoader;

    public PagedDataSource(PagedDataLoader<T> dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, T> callback) {
        this.dataLoader.loadInitial(params, callback);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, T> callback) {
        this.dataLoader.loadAfter(params, callback);
    }
}
