package com.xuqm.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xuqm.base.adapter.callback.AdapterItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 不用{@link BasePagedAdapter}的时候，可以用这个
 *
 * 这个adapter后续还要改，需要支持viewType的设置
 *
 * @param <T> 数据各式
 */
public abstract class BaseNormalAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private int layoutId;
    private List<T> list;
    private Context context;

    private AdapterItemClickListener<T> listener;

    public BaseNormalAdapter(int layoutId) {
        this.layoutId = layoutId;
        this.list = new ArrayList<>();
    }

    public BaseNormalAdapter(int layoutId, List<T> list) {
        this.layoutId = layoutId;
        this.list = null == list ? new ArrayList<>() : list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (null != listener) listener.onClick(holder.itemView, list.get(position), position);
        convert(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(AdapterItemClickListener<T> listener) {
        this.listener = listener;
    }

    public abstract void convert(ViewHolder holder, T item, int position);

}
