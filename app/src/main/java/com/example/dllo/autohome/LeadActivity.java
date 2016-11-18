package com.example.dllo.autohome;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.autohome.base.BaseActivity;

/**
 * Created by dllo on 16/11/15.
 */
public class LeadActivity extends BaseActivity{

    private ImageView leadIv;
    private TextView leadTv;
    private Intent intent;
    private boolean isIntent = true;

    @Override
    protected int getLayout() {
        return R.layout.activity_lead;
    }

    @Override
    protected void initViews() {
        leadIv = bindView(R.id.lead_img);
        leadTv = bindView(R.id.lead_tv);

        intent = new Intent();

        leadTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isIntent = !isIntent;
                intent.setClass(LeadActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        AnimationDrawable drawable = (AnimationDrawable) leadIv.getDrawable();
        drawable.start();

        timer.start();

    }

    private CountDownTimer timer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long l) {
            leadTv.setText(l / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            if (isIntent){

            intent.setClass(LeadActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }
        }
    };

}
