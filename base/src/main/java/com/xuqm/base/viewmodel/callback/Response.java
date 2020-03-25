package com.xuqm.base.viewmodel.callback;

import java.util.ArrayList;

public interface Response<T> {
    void onResponse(ArrayList<T> onResponse);
}
