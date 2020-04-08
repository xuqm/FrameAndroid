package com.xuqm.frame.ui.fragment;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.adapter.CommonPagedAdapter;
import com.xuqm.base.adapter.ViewHolder;
import com.xuqm.base.ui.BaseListAppBarFragment;
import com.xuqm.frame.R;
import com.xuqm.frame.model.AD;
import com.xuqm.frame.model.Article;
import com.xuqm.frame.ui.adapter.BannerImageAdapter;
import com.xuqm.frame.viewmodel.HomeListViewModel;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class ProjectFragment extends BaseListAppBarFragment<Article, HomeListViewModel> {
    private CommonPagedAdapter<Article> adapter = new CommonPagedAdapter<Article>(R.layout.user_item_user) {
        @Override
        protected void convert(ViewHolder holder, Article item, int position) {
            holder.setText(R.id.tvTitle, item.getTitle());
        }
    };

    @Override
    public BasePagedAdapter<Article> adapter() {
        return adapter;
    }

    private ArrayList<AD> list = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        list.add(new AD("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"));
        list.add(new AD("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"));
        list.add(new AD("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png"));
        BannerImageAdapter adapter = new BannerImageAdapter(list);
        Banner banner = getActivity().findViewById(R.id.banner);
        banner.setAdapter(adapter);
    }

    @Override
    protected int getAppBarView() {
        return R.layout.user_item_user_title;
    }
}
