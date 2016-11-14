package com.example.dllo.autohome.findcar;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.tools.URLValues;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.bean.FindCarNewCarLvBean;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.VolleySingleton;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by dllo on 16/10/27.
 */
public class NewCarFragment extends BaseFragment {

    private StickyListHeadersListView leftLv;
    private ListView rightLv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_fincar_newcar;
    }

    @Override
    protected void initView() {
        leftLv = bindView(R.id.find_car_new_car_left_lv);
        rightLv = bindView(R.id.find_car_new_car_right_lv);
    }

    @Override
    protected void initData() {


        GsonRequest<FindCarNewCarLvBean> gsonRequest = new GsonRequest<>(FindCarNewCarLvBean.class, URLValues.NEWCAR_BRAND_URL
                , new Response.Listener<FindCarNewCarLvBean>() {

            @Override
            public void onResponse(FindCarNewCarLvBean response) {

                NewCarRightLvAdapter rightAdapter = new NewCarRightLvAdapter();
                NewCarLeftLvAdapter adapter = new NewCarLeftLvAdapter();

                rightAdapter.setBean(response);
                adapter.setBean(response);
                rightLv.setAdapter(rightAdapter);
                leftLv.setAdapter(adapter);
                leftLv.setVerticalScrollBarEnabled(false);
                leftLv.setFastScrollEnabled(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
        rightLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                leftLv.setSelection(i);
            }
        });

        leftLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                rightLv.smoothScrollToPositionFromTop(i, 0);
            }
        });


    }
}
