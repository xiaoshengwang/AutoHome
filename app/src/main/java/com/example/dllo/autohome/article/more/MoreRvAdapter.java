package com.example.dllo.autohome.article.more;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.MoreRvEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dllo on 16/11/9.
 */
public class MoreRvAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    ArrayList<String> arrayList;
    private Context context;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_more_recycle, parent, false);
        CommonViewHolder viewHolder = new CommonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.setText(R.id.more_item_tv, arrayList.get(position));

        holder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MoreRvAdapter", "点了" + arrayList.get(position));
                EventBus.getDefault().post(new MoreRvEvent(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    //交换数据类里 指定位置的两条数据
    public void move(int from, int to) {
        Collections.swap(arrayList,from,to);
//        arrayList.add(to, arrayList.remove(from));
        notifyItemMoved(from, to);
    }

}
