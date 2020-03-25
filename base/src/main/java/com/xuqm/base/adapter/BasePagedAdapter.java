package com.xuqm.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.xuqm.base.adapter.callback.PageAdapterClickListener;

import java.util.List;

public abstract class BasePagedAdapter<T extends BaseItem> extends PagedListAdapter<T, ViewHolder> {
    private int layoutId;
    private Context context;
    private PageAdapterClickListener<T> itemClickListener;

    public BasePagedAdapter(int layoutId) {
        super(new Diff<>());
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindViewHolder(holder, getItem(position), position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (null != itemClickListener) {
            holder.itemView.setOnClickListener(v -> itemClickListener.onClick(holder.itemView, getItem(position), position));
        }
        bindViewHolder(holder, getItem(position), position, payloads);
    }

    public void bindViewHolder(ViewHolder holder, T item, int position, List<Object> payloads) {
        bindViewHolder(holder, item, position);
    }

    public void setItemClickListener(PageAdapterClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public PageAdapterClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    public abstract void bindViewHolder(ViewHolder holder, T item, int position);

    public void changeItem(int position) {
        if (0 <= position && position < getItemCount()) {
            notifyItemChanged(position);
        }
    }

    public void changeItem(int position, Object payload) {
        if (0 <= position && position < getItemCount()) {
            notifyItemChanged(position, payload);
        }
    }
}
