package com.xuqm.frame.ui.fragment;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.adapter.CommonPagedAdapter;
import com.xuqm.base.adapter.ViewHolder;
import com.xuqm.base.common.RefreshResult;
import com.xuqm.base.ui.BaseListFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.model.Article;
import com.xuqm.frame.viewmodel.HomeListViewModel;

public class HomeListFragment extends BaseListFragment<Article, HomeListViewModel> {


    private CommonPagedAdapter<Article> adapter = new CommonPagedAdapter<Article>(R.layout.user_item_user) {
        @Override
        protected void convert(ViewHolder holder, Article item, int position) {
            holder.setText(R.id.tvTitle, item.getTitle());
        }
    };
    private HomeFragment homeFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }

    public HomeListFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    protected void initView() {
        super.initView();
        getBinding().baseRefreshLayout.setEnabled(false);
        getBinding().baseRecyclerView.setEnabled(false);
    }

    @Override
    protected void initData() {
        super.initData();
        getLifecycle().addObserver(getViewModel());
    }

    @Override
    public BasePagedAdapter<Article> adapter() {
        return adapter;
    }


    public void refresh() {
        getViewModel().invalidate();
    }

    @Override
    public void refreshFinished(RefreshResult result) {
        super.refreshFinished(result);
//        HomeFragment fragment = (HomeFragment) getParentFragment();
        homeFragment.refreshFinished();
    }
}
