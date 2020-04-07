package com.xuqm.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.xuqm.base.adapter.FragmentAdapter;
import com.xuqm.base.ui.BaseActivity;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.ActivityMainBinding;
import com.xuqm.frame.ui.fragment.ClassFragment;
import com.xuqm.frame.ui.fragment.HomeFragment;
import com.xuqm.frame.ui.fragment.ProjectFragment;
import com.xuqm.frame.ui.fragment.UserFragment;
import com.xuqm.frame.ui.fragment.WchatFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private List<Fragment> fragments = new ArrayList<>();
    private boolean drawerOpened = false;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public boolean transparentStatusBar() {
        return true;
    }
//    @Override
//    public boolean showToolbar() {
//        return false;
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void backBtnPressed() {
        if (drawerOpened) getBinding().mainDrawerLayout.closeDrawers();
        else getBinding().mainDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        setIconDraw(R.drawable.toolbar_menu);

        initDrawerLayout();
        initLeftNavigation();
        initBottomNavigation();

        fragments.add(new HomeFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new ClassFragment());
        fragments.add(new WchatFragment());
        getBinding().mainViewPager.setUserInputEnabled(false);
        getBinding().mainViewPager.setOffscreenPageLimit(3);
        getBinding().mainViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments));

    }

    private void initBottomNavigation() {
        getBinding().mainBottomNav.setItemIconTintList(null);
        getBinding().mainBottomNav.setOnNavigationItemSelectedListener(item -> {
            if (R.id.main_bottom_home == item.getItemId()) {
                getBinding().mainViewPager.setCurrentItem(0);
            } else if (R.id.main_bottom_app == item.getItemId()) {
                getBinding().mainViewPager.setCurrentItem(1);
            } else if (R.id.main_bottom_class == item.getItemId()) {
                getBinding().mainViewPager.setCurrentItem(2);
            } else getBinding().mainViewPager.setCurrentItem(3);
            return true;
        });
    }

    private void initLeftNavigation() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_left_flyt, new UserFragment())
                .commit();
    }

    private void initDrawerLayout() {

        getBinding().mainDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                drawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerOpened = false;
            }
        });

    }

    @Override
    public void initData() {

    }
}
