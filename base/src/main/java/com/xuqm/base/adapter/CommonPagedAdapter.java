package com.xuqm.base.adapter;

public abstract class CommonPagedAdapter<T extends BaseItem> extends BasePagedAdapter<T> {


    protected CommonPagedAdapter(final int layoutId) {
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
                CommonPagedAdapter.this.convert(holder, t, position);
            }
        });
    }

    public abstract void convert(ViewHolder holder, T item, int position);

}
