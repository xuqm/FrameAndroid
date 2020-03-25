package com.xuqm.base.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class DataSourceFactory<T> extends DataSource.Factory<Integer, T> {
    private PagedDataLoader<T> dataLoader;

    public DataSourceFactory(PagedDataLoader<T> dataLoader) {
        this.dataLoader = dataLoader;
    }

    public MutableLiveData<PagedDataSource<T>> sourceLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, T> create() {
        PagedDataSource<T> dataSource = new PagedDataSource<>(dataLoader);
        sourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
