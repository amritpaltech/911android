package com.soft.credit911.Login.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.soft.credit911.R;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        int resId = 0;
        switch (position) {
            case 0:
                resId = R.layout.viewpager_login_scrren_1;
                break;
            case 1:
                resId = R.layout.viewpager_login_screen_2;

                break;
            case 2:
                resId = R.layout.viewpager_login_screen_3;
                break;
        }
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(resId, collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
