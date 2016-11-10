package com.example.dllo.autohome.forum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.ForumSeletedBean;
import com.example.dllo.autohome.forum.selected_all.DetailsActivity;

/**
 * Created by dllo on 16/11/5.
 */
public class ForumSeletedLvAdapter extends BaseAdapter{

    Context context;
    ForumSeletedBean forumSeletedBean;

    public void setForumSeletedBean(ForumSeletedBean forumSeletedBean) {
        this.forumSeletedBean = forumSeletedBean;
    }

    public ForumSeletedLvAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return forumSeletedBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return forumSeletedBean.getResult().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_forum_selected);
        viewHolder.setText(R.id.tv_listview_title, forumSeletedBean.getResult().getList().get(i).getTitle())
                .setText(R.id.tv_listview_replycount, forumSeletedBean.getResult().getList().get(i).getBbsname())
                .setText(R.id.tv_listview_date, forumSeletedBean.getResult().getList().get(i).getReplycounts() + "回帖")
                .setImage(R.id.iv_listview_picture, forumSeletedBean.getResult().getList().get(i).getSmallpic());

        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailsActivity.class);
                intent.putExtra("id", forumSeletedBean.getResult().getList().get(i).getTopicid());
                viewGroup.getContext().startActivity(intent);
            }
        });

        return viewHolder.getItemView();
    }
}
