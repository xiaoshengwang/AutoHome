package com.example.dllo.autohome.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

/**
 * Created by dllo on 16/11/14.
 */
public class MyProfileActivity extends BaseActivity{

    private TextView profileTilte;
    private ImageView profileReturn;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_profile;
    }

    @Override
    protected void initViews() {
        profileTilte = bindView(R.id.profile_title);
        profileReturn = bindView(R.id.profile_return);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        profileTilte.setText(intent.getStringExtra("username"));

        profileReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
