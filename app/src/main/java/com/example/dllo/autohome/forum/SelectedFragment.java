package com.example.dllo.autohome.forum;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.bean.ForumSeletedBean;
import com.example.dllo.autohome.forum.hot_post.HotPostActivity;
import com.example.dllo.autohome.forum.selected_all.SelectedAllActivity;
import com.example.dllo.autohome.forum.selected_all.SelectedAllItemActivity;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/27.
 */
public class SelectedFragment extends BaseFragment implements View.OnClickListener {

    private ListView forumSelectedLv;
    private LinearLayout forumSelectedLl;
    private View view;
    private RelativeLayout hotPost;
    private Intent intent;
    private ImageView wife;
    private ImageView beauty;
    private ImageView hige_position;
    private ImageView motorola;
    private ImageView choosy;
    private ImageView woman_find_car;
    private ImageView forum_sensation;

    @Override
    protected int getLayout() {
        return R.layout.fragment_forum_selected;
    }

    @Override
    protected void initView() {
        forumSelectedLv = bindView(R.id.forum_selected_lv);
        forumSelectedLl = bindView(R.id.forum_selected_ll);


        view = LayoutInflater.from(getActivity()).inflate(R.layout.forum_selected_list_view_headview, null);
        RelativeLayout selectedAll = bindView(view, R.id.forum_article_all);
        RelativeLayout hotPost = bindView(view, R.id.forum_24hours);
        wife = bindView(view, R.id.wife);
        beauty = bindView(view, R.id.beauty);
        hige_position = bindView(view, R.id.hige_position);
        motorola = bindView(view, R.id.motorola);
        choosy = bindView(view, R.id.choosy);
        woman_find_car = bindView(view, R.id.woman_find_car);
        forum_sensation = bindView(view, R.id.forum_sensation);

        hotPost.setOnClickListener(this);
        selectedAll.setOnClickListener(this);
        wife.setOnClickListener(this);
        beauty.setOnClickListener(this);
        hige_position.setOnClickListener(this);
        motorola.setOnClickListener(this);
        choosy.setOnClickListener(this);
        woman_find_car.setOnClickListener(this);
        forum_sensation.setOnClickListener(this);
        forumSelectedLv.addHeaderView(view);
    }

    @Override
    protected void initData() {

        intent = new Intent();
        GsonRequest<ForumSeletedBean> gsonRequestQuatation = new GsonRequest<>(ForumSeletedBean.class, ForumUrlValues.FORUM_SELECTED_URL
                , new Response.Listener<ForumSeletedBean>() {

            @Override
            public void onResponse(ForumSeletedBean response) {
                ForumSeletedLvAdapter adapter = new ForumSeletedLvAdapter(getActivity());
                adapter.setForumSeletedBean(response);
                forumSelectedLv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequestQuatation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forum_article_all:

                intent.setClass(getActivity(), SelectedAllActivity.class);
                startActivity(intent);
                break;
            case R.id.forum_24hours:
                intent.setClass(getActivity(), HotPostActivity.class);
                intent.putExtra("title", "热帖");
                startActivity(intent);
                break;
            case R.id.wife:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "媳妇当车模");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c104-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.beauty:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "美人'记'");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c110-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.hige_position:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "高端阵地");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c118-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.motorola:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "摩友天地");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c184-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.choosy:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "精挑细选");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c121-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.woman_find_car:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "妹子选车");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c191-p1-s30.json");
                startActivity(intent);
                break;
            case R.id.forum_sensation:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "论坛红人馆");
                intent.putExtra("url", "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c172-p1-s30.json");
                startActivity(intent);
                break;
        }
    }
}
