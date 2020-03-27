package com.xuqm.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {

    private V binding;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), container, false);
        mContext = getActivity();
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @NonNull
    protected V getBinding() {
        return binding;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected void initData() {
    }

    /**
     * 收起键盘
     */
    protected void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }
}
