package com.xuqm.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.xuqm.base.adapter.callback.AdapterItemClickListener;
import com.xuqm.base.adapter.callback.AdapterItemLongClickListener;

import java.util.List;

/**
 * 如果用到了{@link com.xuqm.base.ui.BaseListActivity}来展示列表页面的话，需要adapter继承这个
 * <p>
 * 如果item只有一种类型，可以使用{@link CommonPagedAdapter}来展示
 * <p>
 * 如果不用{@link CommonPagedAdapter}的话，继承后需要使用{@link #addItemViewDelegate(ItemViewDelegate)}
 * 来设置展示的页面
 *
 * @param <T>
 */
public class BasePagedAdapter<T extends BaseItem> extends PagedListAdapter<T, ViewHolder> {
    private Context context;
    private AdapterItemClickListener<T> itemClickListener;//item的点击事件
    private AdapterItemLongClickListener<T> itemLongClickListener;//item的长按事件
    private ItemViewDelegateManager<T> mItemViewDelegateManager;//ItemViewDelegate的管理类

    public BasePagedAdapter() {
        super(new Diff<>());
        mItemViewDelegateManager = new ItemViewDelegateManager<>();
    }


    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(getItem(position), position);
    }


    /**
     * 判断是否有多种ItemViewType
     * 根据mItemViewDelegateManager 里面存储的数量决定
     *
     * @return true 有多种ItemViewType
     */
    private boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    /**
     * 添加不同的item样式
     *
     * @param itemViewDelegate 自定义的item
     * @return this
     */
    public BasePagedAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    /**
     * 添加不同的item样式
     *
     * @param viewType         自定义的item type 不能重复
     * @param itemViewDelegate 自定义的item
     * @return this
     */
    public BasePagedAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewDelegate itemViewDelegate = mItemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemViewLayoutId();//这里拿到自定义的layoutId
        context = parent.getContext();//context没用传递过来，这里自己获取到
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
        }
        if (null != itemLongClickListener) {
            holder.itemView.setOnLongClickListener(v -> itemLongClickListener.onClick(holder.itemView, getItem(position), position));
        }
        bindViewHolder(holder, getItem(position), position, payloads);
    }

    private void bindViewHolder(ViewHolder holder, T item, int position, List<Object> payloads) {
        convert(holder, item);
    }

    /**
     * 设置item点击监听
     *
     * @param itemClickListener item的点击事件
     */
    public void setItemClickListener(AdapterItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 设置item长按监听
     *
     * @param itemLongClickListener item的长按事件
     */
    public void setItemLongClickListener(AdapterItemLongClickListener<T> itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    /**
     * 部分情况可以需要用到这个，比如item里面元素想要和item使用同一个回调处理
     * @return
     */
    protected AdapterItemClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    /**
     * ui绘制的事件，分发给ItemViewDelegate自己处理
     * 比如settext()   setOnClickListener()这些
     * @param holder holder
     * @param item item
     */
    public void convert(ViewHolder holder, T item) {
        mItemViewDelegateManager.convert(holder, item, holder.getAdapterPosition());
    }

    /**
     * 刷新知道item
     * @param position position
     */
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
