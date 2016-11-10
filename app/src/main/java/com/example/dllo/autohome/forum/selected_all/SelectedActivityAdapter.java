package com.example.dllo.autohome.forum.selected_all;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/5.
 */
public class SelectedActivityAdapter extends BaseAdapter{
    ArrayList<String> arrayList;
    Context context;
    private static final String[] url = {"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c104-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c110-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c172-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c230-p1-s30.json"
            , "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c121-p1-s30.json"
            , "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c106-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c118-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c210-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c199-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c198-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c168-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c113-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c109-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c191-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c196-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c107-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c105-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c122-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c194-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c119-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c112-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c120-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c184-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c108-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c124-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c123-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c185-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c186-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c214-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c218-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c223-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c221-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c222-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c224-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c219-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c225-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c226-p1-s30.json"
            ,"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c212-p1-s30.json"};

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public SelectedActivityAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_selected_activity_listview);
        viewHolder.setText(R.id.selected_activity_item_tv, arrayList.get(i));

        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectedAllItemActivity.class);
                intent.putExtra("title", arrayList.get(i));
                intent.putExtra("url", url[i]);
                Log.d("SelectedActivityAdapter", url[i]);
                context.startActivity(intent);
            }
        });

        return viewHolder.getItemView();
    }
}
