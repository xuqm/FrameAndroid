package com.xuqm.base.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.xuqm.base.R;
import com.xuqm.base.common.AppManager;
import com.xuqm.base.databinding.ActivityBaseBinding;
import com.xuqm.base.ui.callback.UiCallback;

public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity implements UiCallback {

    protected String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    private V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().pushActivity(this);
        mContext = this;
        if (getLayoutId() == 0) {
            ImmersionBar.with(this)
                    .init();
            setContentView();
        } else if (!showToolbar()) {
            bindUI(getLayoutId());
            ImmersionBar.with(this)
                    .titleBar(binding.getRoot()) //指定标题栏view
                    .init();
        } else if (transparentStatusBar()) {
            bindUI(getLayoutId());
            ImmersionBar.with(this).transparentBar().init();
        } else {
            ActivityBaseBinding baseBinding = DataBindingUtil.setContentView(mContext, R.layout.activity_base);
            ImmersionBar.with(this)
                    .titleBar(baseBinding.baseToolbar) //指定标题栏view
                    .init();
            binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), baseBinding.activityRootView, true);
        }
        initView(savedInstanceState);
        initData();
    }

    protected void bindUI(@LayoutRes int layoutResID) {
        binding = DataBindingUtil.setContentView(mContext, layoutResID);
    }

    @Override
    public void setContentView() {

    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    public boolean transparentStatusBar() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().popActivity(this);
    }
}
