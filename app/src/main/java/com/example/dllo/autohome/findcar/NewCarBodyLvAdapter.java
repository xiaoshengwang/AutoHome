package com.example.dllo.autohome.findcar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.FindCarNewCarLvBean;

/**
 * Created by dllo on 16/11/8.
 */
public class NewCarBodyLvAdapter extends BaseAdapter{

    private FindCarNewCarLvBean.ResultBean.BrandlistBean listBean;

    public void setListBean(FindCarNewCarLvBean.ResultBean.BrandlistBean listBean) {
        this.listBean = listBean;
    }

    public NewCarBodyLvAdapter() {

    }

    @Override
    public int getCount() {
        return listBean == null?0:listBean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return listBean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.new_car_body_lv_item);
        commonViewHolder.setImage(R.id.iv_new_car_body, listBean.getList().get(i).getImgurl());
        commonViewHolder.setText(R.id.tv_new_car_body, listBean.getList().get(i).getName());
        return commonViewHolder.getItemView();
    }


}
