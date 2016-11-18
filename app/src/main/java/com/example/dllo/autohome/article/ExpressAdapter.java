package com.example.dllo.autohome.article;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.ExpressBean;
import com.example.dllo.autohome.forum.selectedall.DetailsActivity;

/**
 * Created by dllo on 16/11/16.
 */
public class ExpressAdapter extends BaseAdapter{
private ExpressBean bean;
    private String id;


    public void setBean(ExpressBean bean) {
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

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_article_express);
        viewHolder.setText(R.id.express_playcount, bean.getResult().getList().get(i).getReviewcount() + "位观众")
                .setText(R.id.express_title, bean.getResult().getList().get(i).getTitle())
                .setText(R.id.express_date, bean.getResult().getList().get(i).getAdvancetime())
                .setImage(R.id.express_img, bean.getResult().getList().get(i).getBgimage());

        id = bean.getResult().getList().get(i).getId() + "";
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailsActivity.class);
                intent.putExtra("title", "快报");
                intent.putExtra("id", id);
                viewGroup.getContext().startActivity(intent);
            }
        });

        return viewHolder.getItemView();
    }
}
