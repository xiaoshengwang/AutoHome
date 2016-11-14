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
public class FoundFieldItemRVAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    private List<SaleBean.ResultBean.CardlistBean.DataBean> databean;
    public void setDatas(List<SaleBean.ResultBean.CardlistBean.DataBean> datas) {
        this.databean = datas;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_found_field_rv);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_found_field_rv_iv, databean.get(position).getImageurl());
    }

    @Override
    public int getItemCount() {
        return databean == null ? 0 : databean.size();
    }

}
