package com.xuqm.base.adapter;

import java.util.List;

/**
 * 这个adapter主要是用来简化通用列表页的绘制
 * 如果item只有一种样式，或者说不需要用到itemViewType，可以直接使用这个
 * <p>
 * 构造函数直接传入对应的layoutId，然后重写convert方法就可以了
 *
 * @param <T> item用到的数据类型
 */
public abstract class CommonAdapter<T extends BaseItem> extends BaseNormalAdapter<T> {


    protected CommonAdapter(final int layoutId) {
        super();
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected CommonAdapter(final int layoutId, List<T> list) {
        super(list);
        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T item, int position);

}
