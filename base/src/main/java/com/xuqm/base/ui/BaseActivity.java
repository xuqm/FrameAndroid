package com.xuqm.base.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.xuqm.base.R;
import com.xuqm.base.common.AppManager;
import com.xuqm.base.databinding.ActivityBaseBinding;
import com.xuqm.base.ui.callback.UiCallback;

public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity implements UiCallback {

    protected String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    private V binding;

    private ActivityBaseBinding baseBinding;

    //setStatusBarDarkFont
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
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (transparentStatusBar()) {//透明状态栏，但是显示状态栏的内容
            bindUI(getLayoutId());
            ImmersionBar.with(this).transparentBar().hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        } else {//使用base提供的toolbar
            baseBinding = DataBindingUtil.setContentView(mContext, R.layout.activity_base);
            ImmersionBar.with(this)
                    .titleBar(baseBinding.baseToolbar) //指定标题栏view
                    .init();

            binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), baseBinding.activityRootView, true);
        }
        if (null != baseBinding) {
            baseBinding.baseToolbar.backBtnPressed(this::backBtnPressed);
        }
        initView(savedInstanceState);
        initData();
    }

    @NonNull
    public V getBinding() {
        return binding;
    }

    /**
     * 导航栏展示的内容
     *
     * @param titleId 标题
     */
    public void setTitleText(@StringRes int titleId) {
        setTitleText(getText(titleId));
    }

    /**
     * 导航栏展示的内容
     *
     * @param title 标题
     */
    public void setTitleText(CharSequence title) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.setTitle(title);
    }

    /**
     * 设置标题颜色
     *
     * @param color 标题颜色
     */
    public void setTextColor(int color) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.setTextColor(color);
    }

    /**
     * 设置返回图标颜色
     *
     * @param iconTintColor 返回图标颜色
     */
    public void setIconTintColor(int iconTintColor) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.setIconTintColor(iconTintColor);
    }

    public void setIconDraw(@DrawableRes int resId) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.getBackBtn().setImageResource(resId);
    }

    /**
     * 是否展示返回按钮
     *
     * @param showBack 是否展示返回按钮
     */
    public void showBack(boolean showBack) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.setShowBack(showBack);
    }

    /**
     * 是否显示导航栏下面的线
     *
     * @param showLine 是否显示导航栏下面的线
     */
    public void showLine(boolean showLine) {
        if (null == baseBinding) return;
        baseBinding.baseToolbar.setShowLine(showLine);
    }


    public void backBtnPressed() {
        finish();
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

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

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

    /**
     * 收起键盘
     */
    protected void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}
