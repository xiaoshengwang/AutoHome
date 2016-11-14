package com.example.dllo.autohome.sale;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;
import com.example.dllo.autohome.tools.Point;

import java.util.List;

/**
 * Created by dllo on 16/11/11.
 */
public class BusinessRvAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    private List<SaleBean.ResultBean.CardlistBean.DataBean> bean;

    public void setBean(List<SaleBean.ResultBean.CardlistBean.DataBean> bean) {
        this.bean = bean;
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_business_rv);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_business_rv_img, bean.get(position).getImageurl());
        holder.setText(R.id.item_business_rv_tv, bean.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.size();
    }


}
