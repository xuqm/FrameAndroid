package com.xuqm.frame.ui.fragment;

import com.xuqm.base.ui.BaseFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.AppFragmentLoginBinding;
import com.xuqm.frame.ui.LoginActivity;

import java.util.Objects;

public class LoginFragment extends BaseFragment<AppFragmentLoginBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_login;
    }

    @Override
    protected void initView() {
        super.initView();
        getBinding().textRegister.setOnClickListener(v -> ((LoginActivity) Objects.requireNonNull(getActivity())).goRegister());
    }
}
