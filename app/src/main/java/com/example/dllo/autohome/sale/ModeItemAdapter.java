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
public class ModeItemAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private List<SaleBean.ResultBean.CardlistBean.DataBean> dataBeen;

    public void setDataBeen(List<SaleBean.ResultBean.CardlistBean.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_mode_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_mode_img, dataBeen.get(position).getImageurl());
        holder.setText(R.id.item_mode_tv_title, dataBeen.get(position).getTitle());
        holder.setText(R.id.item_mode_tv_adinfo, dataBeen.get(position).getAdinfo());
        holder.setText(R.id.item_mode_tv_price, dataBeen.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }


}
