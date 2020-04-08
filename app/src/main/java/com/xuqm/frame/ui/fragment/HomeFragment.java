package com.xuqm.frame.ui.fragment;

import android.view.View;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.common.LogHelper;
import com.xuqm.base.common.ToolsHelper;
import com.xuqm.base.ui.BaseListAppBarFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.model.Article;
import com.xuqm.frame.ui.adapter.BannerImageAdapter;
import com.xuqm.frame.ui.adapter.CommonArticleAdapter;
import com.xuqm.frame.viewmodel.HomeListViewModel;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class HomeFragment extends BaseListAppBarFragment<Article, HomeListViewModel> {

    @Override
    public BasePagedAdapter<Article> adapter() {
        return new CommonArticleAdapter();
    }


    private ArrayList<AD> list = new ArrayList<>();
    private BannerImageAdapter bannerImageAdapter;

    private Banner banner;

    @Override
    protected int getAppBarView() {
        return R.layout.user_item_user_title;
    }

    @Override
    protected void initView() {
        super.initView();
        banner = getActivity().findViewById(R.id.banner);

        bannerImageAdapter = new BannerImageAdapter(list);
        banner.setAdapter(bannerImageAdapter)
                .setIndicator(new CircleIndicator(mContext))
                .setIndicatorNormalColorRes(R.color.dark_gray)
                .setIndicatorSelectedColorRes(R.color.blue)
                .setOnBannerListener((OnBannerListener<AD>) (data, position) -> LogHelper.e("=====>点击了" + data + position));
    }

    @Override
    protected void initData() {
        super.initData();

        getViewModel().adLiveData.observe(this, banner::setDatas);
        getViewModel().get();

    }

    @Override
    public void itemClicked(View view, Article item, int position) {
        ToolsHelper.snack(getBinding().baseRefreshLayout, item.toString());
    }
}
