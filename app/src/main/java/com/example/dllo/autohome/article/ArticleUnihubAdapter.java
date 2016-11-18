package com.example.dllo.autohome.article;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.UnihubBean;
import com.example.dllo.autohome.forum.selectedall.DetailsActivity;

/**
 * Created by dllo on 16/11/15.
 */
public class ArticleUnihubAdapter extends BaseAdapter{
    private UnihubBean bean;
    private CommonViewHolder viewHolder;

    public void setBean(UnihubBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getResult().getNewslist().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getNewslist().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return bean.getResult().getNewslist().get(position).getMediatype();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        switch (getItemViewType(i)){
            case 1:
                viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_unihub_one);
                viewHolder.setImage(R.id.unihub_user_one_icon, bean.getResult().getNewslist().get(i).getUserpic(), true)
                        .setText(R.id.unihub_one_uesrname, bean.getResult().getNewslist().get(i).getUsername())
                        .setImage(R.id.unihub_one_pic, bean.getResult().getNewslist().get(i).getThumbnailpics().get(0))
                        .setText(R.id.unihub_one_title, bean.getResult().getNewslist().get(i).getTitle())
                        .setText(R.id.unihub_one_date, bean.getResult().getNewslist().get(i).getPublishtime())
                        .setText(R.id.unihub_one_replycount, "评论" + bean.getResult().getNewslist().get(i).getReplycount())
                        .setText(R.id.unihub_one_praisenum, "赞" + bean.getResult().getNewslist().get(i).getPraisenum());
                break;
            case 3:

                viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_unihub_three);
                viewHolder.setImage(R.id.unihub_user_three_icon, bean.getResult().getNewslist().get(i).getUserpic(), true)
                        .setText(R.id.unihub_three_uesrname, bean.getResult().getNewslist().get(i).getUsername())
                        .setImage(R.id.unihub_three_pic, bean.getResult().getNewslist().get(i).getThumbnailpics().get(0))
                        .setText(R.id.unihub_three_title, bean.getResult().getNewslist().get(i).getTitle())
                        .setText(R.id.unihub_three_date, bean.getResult().getNewslist().get(i).getPublishtime())
                        .setText(R.id.unihub_three_replycount, "评论" + bean.getResult().getNewslist().get(i).getReplycount())
                        .setText(R.id.unihub_three_praisenum, "赞" + bean.getResult().getNewslist().get(i).getPraisenum());
                break;
        }

        viewHolder.setItemClick(new MyListener(viewGroup.getContext()));

        return viewHolder.getItemView();
    }

    class MyListener implements View.OnClickListener{
        private Context context;
        public MyListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "不好意思, 没有接口~", Toast.LENGTH_SHORT).show();
        }
    }

}
