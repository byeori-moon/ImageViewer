package com.example.imageviewer

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageLoader {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // 메모리 및 디스크 캐시 사용
            .fitCenter() // 원본 비율 유지
            .into(imageView)
    }
}
