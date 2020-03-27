package com.xuqm.base.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 一个image相关的工具类
 */
public class ImageHelper {
    /**
     * 给imageView添加图片的方法
     *
     * @param imageView 需要添加图片的控件
     * @param url       url地址，可以是path  draw等
     */
    public static void load(ImageView imageView, Object url) {
        Glide.with(imageView).load(url).into(imageView);
    }
}
