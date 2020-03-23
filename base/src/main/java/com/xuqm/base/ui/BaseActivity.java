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
        if (getLayoutId() == 0) {//没有layout的时候，act自己写布局
            ImmersionBar.with(this)
                    .init();
            setContentView();
        } else if (!showToolbar()) {//没有toolbar但是有状态栏的情况
            bindUI(getLayoutId());
            ImmersionBar.with(this)
                    .titleBar(binding.getRoot()) //指定标题栏view
                    .init();
        } else if (transparentStatusBar()) {//透明状态栏，但是显示状态栏的内容
            bindUI(getLayoutId());
            ImmersionBar.with(this).transparentBar().init();
        } else {//使用base提供的toolbar
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

    /**
     * 如果不提供layoutId{@link #getLayoutId()} 则可以重写这个方法自己布局
     */
    @Override
    public void setContentView() {

    }

    /**
     * 是否需要展示toolbar，默认为true，使用默认布局展示toolbar
     * false的话，可以自定义toolbar
     *
     * @return 是否展示默认toolbar
     */
    @Override
    public boolean showToolbar() {
        return true;
    }

    /**
     * 如果返回true，则状态栏变成透明状态，但是状态栏的文字还显示
     *
     * @return 是否需要设置状态栏为透明状态
     */
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
