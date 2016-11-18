package com.example.dllo.autohome.forum.selectedall;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

/**
 * Created by dllo on 16/11/7.
 */
public class DetailsActivity extends BaseActivity{

    private WebView detailsWeb;
    private ImageView returnImg;
    private String url;

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViews() {
        detailsWeb = bindView(R.id.activity_details_wab);
        returnImg = bindView(R.id.details_return);

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");

        switch (title) {
            case "论坛":
                url = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t" +
                        id +
                        "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
                break;
            case "推荐":
                url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" +
                        id +
                        "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";

                break;
            case "说客":
                url = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n" +
                        id +
                        "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.htm";

                break;
            case "快报":

                url = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n" +
                        id +
                        "-lastid0-o1.json";
                Log.d("DetailsActivity123", id);
                Log.d("DetailsActivity123", url);
                break;
            case "视频":
                url = "http://v.autohome.com.cn/v-" + id + ".html";
                break;
            default:

                break;
        }

        detailsWeb.loadUrl(url);
    }
}
