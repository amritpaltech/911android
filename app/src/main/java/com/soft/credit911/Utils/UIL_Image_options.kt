package com.soft.credit911.Utils


import android.graphics.Bitmap
import android.os.Handler
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer
import com.soft.credit911.R


object UIL_Image_options {


    var options = DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.application_main_icon)
        .showImageForEmptyUri(R.drawable.application_main_icon)
        .showImageOnFail(R.drawable.application_main_icon)
        .resetViewBeforeLoading(true)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(false)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .displayer(SimpleBitmapDisplayer())
        .handler(Handler())
        .build()

    var options2 = DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.application_main_icon)
        .showImageForEmptyUri(R.drawable.application_main_icon)
        .showImageOnFail(R.drawable.application_main_icon)
        .resetViewBeforeLoading(true)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(false)
        .bitmapConfig(Bitmap.Config.RGB_565)
        .displayer(SimpleBitmapDisplayer())
        .handler(Handler())
        .build()
    var profile = DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.application_main_icon)
        .showImageForEmptyUri(R.drawable.application_main_icon)
        .showImageOnFail(R.drawable.application_main_icon)
        .resetViewBeforeLoading(true)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(false)
        .bitmapConfig(Bitmap.Config.ARGB_8888)
        .displayer(SimpleBitmapDisplayer())
        .handler(Handler())
        .build()


}
