package com.xuqm.base.adapter;


/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();//这个  ItemViewDelegate  将要展示的页面

    boolean isForViewType(T item, int position); //条件判断，用来判断什么时候展示这个ItemViewDelegate

    void convert(ViewHolder holder, T t, int position);//UI绘制与事件添加

}
