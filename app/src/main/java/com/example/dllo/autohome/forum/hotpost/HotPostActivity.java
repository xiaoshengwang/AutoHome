package com.example.dllo.autohome.forum.hotpost;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;
import com.example.dllo.autohome.bean.HotPostBean;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.URLValues;
import com.example.dllo.autohome.tools.VolleySingleton;


/**
 * Created by dllo on 16/11/7.
 */
public class HotPostActivity extends BaseActivity implements View.OnClickListener {

    private ImageView hot_post_iv;
    private ListView hot_post_lv;
    private TextView hot_post_title;
    private SwipeRefreshLayout refresh;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_post;
    }

    @Override
    protected void initViews() {
        hot_post_iv = bindView(R.id.hot_post_activity_return);
        hot_post_lv = bindView(R.id.hot_post_activity_lv);
        hot_post_title = bindView(R.id.activity_hot_post_title);
        refresh = bindView(R.id.refresh);

        hot_post_iv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        hot_post_title.setText(intent.getStringExtra("title"));

        gsonRequest(URLValues.FORUM_SELECTED_URL);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gsonRequest(URLValues.FORUM_SELECTED_URL);
                refresh.setRefreshing(false);
            }
        });



    }

    private void gsonRequest(String forumSelectedUrl) {

        GsonRequest<HotPostBean> gsonRequestQuatation = new GsonRequest<>(HotPostBean.class, forumSelectedUrl
                , new Response.Listener<HotPostBean>() {

            @Override
            public void onResponse(HotPostBean response) {
                HotPostAdapter adapter = new HotPostAdapter();
                adapter.setBean(response);
                hot_post_lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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
            case R.id.hot_post_activity_return:
                finish();
                break;
        }
    }
}
