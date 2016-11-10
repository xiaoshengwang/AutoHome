package com.example.dllo.autohome.findcar;

import android.webkit.WebView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/10/27.
 */
public class UsedCarFragment extends BaseFragment {

    private WebView usedCarWeb;

    @Override
    protected int getLayout() {
        return R.layout.fragment_findcar_usedcar;
    }

    @Override
    protected void initView() {
        usedCarWeb = bindView(R.id.used_car_web);
    }

    @Override
    protected void initData() {
        usedCarWeb.loadUrl("http://m.che168.com/dalian/list/?sourcename=mainapp&safe=0&carsafe=1&pvareaid=102254");
    }
}
