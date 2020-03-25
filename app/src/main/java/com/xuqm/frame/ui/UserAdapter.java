package com.xuqm.frame.ui;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.adapter.ItemViewDelegate;
import com.xuqm.base.adapter.ViewHolder;
import com.xuqm.frame.R;
import com.xuqm.frame.model.User;

public class UserAdapter extends BasePagedAdapter<User> {

    public UserAdapter() {
        super();
        addItemViewDelegate(new ItemViewDelegate<User>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.user_item_user_title;
            }

            @Override
            public boolean isForViewType(User item, int position) {
                return position % 2 == 0;
            }

            @Override
            public void convert(ViewHolder holder, User user, int position) {

                holder.setText(R.id.tvTitle, user.getTitle())
                        .setClickListener(R.id.tvTitle, view -> getItemClickListener().onClick(view, user, position));
            }
        });
        addItemViewDelegate(new ItemViewDelegate<User>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.user_item_user;
            }

            @Override
            public boolean isForViewType(User item, int position) {
                return position % 2 != 0;
            }

            @Override
            public void convert(ViewHolder holder, User user, int position) {

                holder.setText(R.id.tvTitle, user.getTitle())
                        .setClickListener(R.id.tvTitle, view -> getItemClickListener().onClick(view, user, position));
            }
        });
    }

}
