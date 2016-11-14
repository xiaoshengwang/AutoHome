package com.example.dllo.autohome.sale;

import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.URLValues;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.bean.SaleBean;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class SaleFragment extends BaseFragment {

    private RecyclerView saleFragmentRv;
    private SaleBean bean;
    private SaleRvAdapter adapter;
    private LinearLayoutManager manager;
    @Override
    protected int getLayout() {
        return R.layout.fragment_sale;
    }

    @Override
    protected void initView() {
        saleFragmentRv = bindView(R.id.sale_fragment_rv);
        adapter = new SaleRvAdapter(getActivity());
        manager = new LinearLayoutManager(getActivity());

    }

    @Override
    protected void initData() {

        GsonRequest<SaleBean> gsonRequest = new GsonRequest<>(SaleBean.class, URLValues.FIND_URL, new Response.Listener<SaleBean>() {
            @Override
            public void onResponse(SaleBean response) {
                Log.d("SaleFragment", "傻逼");
                adapter.setSaleBean(getType(response));
                saleFragmentRv.setAdapter(adapter);
                saleFragmentRv.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    private SaleBean getType(SaleBean bean) {

        int type = 100;
        for (int i = 0; i < bean.getResult().getCardlist().size(); i++) {
            switch (i) {
                case 0:
                    type=0;
                break;
                case 1:
                    type=1;
                    break;
                case 2:
                    type=2;
                    break;
                case 3:
                    type=4;
                    break;
                case 4:
                    type=5;
                    break;
                case 5:
                    type=6;
                    break;
                case 6:
                    type=7;
                    break;
                case 7:
                    type=7;
                    break;
                case 8:
                    type=8;
                    break;
            }
            bean.getResult().getCardlist().get(i).setcType(type);
            for (int j = 0; j <bean.getResult().getCardlist().get(i).getData().size() ; j++) {
                bean.getResult().getCardlist().get(i).getData().get(j).setType(type);
            }
        }
        return bean;

    }

}
