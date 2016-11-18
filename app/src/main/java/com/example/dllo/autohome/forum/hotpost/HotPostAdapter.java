package com.example.dllo.autohome.forum.hotpost;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.HotPostBean;
import com.example.dllo.autohome.forum.selectedall.DetailsActivity;

/**
 * Created by dllo on 16/11/7.
 */
public class HotPostAdapter extends BaseAdapter{
    private HotPostBean bean;

    public void setBean(HotPostBean bean) {
        this.bean = bean;
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
    public View getView(int i, View view, final ViewGroup viewGroup) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_hot_post);
        viewHolder.setText(R.id.hot_post_item_date, bean.getResult().getList().get(i).getPostdate())
                .setText(R.id.hot_post_item_title, bean.getResult().getList().get(i).getTitle())
                .setText(R.id.hot_post_item_bbsname, bean.getResult().getList().get(i).getBbsname())
                .setText(R.id.hot_post_item_replycounts, bean.getResult().getList().get(i).getReplycounts() + "回帖");

        final String id = bean.getResult().getList().get(i).getTopicid() + "";

        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailsActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title", "论坛");

                viewGroup.getContext().startActivity(intent);
            }
        });


        return viewHolder.getItemView();
    }


}
