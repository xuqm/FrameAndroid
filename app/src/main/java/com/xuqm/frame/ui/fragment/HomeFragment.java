package com.xuqm.frame.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xuqm.base.common.LogHelper;
import com.xuqm.base.ui.BaseFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.databinding.AppFragmentHomeBinding;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.ui.adapter.BannerImageAdapter;
import com.xuqm.frame.viewmodel.HomeViewModel;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<AppFragmentHomeBinding> {

    private ArrayList<AD> list = new ArrayList<>();
    private BannerImageAdapter adapter;

    private HomeViewModel viewModel;
    private HomeListFragment fragment;

    @Override
    protected int getLayoutId() {
        return R.layout.app_fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
//        banner = Objects.requireNonNull(getActivity()).findViewById(R.id.banner);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        fragment = new HomeListFragment(this);

        adapter = new BannerImageAdapter(list);
        getBinding().banner.setAdapter(adapter)
                .setIndicator(new CircleIndicator(mContext))
                .setIndicatorNormalColorRes(R.color.dark_gray)
                .setIndicatorSelectedColorRes(R.color.blue)
                .setOnBannerListener((OnBannerListener<AD>) (data, position) -> LogHelper.e("=====>点击了" + data + position));

        getBinding().baseRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragment.refresh();
                viewModel.initData();
                getBinding().baseRefreshLayout.setRefreshing(false);
            }
        });

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.home_flyt, fragment)
                .commit();


    }

    @Override
    protected void initData() {
        super.initData();


        viewModel.adLiveData.observe(this, getBinding().banner::setDatas);
        viewModel.initData();

    }

    public void refreshFinished() {
        getBinding().baseRefreshLayout.setRefreshing(false);
    }

}
