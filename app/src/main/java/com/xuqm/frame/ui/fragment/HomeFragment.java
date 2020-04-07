package com.xuqm.frame.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

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
    private HomeViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.app_fragment_home;
    }

    private ArrayList<AD> list = new ArrayList<>();
    private BannerImageAdapter adapter;

    @Override
    protected void initView() {
        super.initView();

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        adapter = new BannerImageAdapter(list);
        getBinding().banner.setAdapter(adapter)
                .setIndicator(new CircleIndicator(mContext))
                .setIndicatorNormalColorRes(R.color.dark_gray)
                .setIndicatorSelectedColorRes(R.color.blue)
                .setOnBannerListener(new OnBannerListener<AD>() {

                    @Override
                    public void OnBannerClick(AD data, int position) {
                        LogHelper.e("=====>点击了" + data + position);
                    }
                });

    }

    @Override
    protected void initData() {
        super.initData();


        viewModel.adLiveData.observe(this, this::updateBanner);
        viewModel.initData();

    }

    private void updateBanner(ArrayList<AD> ads) {
        getBinding().banner.setDatas(ads);
//        list.clear();
//        list.addAll(ads);
//        adapter.notifyDataSetChanged();
    }

}
