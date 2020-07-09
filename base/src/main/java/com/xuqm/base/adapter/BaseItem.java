package com.xuqm.base.adapter;

/**
 * 所有用到{@link com.xuqm.base.ui.BaseListActivity}来做的列表页
 * 数据model都要继承BaseItem
 */
public class BaseItem {
    private int s_id;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
}
