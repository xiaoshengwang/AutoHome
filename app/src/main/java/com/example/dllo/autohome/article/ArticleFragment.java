package com.example.dllo.autohome.article;


import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.article.more.MoreActivity;
import com.example.dllo.autohome.bean.MoreRvEvent;
import com.example.dllo.autohome.search.SearchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ArticleFragment extends BaseFragment implements View.OnClickListener{

    private TabLayout articleTB;
    private ViewPager articleVP;
    private ImageView articleIMG;
    private String[] s = {"推荐", "优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装"};
    private ImageView search;
    private Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {
        articleTB = bindView(R.id.article_tb);
        articleVP = bindView(R.id.article_vp);
        articleIMG = bindView(R.id.article_img_more);

        ImageView search = bindView(R.id.article_search);

        setClick(this, articleIMG);
        setClick(this, search);

    }

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

        ArticleVPAdapter adapter = new ArticleVPAdapter(getChildFragmentManager(), s);
        articleVP.setAdapter(adapter);
        articleTB.setupWithViewPager(articleVP);
        articleTB.setSelectedTabIndicatorColor(Color.BLACK);
        articleTB.setTabMode(TabLayout.MODE_SCROLLABLE);

        intent = new Intent();

    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getPosition(MoreRvEvent event){
//        articleVP.setCurrentItem(event.getI());
//    }
    @Subscribe
    public void sahbiPosition(MoreRvEvent event) {
        articleVP.setCurrentItem(event.getI());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.article_img_more:
                // 跳转到更多界面
                intent.setClass(getActivity(), MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.article_search:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
