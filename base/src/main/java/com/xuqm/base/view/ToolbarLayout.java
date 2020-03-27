package com.xuqm.base.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.xuqm.base.R;
import com.xuqm.base.ui.callback.ToolBarListener;

/**
 * 自定义的toolbar
 */
public class ToolbarLayout extends FrameLayout {

    private CharSequence title = "";
    private int textColor = 0x333333;
    private int iconTintColor = 0x333333;
    private boolean showBack = true;
    private boolean showLine = true;

    public void setTitle(CharSequence title) {
        this.title = title;
        getTitleView().setText(title);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        getTitleView().setTextColor(textColor);
    }

    public void setIconTintColor(int iconTintColor) {
        this.iconTintColor = iconTintColor;
        tintIcon(iconTintColor);
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
        getBackBtn().setVisibility(showBack ? VISIBLE : GONE);
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
        findViewById(R.id.toolbarLine).setVisibility(showLine ? VISIBLE : GONE);
    }

    private ToolBarListener backListener;

    public void backBtnPressed(ToolBarListener listener) {
        backListener = listener;
    }

    public ToolbarLayout(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ToolbarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Context mContext = getContext();
        if (null == attrs) return;
        View.inflate(mContext, R.layout.toolbar, this);
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.ToolbarLayout);
        title = typedArray.getString(R.styleable.ToolbarLayout_title);
        showBack = typedArray.getBoolean(R.styleable.ToolbarLayout_showBack, true);
        showLine = typedArray.getBoolean(R.styleable.ToolbarLayout_showLine, true);
        textColor = typedArray.getColor(R.styleable.ToolbarLayout_textColor, ContextCompat.getColor(mContext, R.color.text_black));
        iconTintColor = typedArray.getColor(R.styleable.ToolbarLayout_iconTint, ContextCompat.getColor(mContext, R.color.text_black));
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (null != getBackBtn()) {
            getBackBtn().setVisibility(showBack ? VISIBLE : GONE);
            if (showBack) {
                tintIcon(iconTintColor);
                getBackBtn().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backListener.back();
                    }
                });

            }
        }
        if (null != getTitleView()) {
            getTitleView().setText(title);
            getTitleView().setTextColor(textColor);
        }
        findViewById(R.id.toolbarLine).setVisibility(showLine ? VISIBLE : GONE);
    }

    private void tintIcon(int colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(getBackBtn().getDrawable());
        DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(colors));
        getBackBtn().setImageDrawable(wrappedDrawable);
    }

    private ImageView backBtn;

    private ImageView getBackBtn() {
        if (null == backBtn)
            backBtn = findViewById(R.id.toolbarBack);
        return backBtn;
    }

    private TextView titleView;

    private TextView getTitleView() {
        if (null == titleView)
            titleView = findViewById(R.id.toolbarTitle);
        return titleView;
    }
}
