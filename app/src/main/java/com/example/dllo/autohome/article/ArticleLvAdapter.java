package com.example.dllo.autohome.article;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.ArticleTopBean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */
public class ArticleLvAdapter extends BaseAdapter{
    Context context;
    private ArticleTopBean articleTopBean;

    static final int TYPE_1 = 1;
    static final int TYPE_2 = 2;
    static final int TYPE_3 = 3;
    static final int TYPE_5 = 5;
    static final int TYPE_6 = 6;
    private CommonViewHolder viewHolder;

    public ArticleLvAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendPageListViewBean(ArticleTopBean articleTopBean) {
        this.articleTopBean = articleTopBean;
    }

    @Override
    public int getCount() {
        return articleTopBean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int position) {
        return articleTopBean.getResult().getNewslist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (articleTopBean.getResult().getNewslist().get(position).getMediatype() == 1) {
            return TYPE_1;
        } else if(articleTopBean.getResult().getNewslist().get(position).getMediatype() == 2)
        {
            return TYPE_2;
        } else if (articleTopBean.getResult().getNewslist().get(position).getMediatype() == 3) {
            return TYPE_3;
        } else if (articleTopBean.getResult().getNewslist().get(position).getMediatype() == 5) {
            return TYPE_5;
        } else if (articleTopBean.getResult().getNewslist().get(position).getMediatype() == 6){
            return TYPE_6;
        }else
            return TYPE_1;

    }

    @Override
    public int getViewTypeCount() {
        return 10;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case TYPE_1:
                viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_article_listview_type_one);
                viewHolder.setText(R.id.tv_listview_title_t1, articleTopBean.getResult().getNewslist().get(position).getTitle())
                        .setText(R.id.tv_listview_replycount_t1, articleTopBean.getResult().getNewslist().get(position).getReplycount() + "评论")
                        .setText(R.id.tv_listview_date_t1, articleTopBean.getResult().getNewslist().get(position).getTime())
                        .setImage(R.id.iv_listview_picture_t1, articleTopBean.getResult().getNewslist().get(position).getSmallpic());
                Log.d("ArticleLvAdapter", "aaaaaaa");

                break;

            case TYPE_2:
                viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_article_listview_type_two);
                viewHolder.setText(R.id.tv_listview_title_t2,articleTopBean.getResult().getNewslist().get(position).getTitle())
                        .setText(R.id.tv_listview_replycount_t2,articleTopBean.getResult().getNewslist().get(position).getReplycount() + "回帖")
                        .setText(R.id.tv_listview_date_t2,articleTopBean.getResult().getNewslist().get(position).getTime())
                        .setImage(R.id.iv_listview_picture_t2,articleTopBean.getResult().getNewslist().get(position).getSmallpic());
                break;
            case TYPE_3:
                viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_article_listview_type_three);
                viewHolder.setText(R.id.tv_listview_title_t3,articleTopBean.getResult().getNewslist().get(position).getTitle())
                        .setText(R.id.tv_listview_replycount_t3,articleTopBean.getResult().getNewslist().get(position).getReplycount() + "播放")
                        .setText(R.id.tv_listview_date_t3,articleTopBean.getResult().getNewslist().get(position).getTime())
                        .setImage(R.id.iv_listview_picture_t3,articleTopBean.getResult().getNewslist().get(position).getSmallpic());
                break;
            case TYPE_5:
                viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_article_listview_type_five);
                viewHolder.setText(R.id.tv_listview_title_t5,articleTopBean.getResult().getNewslist().get(position).getTitle())
                        .setText(R.id.tv_listview_replycount_t5,articleTopBean.getResult().getNewslist().get(position).getReplycount() + "回帖")
                        .setText(R.id.tv_listview_date_t5,articleTopBean.getResult().getNewslist().get(position).getTime())
                        .setImage(R.id.iv_listview_picture_t5,articleTopBean.getResult().getNewslist().get(position).getSmallpic());
                break;
            case TYPE_6:
                viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_articlse_listview_type_six);
                viewHolder.setText(R.id.tv_listview_title_t6,articleTopBean.getResult().getNewslist().get(position).getTitle())
                        .setText(R.id.tv_listview_replycount_t6,articleTopBean.getResult().getNewslist().get(position).getReplycount() + "评论")
                        .setText(R.id.tv_listview_date_t6,articleTopBean.getResult().getNewslist().get(position).getTime());

                List<String> list = getallpic(articleTopBean.getResult().getNewslist().get(position).getIndexdetail());
                viewHolder.setImage(R.id.iv_listview_picture_t6_1, list.get(0))
                        .setImage(R.id.iv_listview_picture_t6_2,list.get(1))
                        .setImage(R.id.iv_listview_picture_t6_3,list.get(2));
             break;

        }



        return viewHolder.getItemView();

    }

        public static List<String> getallpic(String str) {
            String str1 = str.substring(11, str.length());
            String[] strs = str1.split(",");
            List<String> list = Arrays.asList(strs);
            return list;
        }

}
