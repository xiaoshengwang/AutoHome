package com.example.dllo.autohome.forum.selected_all;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;
import com.example.dllo.autohome.bean.SelectedAllItemBean;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.VolleySingleton;

/**
 * Created by dllo on 16/11/5.
 */
public class SelectedAllItemActivity extends BaseActivity implements View.OnClickListener {

    private ListView lv;
    private TextView tv;
    private ImageView iv;

    @Override
    protected int getLayout() {
        return R.layout.activity_selected_all_item;
    }

    @Override
    protected void initViews() {
        lv = bindView(R.id.selected_item_activity_lv);
        tv = bindView(R.id.selected_activity_item_title_tv);
        iv = bindView(R.id.selected_activity_item_return);

        iv.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        tv.setText(title);

        GsonRequest<SelectedAllItemBean> gsonRequestQuatation = new GsonRequest<>(SelectedAllItemBean.class, url
                , new Response.Listener<SelectedAllItemBean>() {

            @Override
            public void onResponse(SelectedAllItemBean response) {
                SelectedAllItemAdapter adapter = new SelectedAllItemAdapter(SelectedAllItemActivity.this);
                adapter.setBean(response);
                lv.setAdapter(adapter);

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
            case R.id.selected_activity_item_return:
                finish();
                break;
        }
    }
}
