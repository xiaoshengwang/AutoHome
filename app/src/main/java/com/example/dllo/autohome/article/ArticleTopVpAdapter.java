package com.example.dllo.autohome.article;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/28.
 */
public class ArticleTopVpAdapter extends PagerAdapter{

    ArrayList<String> arrayList;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        Picasso.with(container.getContext()).load(arrayList.get(position % arrayList.size())).fit().into(imageView);
        container.addView(imageView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);

        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }

    public int getImgCount() {
        return arrayList.size();
    }
}
