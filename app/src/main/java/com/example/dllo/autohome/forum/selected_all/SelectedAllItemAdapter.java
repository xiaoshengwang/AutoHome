package com.example.dllo.autohome.forum.selected_all;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SelectedAllItemBean;

/**
 * Created by dllo on 16/11/5.
 */
public class SelectedAllItemAdapter extends BaseAdapter{
    SelectedAllItemBean bean;
    Context context;

    public void setBean(SelectedAllItemBean bean) {
        this.bean = bean;
    }

    public SelectedAllItemAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        final CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_selected_item_activity);
        viewHolder.setText(R.id.selected_item_title_tv, bean.getResult().getList().get(i).getTitle())
                .setText(R.id.selected_item_bbsname_tv, bean.getResult().getList().get(i).getBbsname())
                .setText(R.id.selected_item_replycounts_tv, bean.getResult().getList().get(i).getReplycounts() + "回帖")
                .setImage(R.id.selected_item_iv, bean.getResult().getList().get(i).getSmallpic());

        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailsActivity.class);
                intent.putExtra("id", bean.getResult().getList().get(i).getTopicid());
                viewGroup.getContext().startActivity(intent);
            }
        });

        return viewHolder.getItemView();
    }
}
