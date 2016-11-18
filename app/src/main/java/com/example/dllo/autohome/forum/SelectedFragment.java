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
import com.example.dllo.autohome.forum.hotpost.HotPostActivity;
import com.example.dllo.autohome.forum.selectedall.SelectedAllActivity;
import com.example.dllo.autohome.forum.selectedall.SelectedAllItemActivity;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.URLValues;
import com.example.dllo.autohome.tools.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by dllo on 16/10/27.
 */
public class SelectedFragment extends BaseFragment implements View.OnClickListener {

    private PullToRefreshListView forumSelectedLv;
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
    private int i = 1;
    private ForumSeletedLvAdapter adapter;

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

        ListView listView = forumSelectedLv.getRefreshableView();
        listView.addHeaderView(view);
    }

    @Override
    protected void initData() {

        intent = new Intent();
        GsonRequest<ForumSeletedBean> gsonRequestQuatation = new GsonRequest<>(ForumSeletedBean.class, URLValues.FORUM_SELECTED_URL
                , new Response.Listener<ForumSeletedBean>() {

            @Override
            public void onResponse(ForumSeletedBean response) {
                adapter = new ForumSeletedLvAdapter(getActivity());
                adapter.setForumSeletedBean(response);
                forumSelectedLv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequestQuatation);
        forumSelectedLv.setMode(PullToRefreshBase.Mode.BOTH);
        forumSelectedLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                GsonRequest<ForumSeletedBean> gsonRequestQuatation = new GsonRequest<>(ForumSeletedBean.class,URLValues.FORUM_SELECTED_URL

                        , new Response.Listener<ForumSeletedBean>() {

                    @Override
                    public void onResponse(ForumSeletedBean response) {
                        adapter.setForumSeletedBean(response);
                        forumSelectedLv.setAdapter(adapter);
                        forumSelectedLv.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequestQuatation);



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                i = i + 1;
                GsonRequest<ForumSeletedBean> gsonRequestQuatation = new GsonRequest<>(ForumSeletedBean.class,
                        "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c0-p" + i + "-s30.json"
                        , new Response.Listener<ForumSeletedBean>() {

                    @Override
                    public void onResponse(ForumSeletedBean response) {
                        adapter.addBean(response);
                        forumSelectedLv.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequestQuatation);

                forumSelectedLv.onRefreshComplete();
            }
        });



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
                intent.putExtra("url", URLValues.WIFE_MODEL_URL);
                startActivity(intent);
                break;
            case R.id.beauty:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "美人'记'");
                intent.putExtra("url", URLValues.NOTORIOUS_URL);
                startActivity(intent);
                break;
            case R.id.hige_position:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "高端阵地");
                intent.putExtra("url", URLValues.HIGH_POINT_URL);
                startActivity(intent);
                break;
            case R.id.motorola:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "摩友天地");
                intent.putExtra("url", URLValues.FRIEND_HEAVEN_EARTH_URL);
                startActivity(intent);
                break;
            case R.id.choosy:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "精挑细选");
                intent.putExtra("url", URLValues.AUSLESE_URL);
                startActivity(intent);
                break;
            case R.id.woman_find_car:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "妹子选车");
                intent.putExtra("url", URLValues.SISTER_CAR_URL);
                startActivity(intent);
                break;
            case R.id.forum_sensation:
                intent.setClass(getActivity(), SelectedAllItemActivity.class);
                intent.putExtra("title", "论坛红人馆");
                intent.putExtra("url", URLValues.HOF_URL);
                startActivity(intent);
                break;
        }
    }
}
