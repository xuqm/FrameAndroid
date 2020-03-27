package com.xuqm.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xuqm.base.R;
import com.xuqm.base.common.LogHelper;
import com.xuqm.base.view.enu.Status;

/**
 * 自定义的view
 * 展示不同的数据加载状态
 */
public class EmptyView extends FrameLayout {

    private View contentView;
    private Status status = Status.DISMISS;


    public EmptyView(@NonNull Context context) {
        super(context);
        init();
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.empty_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 5) {
            throw new IllegalStateException("EmptyView can only have one child view");
        }
        if (getChildCount() == 5) {
            contentView = getChildAt(4);
        }
        emptyViewLoading = findViewById(R.id.emptyViewLoading);
        emptyViewNoData = findViewById(R.id.emptyViewNoData);
        emptyViewLoadFailed = findViewById(R.id.emptyViewLoadFailed);
        emptyViewNetworkUnavailable = findViewById(R.id.emptyViewNetworkUnavailable);

        setStatus(Status.DISMISS);
    }

    private LinearLayout emptyViewLoading;
    private LinearLayout emptyViewNoData;
    private LinearLayout emptyViewLoadFailed;
    private LinearLayout emptyViewNetworkUnavailable;


    public void setStatus(Status status) {
        LogHelper.e(contentView.getVisibility());
        if (status != this.status) {

            if (status == Status.DISMISS) {
                if (null != contentView) contentView.setVisibility(VISIBLE);
                emptyViewLoading.setVisibility(GONE);
                emptyViewNoData.setVisibility(GONE);
                emptyViewLoadFailed.setVisibility(GONE);
                emptyViewNetworkUnavailable.setVisibility(GONE);
            } else if (status == Status.LOADING) {
                if (null != contentView) contentView.setVisibility(GONE);
                emptyViewLoading.setVisibility(VISIBLE);
                emptyViewNoData.setVisibility(GONE);
                emptyViewLoadFailed.setVisibility(GONE);
                emptyViewNetworkUnavailable.setVisibility(GONE);
            } else if (status == Status.NO_DATA) {
                if (null != contentView) contentView.setVisibility(GONE);
                emptyViewLoading.setVisibility(GONE);
                emptyViewNoData.setVisibility(VISIBLE);
                emptyViewLoadFailed.setVisibility(GONE);
                emptyViewNetworkUnavailable.setVisibility(GONE);
            } else if (status == Status.LOAD_FAILED) {
                if (null != contentView) contentView.setVisibility(GONE);
                emptyViewLoading.setVisibility(GONE);
                emptyViewNoData.setVisibility(GONE);
                emptyViewLoadFailed.setVisibility(VISIBLE);
                emptyViewNetworkUnavailable.setVisibility(GONE);
            } else if (status == Status.NETWORK_UNAVAILABLE) {
                if (null != contentView) contentView.setVisibility(GONE);
                emptyViewLoading.setVisibility(GONE);
                emptyViewNoData.setVisibility(GONE);
                emptyViewLoadFailed.setVisibility(GONE);
                emptyViewNetworkUnavailable.setVisibility(VISIBLE);
            }
            this.status = status;
        }
    }
}
