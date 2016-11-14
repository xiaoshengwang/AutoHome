package com.example.dllo.autohome.sale;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;

import java.util.List;

/**
 * Created by dllo on 16/11/12.
 */
public class FoundProduceItemRVAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private List<SaleBean.ResultBean.CardlistBean.DataBean> databean;

    public void setDatas(List<SaleBean.ResultBean.CardlistBean.DataBean> datas) {
        this.databean = datas;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_found_produce_rv_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_found_produce_iv, databean.get(position).getLogo());
        holder.setText(R.id.item_found_produce_title, databean.get(position).getTitle());
        holder.setText(R.id.item_found_produce_adinfo, databean.get(position).getAdinfo());
        holder.setText(R.id.item_found_produce_price, databean.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return databean == null ? 0 : databean.size();
    }



}
