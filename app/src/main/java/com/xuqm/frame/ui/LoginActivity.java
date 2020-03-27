package com.xuqm.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.ActivityLoginBinding;
import com.xuqm.frame.ui.fragment.LoginFragment;
import com.xuqm.frame.ui.fragment.RegisterFragment;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    public boolean showToolbar() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    @Override
    public void initView(Bundle savedInstanceState) {

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, loginFragment, "login").commit();
    }

    public void goRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.user_fragment_right_in,
                        R.anim.user_fragment_left_out,
                        R.anim.user_fragment_left_in,
                        R.anim.user_fragment_right_out)
                .hide(loginFragment)
                .add(R.id.frame, registerFragment, "login")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void initData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
