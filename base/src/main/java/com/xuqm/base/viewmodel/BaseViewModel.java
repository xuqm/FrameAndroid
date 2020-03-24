package com.xuqm.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import com.xuqm.base.common.LogHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel implements DefaultLifecycleObserver {
    private final String TAG = getClass().getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void add(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        LogHelper.d(TAG, "onDestroy");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogHelper.d(TAG, "onCleared");
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
