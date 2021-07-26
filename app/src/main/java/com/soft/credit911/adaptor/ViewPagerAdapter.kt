package com.soft.credit911.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.soft.credit911.R

class ViewPagerAdapter(private val mContext: Context) : PagerAdapter() {
    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        var resId = 0
        when (position) {
            0 -> resId = R.layout.viewpager_login_scrren_1
            1 -> resId = R.layout.viewpager_login_screen_2
            2 -> resId = R.layout.viewpager_login_screen_3
        }
        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(resId, collection, false) as ViewGroup
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}