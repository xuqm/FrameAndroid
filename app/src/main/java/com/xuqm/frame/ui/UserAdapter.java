package com.xuqm.frame.ui;

import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.adapter.ViewHolder;
import com.xuqm.frame.R;
import com.xuqm.frame.model.User;

public class UserAdapter extends BasePagedAdapter<User> {

    public UserAdapter() {
        super(R.layout.user_item_user);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, User item, int position) {

        holder.setText(R.id.tvTitle, item.getTitle())
                .setClickListener(R.id.tvTitle, view -> getItemClickListener().onClick(view, item, position));


    }
}
