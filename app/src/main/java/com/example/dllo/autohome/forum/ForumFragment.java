package com.example.dllo.autohome.forum;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ForumFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView() {
        TabLayout forumTB = bindView(R.id.forum_tb);
        ViewPager forumVP = bindView(R.id.forum_vp);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectedFragment());
        fragments.add(new ForumForumFragment());
        MyForumVPAdapter adapter = new MyForumVPAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        forumVP.setAdapter(adapter);
        forumTB.setupWithViewPager(forumVP);
        forumTB.setSelectedTabIndicatorColor(Color.BLACK);
    }

    @Override
    protected void initData() {

    }
}
