package com.example.dllo.autohome.article;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ArticleVPAdapter extends FragmentPagerAdapter{
    SparseArray<Fragment> arrayList;
    private final String[] s;


    public ArticleVPAdapter(FragmentManager fm, String[] strings) {
        super(fm);
        arrayList = new SparseArray<>();
        s = strings;
    }

    @Override
    public Fragment getItem(int position) {
        if (arrayList.get(position) == null) {
            arrayList.put(position, ArticleTopFragment.getInstance(position));
        }
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return s.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return s[position];
    }
}
