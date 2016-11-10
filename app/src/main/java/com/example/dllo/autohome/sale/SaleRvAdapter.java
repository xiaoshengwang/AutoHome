package com.example.dllo.autohome.sale;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;

/**
 * Created by dllo on 16/11/10.
 */
public class SaleRvAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private SaleBean saleBean;

    public void setSaleBean(SaleBean saleBean) {
        this.saleBean = saleBean;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
//        saleBean.getResult().getCardlist().get()
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
