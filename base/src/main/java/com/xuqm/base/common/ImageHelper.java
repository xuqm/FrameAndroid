package com.xuqm.base.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageHelper {
    public static void load(ImageView imageView, Object url) {
        Glide.with(imageView).load(url).into(imageView);
    }
}
