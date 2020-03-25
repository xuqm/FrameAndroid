package com.xuqm.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.xuqm.base.adapter.callback.AdapterClickListener;
import com.xuqm.base.common.ImageHelper;

import java.util.List;

public class ViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ViewGroup parent;
    private int layoutId;

    private SparseArray<View> views = new SparseArray<>();

    public ViewHolder(Context context, ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(context).inflate(layoutId, parent, false));
        this.context = context;
        this.parent = parent;
        this.layoutId = layoutId;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (null == view) {
            view = itemView.findViewById(viewId);
            if (null == view) throw new IllegalArgumentException("not found id");
            views.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(@IdRes int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView textView = getView(viewId);
        textView.setText(context.getString(resId));
        return this;
    }

    public ViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolder setImage(@IdRes int viewId, String url) {
        ImageView imageView = getView(viewId);
        ImageHelper.load(imageView, url);
        return this;
    }

    public ViewHolder gone(@IdRes int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    public ViewHolder gone(View view) {
        view.setVisibility(View.GONE);
        return this;
    }

    public ViewHolder visible(@IdRes int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public ViewHolder visible(View view) {
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public ViewHolder setClickListener(@IdRes int viewId, AdapterClickListener adapterClickListener) {
        View view = getView(viewId);
        if (null != view) view.setOnClickListener(adapterClickListener::onClick);
        return this;
    }

    public ViewHolder setClickListener(List<Integer> viewIds, AdapterClickListener adapterClickListener) {
        for (Integer viewId : viewIds) {
            View view = getView(viewId);
            if (null != view) view.setOnClickListener(adapterClickListener::onClick);
        }
        return this;
    }

}
