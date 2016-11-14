package com.example.dllo.autohome.sale;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;

import java.util.List;

/**
 * Created by dllo on 16/11/11.
 */
public class FoundTimeGoGoItemRVAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    private List<SaleBean.ResultBean.CardlistBean.DataBean> dataBean;

    public void setDataBean(List<SaleBean.ResultBean.CardlistBean.DataBean> bean) {
        this.dataBean = bean;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_time_gogo);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_time_gogo_iv, dataBean.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return dataBean == null ? 0 : dataBean.size();
    }

}
