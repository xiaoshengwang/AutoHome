package com.example.dllo.autohome.forum.selectedall;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/5.
 */
public class SelectedAllActivity extends BaseActivity implements View.OnClickListener {

    private final String [] s = {"媳妇当车模","美人'记' ","论坛红人馆","论坛讲师","精挑细选","现身说法","高端阵地","电动车"
            ,"汇买车","行车点评","超级试购员","海外购车","经典老车","妹子选车","优惠购车","原创大片"
            ,"顶配风采","改装有礼","养车有道","首发阵营","新车直播","历史选题","摩友天地","蜜月之旅"
            ,"甜蜜婚礼","摄影课堂","车友聚会","单车部落","杂谈俱乐部","华北游记","西南游记","东北游记"
            ,"西北游记","华中游记","华南游记","华东游记","港澳台游记","海外游记","沧海遗珠"};

    private ImageView seletedActivityReturn;
    private ArrayList<String> arrayList;
    private ListView selectedActivityLv;

    @Override
    protected int getLayout() {
        return R.layout.activity_selected_all;
    }

    @Override
    protected void initViews() {
        seletedActivityReturn = bindView(R.id.selected_activity_return);
        selectedActivityLv = bindView(R.id.selected_activity_lv);

        seletedActivityReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            arrayList.add(s[i]);
        }

        SelectedActivityAdapter activityAdapter = new SelectedActivityAdapter(this);
        activityAdapter.setArrayList(arrayList);
        selectedActivityLv.setAdapter(activityAdapter);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selected_activity_return:
                finish();
                break;
        }
    }
}
