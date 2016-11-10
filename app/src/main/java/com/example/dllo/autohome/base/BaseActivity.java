package com.example.dllo.autohome.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/10/24.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 绑定布局
        setContentView(getLayout());
        // 初始化组件
        initViews();
        // 初始化数据
        initData();

    }

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void initData();

    // 简化findViewById
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    // 设置监听
    protected  void setClick(View.OnClickListener clickListener, View ... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }


}
