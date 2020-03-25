package com.xuqm.base.viewmodel.callback;

public interface AdapterObserverCallback {
    void notifyItem(int position, Object payload);

    void removeItem(int position);
}
