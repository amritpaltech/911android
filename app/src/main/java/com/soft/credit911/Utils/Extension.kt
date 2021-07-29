package com.soft.credit911.Utils

import android.widget.ImageView
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

fun ImageView.loadImg(url1: String?) {
    val imageLoader = ImageLoader.getInstance()
    val context = this.context
    imageLoader.init(ImageLoaderConfiguration.createDefault(context))
    val url= url1
    imageLoader.displayImage(
        url, this,
        UIL_Image_options.options
    )
}

fun ImageView.loadProfileImage(url: String) {
    val imageLoader = ImageLoader.getInstance()

    imageLoader.displayImage(
        url, this,
        UIL_Image_options.profile
    )
}
