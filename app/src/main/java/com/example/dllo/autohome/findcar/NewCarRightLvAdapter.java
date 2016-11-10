package com.example.dllo.autohome.findcar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.FindCarNewCarLvBean;

/**
 * Created by dllo on 16/11/8.
 */
public class NewCarRightLvAdapter extends BaseAdapter{
    FindCarNewCarLvBean bean;

    @Override
    public int getCount() {
        return bean.getResult().getBrandlist().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getBrandlist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public void setBean(FindCarNewCarLvBean bean) {
        this.bean = bean;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view,viewGroup, R.layout.item_find_car_right_lv);
        commonViewHolder.setText(R.id.new_car_right_tv,bean.getResult().getBrandlist().get(i).getLetter());

        commonViewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(viewGroup.getContext(), bean.getResult().getBrandlist().get(i).getLetter(), Toast.LENGTH_SHORT).show();
            }
        });

        return commonViewHolder.getItemView();
    }

}
