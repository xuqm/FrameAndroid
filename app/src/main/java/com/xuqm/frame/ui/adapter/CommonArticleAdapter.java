package com.xuqm.frame.ui.adapter;

import com.xuqm.base.adapter.CommonPagedAdapter;
import com.xuqm.base.adapter.ViewHolder;
import com.xuqm.base.common.ToolsHelper;
import com.xuqm.frame.R;
import com.xuqm.frame.model.Article;

public class CommonArticleAdapter extends CommonPagedAdapter<Article> {
    public CommonArticleAdapter() {
        super(R.layout.home_item_list);
    }

    @Override
    protected void convert(ViewHolder holder, Article item, int position) {
        holder.setText(R.id.home_content_title, item.getTitle())
                .setText(R.id.home_content_author, ToolsHelper.isNull(item.getAuthor()) ? "分享人:" + item.getShareUser() : "作者:" + item.getAuthor())
                .setText(R.id.home_content_class, "分类:" + item.getSuperChapterName() + "/" + item.getChapterName())
                .setText(R.id.home_content_time, "时间:" + item.getNiceDate())
                .setImageResource(R.id.home_content_collect, item.isCollect() ? R.drawable.ic_heart_red : R.drawable.ic_heart)
                .gone(R.id.home_header_local)
                .gone(R.id.home_header_new)
                .gone(R.id.home_header_project)
                .gone(R.id.home_header_qa)
                .gone(R.id.home_header_top)
                .gone(R.id.home_header_wchat);

//        holder.setClickListener();

        if (!item.getNiceDate().contains("-")) {
            holder.visible(R.id.home_header_new);
        }
        if ("公众号".equals(item.getSuperChapterName())) {
            holder.visible(R.id.home_header_wchat);
        }
        if ("官方".equals(item.getChapterName())) {
            holder.visible(R.id.home_header_local);
        }
        if ("开源项目主Tab".equals(item.getSuperChapterName())) {
            holder.visible(R.id.home_header_project);
        }
        if ("问答".equals(item.getSuperChapterName())) {
            holder.visible(R.id.home_header_qa);
        }

    }
}
