package com.example.dllo.autohome.article;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.VideoBean;
import com.example.dllo.autohome.forum.selectedall.DetailsActivity;

/**
 * Created by dllo on 16/11/16.
 */
public class VideoAdapter extends BaseAdapter{

    private String id;

    public void setBean(VideoBean bean) {
        this.bean = bean;
    }

    private VideoBean bean;
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

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_article_video);
        viewHolder.setText(R.id.video_title, bean.getResult().getList().get(i).getTitle())
                .setText(R.id.video_playcount, bean.getResult().getList().get(i).getReplycount() + "播放")
                .setText(R.id.video_replycount, bean.getResult().getList().get(i).getReplycount() + "评论")
                .setText(R.id.video_date, bean.getResult().getList().get(i).getTime())
                .setImage(R.id.video_img, bean.getResult().getList().get(i).getSmallimg());

        id = bean.getResult().getList().get(i).getId() + "";
        viewHolder.setItemClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewGroup.getContext(), DetailsActivity.class);
                intent.putExtra("title", "视频");
                intent.putExtra("id", id);
                viewGroup.getContext().startActivity(intent);
            }
        });

        return viewHolder.getItemView();
    }
}
