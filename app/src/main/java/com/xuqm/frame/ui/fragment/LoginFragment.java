package com.xuqm.frame.ui.fragment;

import android.text.TextUtils;

import com.xuqm.base.common.ToolsHelper;
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

        ToolsHelper.addTextChangedListener(getBinding().editTextUsername, getBinding().textInputUsername);
        ToolsHelper.addTextChangedListener(getBinding().editTextPassword, getBinding().textInputPassword);
        getBinding().buttonLogin.setOnClickListener(v -> {

            String username = getBinding().editTextUsername.getText().toString().trim();
            String password = getBinding().editTextPassword.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                ToolsHelper.showError(getBinding().textInputUsername, "用户名不能为空");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                ToolsHelper.showError(getBinding().textInputPassword, "密码不能为空");
                return;
            }
            hideSoftInput();
        });


        getBinding().textRegister.setOnClickListener(v -> ((LoginActivity) Objects.requireNonNull(getActivity())).goRegister());


    }
}
