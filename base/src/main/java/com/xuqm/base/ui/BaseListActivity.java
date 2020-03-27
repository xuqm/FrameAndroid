package com.xuqm.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xuqm.base.R;
import com.xuqm.base.adapter.BaseItem;
import com.xuqm.base.adapter.BasePagedAdapter;
import com.xuqm.base.common.RefreshResult;
import com.xuqm.base.common.ToolsHelper;
import com.xuqm.base.databinding.ActivityBaseListBinding;
import com.xuqm.base.view.enu.Status;
import com.xuqm.base.viewmodel.BaseListViewModel;
import com.xuqm.base.viewmodel.callback.AdapterObserverCallback;
import com.xuqm.base.viewmodel.callback.DataObserverCallback;

import java.lang.reflect.ParameterizedType;

/**
 * 列表页面的activity继承这个，只需要指定一个adapter就可以展示数据了
 * 例子参考：
 * MainActivity extends BaseListActivity<User, MainViewModel>
 *
 * User需要继承{@link BaseItem}
 * MainViewModel 需要继承{@link BaseListViewModel}
 *
 * @param <T>
 * @param <VM>
 */
public abstract class BaseListActivity<T extends BaseItem, VM extends BaseListViewModel<T>>
        extends BaseActivity<ActivityBaseListBinding> {

    private ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    private Class<VM> cal = (Class<VM>) parameterizedType.getActualTypeArguments()[1];
    private VM viewModel;

    private BasePagedAdapter<T> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_list;
    }

    /**
     * 获取到viewModel，可以做其它事情，比如item的增删改查等
     *
     * @return viewModel
     */
    public VM getViewModel() {
        return viewModel;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(cal);
        adapter = adapter();
        adapter.setItemClickListener(this::itemClicked);
        adapter.setItemLongClickListener(this::itemLongClicked);
        getBinding().baseRecyclerView.setAdapter(adapter);
        getBinding().baseRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getBinding().baseRefreshLayout.setOnRefreshListener(() -> viewModel.invalidate());
    }

    @Override
    public void initData() {

        /*
          数据更新更新事件的观察者
         */
        viewModel.observeDataObserver(this, new DataObserverCallback<T>() {
            @Override
            public void data(PagedList<T> data) {
                adapter.submitList(data);//数据加载
            }

            @Override
            public void refreshResult(RefreshResult refreshResult) {
                refreshFinished(refreshResult);//刷新状态处理
            }

            @Override
            public void loadMoreResult(RefreshResult refreshResult) {
                loadMoreFinished(refreshResult);//加载更多的处理
            }
        });
        //数据更新处理观察者
        viewModel.observeAdapterObserver(this, new AdapterObserverCallback() {
            @Override
            public void notifyItem(int position, Object payload) {
                adapter.notifyItemChanged(position, payload);
            }

            @Override
            public void removeItem(int position) {
                adapter.notifyItemRemoved(position);
            }
        });

    }

    /**
     * 如果需要对item的点击事件做处理，直接重写这个方法就可以了
     *
     * @param view     view
     * @param item     item
     * @param position position
     */
    public void itemClicked(View view, T item, int position) {

    }

    /**
     * 如果需要对item的长按事件做处理，直接重写这个方法就可以了
     *
     * @param view     view
     * @param item     item
     * @param position position
     * @return true
     */
    public boolean itemLongClicked(View view, T item, int position) {
        return false;
    }

    private void refreshFinished(RefreshResult result) {
        getBinding().baseRefreshLayout.setRefreshing(false);

        if (result == RefreshResult.SUCCEED)
            getBinding().baseEmptyView.setStatus(Status.DISMISS);
        else if (result == RefreshResult.FAILED)
            getBinding().baseEmptyView.setStatus(Status.LOAD_FAILED);
        else if (result == RefreshResult.NO_DATA)
            getBinding().baseEmptyView.setStatus(Status.NO_DATA);
        else if (result == RefreshResult.NO_MORE) {
            getBinding().baseEmptyView.setStatus(Status.DISMISS);
            ToolsHelper.snack(getBinding().baseEmptyView, "全部加载完成");
        }
    }

    private void loadMoreFinished(RefreshResult result) {
//        if (result == RefreshResult.SUCCEED) {
//        } else if (result == RefreshResult.FAILED) {
//        } else
        if (result == RefreshResult.NO_MORE) {
            ToolsHelper.snack(getBinding().baseEmptyView, "全部加载完成");
        }

    }

    /**
     * 需要指定一个adapter，item只有一种类型的使用{@link com.xuqm.base.adapter.CommonPagedAdapter}
     * 需要指定一个adapter，item有多种类型的使用{@link com.xuqm.base.adapter.BasePagedAdapter}
     *
     * @return 自定义的adapter
     */
    public abstract BasePagedAdapter<T> adapter();
}
