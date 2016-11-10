package com.example.dllo.autohome.forum.selected_all;

import android.content.Intent;
import android.webkit.WebView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

/**
 * Created by dllo on 16/11/7.
 */
public class DetailsActivity extends BaseActivity{

    private WebView detailsWeb;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        detailsWeb = bindView(R.id.activity_details_wab);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String s = intent.getStringExtra("id");
        String url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t"
        + s +"o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
        detailsWeb.loadUrl(url);
    }
}
