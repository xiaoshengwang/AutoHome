package com.example.dllo.autohome.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/14.
 */
public class HistoryAdapter extends BaseAdapter {

    private ArrayList<SearchCarNameBean>beanArrayList;

    public void setBeanArrayList(ArrayList<SearchCarNameBean> beanArrayList) {
        this.beanArrayList = beanArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanArrayList == null?0: beanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.history_item);
        commonViewHolder.setText(R.id.tv_history,beanArrayList.get(i).getName());
        return commonViewHolder.getItemView();
    }
}
