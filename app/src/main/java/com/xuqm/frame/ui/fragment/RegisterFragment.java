package com.xuqm.frame.ui.fragment;

import com.xuqm.base.ui.BaseFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.AppFragmentRegisterBinding;
import com.xuqm.frame.ui.MainActivity;

public class RegisterFragment extends BaseFragment<AppFragmentRegisterBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.app_fragment_register;
    }

    @Override
    protected void initView() {
        super.initView();
        getBinding().textLogin.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        getBinding().buttonRegister.setOnClickListener(v -> MainActivity.startActivity(mContext));
    }
}
