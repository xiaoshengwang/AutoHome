package com.example.dllo.autohome.forum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.ForumSeletedBean;
import com.example.dllo.autohome.forum.selectedall.DetailsActivity;

/**
 * Created by dllo on 16/11/5.
 */
public class ForumSeletedLvAdapter extends BaseAdapter{

    private Context context;
    private ForumSeletedBean forumSeletedBean;
    private String id;

    public void setForumSeletedBean(ForumSeletedBean forumSeletedBean) {
        this.forumSeletedBean = forumSeletedBean;
    }

    public void addBean(ForumSeletedBean bean) {
        Log.d("ForumSeletedLvAdapter", "forumSeletedBean:" + forumSeletedBean);
        this.forumSeletedBean.getResult().addData(bean.getResult().getList());
        notifyDataSetChanged();
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
    public View getView(int i, View view, final ViewGroup viewGroup) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_forum_selected);
        viewHolder.setText(R.id.tv_listview_title, forumSeletedBean.getResult().getList().get(i).getTitle())
                .setText(R.id.tv_listview_replycount, forumSeletedBean.getResult().getList().get(i).getBbsname())
                .setText(R.id.tv_listview_date, forumSeletedBean.getResult().getList().get(i).getReplycounts() + "回帖")
                .setImage(R.id.iv_listview_picture, forumSeletedBean.getResult().getList().get(i).getSmallpic());

        id = forumSeletedBean.getResult().getList().get(i).getTopicid()+ "";
        Log.d("ForumSeletedLvAdapter12", "id:" + id);
        viewHolder.setItemClick(new MyListener(id));

        return viewHolder.getItemView();
    }

    class MyListener implements View.OnClickListener{
        String id;

        public MyListener(String id) {
            this.id = id;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", "论坛");
            Log.d("ForumSeletedLvAdapter12", id);
            context.startActivity(intent);
        }
    }

}
