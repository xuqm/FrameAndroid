package com.xuqm.base.adapter;

/**
 * 所有用到{@link com.xuqm.base.ui.BaseListActivity}来做的列表页
 * 数据model都要继承BaseItem
 */
public class BaseItem {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
