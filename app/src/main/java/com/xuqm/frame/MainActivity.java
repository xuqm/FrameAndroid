package com.xuqm.frame;

import android.os.Bundle;
import android.view.View;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.common.LogHelper;
import com.xuqm.base.common.ToolsHelper;
import com.xuqm.base.ui.BaseListActivity;
import com.xuqm.frame.model.User;
import com.xuqm.frame.ui.UserAdapter;
import com.xuqm.frame.viewmodel.LoginViewModel;

public class MainActivity extends BaseListActivity<User, LoginViewModel> {


    private UserAdapter adapter = new UserAdapter();

    @Override
    public BasePagedAdapter<User> adapter() {
        return adapter;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTitleText("list测试");
        showBack(false);
    }

    @Override
    public void itemClicked(View view, User item, int position) {
        LogHelper.e(view.toString());
        if (R.id.tvTitle == view.getId()) {
            ToolsHelper.snack(view, item.toString());
        }
    }
}
