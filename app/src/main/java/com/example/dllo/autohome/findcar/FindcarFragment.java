package com.example.dllo.autohome.findcar;

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
public class FindcarFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_findcar;
    }

    @Override
    protected void initView() {
        TabLayout findCarTB = bindView(R.id.find_car_tb);
        ViewPager findCarVp = bindView(R.id.find_car_vp);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewCarFragment());
        fragments.add(new UsedCarFragment());
        MyFindCarVPAdapter adapter = new MyFindCarVPAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        findCarVp.setAdapter(adapter);
        findCarTB.setupWithViewPager(findCarVp);
        findCarTB.setSelectedTabIndicatorColor(Color.BLACK);
    }

    @Override
    protected void initData() {

    }
}
