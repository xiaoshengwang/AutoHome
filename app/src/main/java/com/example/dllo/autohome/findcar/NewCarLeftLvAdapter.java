package com.example.dllo.autohome.findcar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.FindCarNewCarLvBean;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dllo on 16/11/8.
 */
public class NewCarLeftLvAdapter extends BaseAdapter implements StickyListHeadersAdapter{

    private FindCarNewCarLvBean bean;

    private ListView listView;

    public void setBean(FindCarNewCarLvBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getBrandlist().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getBrandlist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_new_car_bode);

        listView = (ListView) commonViewHolder.getItemView().findViewById(R.id.lv_new_car_body);

        FindCarNewCarLvBean.ResultBean.BrandlistBean listBean  = bean.getResult().getBrandlist().get(i);

        NewCarBodyLvAdapter adapter = new NewCarBodyLvAdapter();
        adapter.setListBean(listBean);
        listView.setAdapter(adapter);
        return commonViewHolder.getItemView();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_new_car_head);
        viewHolder.setText(R.id.item_new_car_list_head_tv, bean.getResult().getBrandlist().get(position).getLetter());
        return viewHolder.getItemView();
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }

}
