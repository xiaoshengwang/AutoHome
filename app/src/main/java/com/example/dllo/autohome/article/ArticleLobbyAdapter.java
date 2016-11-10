package com.example.dllo.autohome.article;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.LobbyistsBean;

/**
 * Created by dllo on 16/11/3.
 */
public class ArticleLobbyAdapter extends BaseAdapter{
    LobbyistsBean lobbyistsBean;
    Context context;
    private CommonViewHolder viewHolder;

    public void setLobbyistsBean(LobbyistsBean lobbyistsBean) {
        this.lobbyistsBean = lobbyistsBean;
    }

    public ArticleLobbyAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return lobbyistsBean == null ? 0 : lobbyistsBean.getResult().getList().size();
    }

    @Override
    public Object getItem(int i) {
        return lobbyistsBean.getResult().getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder = CommonViewHolder.getViewHolder(view, viewGroup, R.layout.item_article_lobby_listview);
        viewHolder.setText(R.id.lobby_list_title_tv, lobbyistsBean.getResult().getList().get(i).getTitle())
                .setText(R.id.lobby_list_comment_tv, lobbyistsBean.getResult().getList().get(i).getReplycount() + "评论")
                .setText(R.id.lobby_list_date_tv, lobbyistsBean.getResult().getList().get(i).getTime())
                .setImage(R.id.lobby_list_img, lobbyistsBean.getResult().getList().get(i).getSmallpic());
        return viewHolder.getItemView();
    }
}
