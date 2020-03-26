package com.xuqm.frame.ui.fragment;

import com.xuqm.base.ui.BaseFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.AppFragmentRegisterBinding;

public class RegisterFragment extends BaseFragment<AppFragmentRegisterBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_register;
    }

    @Override
    protected void initView() {
        super.initView();
        getBinding().textLogin.setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}
