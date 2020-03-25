package com.xuqm.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.xuqm.base.adapter.callback.AdapterItemClickListener;
import com.xuqm.base.adapter.callback.AdapterItemLongClickListener;

import java.util.List;

public class BasePagedAdapter<T extends BaseItem> extends PagedListAdapter<T, ViewHolder> {
    private Context context;
    private AdapterItemClickListener<T> itemClickListener;
    private AdapterItemLongClickListener<T> itemLongClickListener;
    private ItemViewDelegateManager<T> mItemViewDelegateManager;

    public BasePagedAdapter() {
        super(new Diff<>());
        mItemViewDelegateManager = new ItemViewDelegateManager<>();
    }


    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(getItem(position), position);
    }


    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public BasePagedAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public BasePagedAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();
        context = parent.getContext();
        return new ViewHolder(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        convert(holder, getItem(position));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (null != itemClickListener) {
            holder.itemView.setOnClickListener(v -> itemClickListener.onClick(holder.itemView, getItem(position), position));
        }if (null != itemLongClickListener) {
            holder.itemView.setOnLongClickListener(v -> itemLongClickListener.onClick(holder.itemView, getItem(position), position));
        }
        bindViewHolder(holder, getItem(position), position, payloads);
    }

    private void bindViewHolder(ViewHolder holder, T item, int position, List<Object> payloads) {
        convert(holder, item);
    }

    public void setItemClickListener(AdapterItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(AdapterItemLongClickListener<T> itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    protected AdapterItemClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    public void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

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
