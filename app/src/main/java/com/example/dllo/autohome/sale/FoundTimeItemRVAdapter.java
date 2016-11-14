package com.example.dllo.autohome.sale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;

import java.util.List;

/**
 * Created by dllo on 16/11/11.
 */
public class FoundTimeItemRVAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private List<SaleBean.ResultBean.CardlistBean.DataBean> dataBeen;

    public void setDataBeen(List<SaleBean.ResultBean.CardlistBean.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_time_rv);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_time_iv, dataBeen.get(position).getImageurl());
        holder.setText(R.id.item_time_tv_title, dataBeen.get(position).getTitle());
        holder.setText(R.id.item_time_tv_price, dataBeen.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }
}
