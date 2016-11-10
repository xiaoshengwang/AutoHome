package com.example.dllo.autohome.sale;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class SaleFragment extends BaseFragment {

    private RecyclerView saleFragmentRv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sale;
    }

    @Override
    protected void initView() {
        saleFragmentRv = bindView(R.id.sale_fragment_rv);
    }

    @Override
    protected void initData() {

    }
}
