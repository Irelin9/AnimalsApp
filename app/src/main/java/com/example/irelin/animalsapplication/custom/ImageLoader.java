package com.example.irelin.animalsapplication.custom;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {
    public static void load(ImageView view, String url, Context context) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(view);
    }
}
