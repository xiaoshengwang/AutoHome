package com.example.dllo.autohome.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.LiteOrmSingleton;
import com.example.dllo.autohome.tools.VolleySingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    private EditText editTextSearch;
    private ListView classListview;
    private SearchAdapter searchAdapter;
    private WebView webView;
    private TextView tvCancel;
    private ListView historyListView;
    private ArrayList<SearchCarNameBean> beanArrayList1;
    private LinearLayout linearLayoutHistory;
    private TextView tvDeleteAll;
    private HistoryAdapter historyAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        editTextSearch = bindView(R.id.et_search);
        editTextSearch.addTextChangedListener(this);
        classListview = bindView(R.id.lv_search_class);
        historyListView = bindView(R.id.lv_history);
        linearLayoutHistory = bindView(R.id.ll_history_delete_all);
        tvDeleteAll = bindView(R.id.tv_history_delete_all);
        webView = (WebView) findViewById(R.id.web_search);
        tvCancel = (TextView) findViewById(R.id.tv_search_cancel);
        tvCancel.setOnClickListener(this);
        tvDeleteAll.setOnClickListener(this);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayoutHistory.setVisibility(View.GONE);
                historyListView.setVisibility(View.GONE);
                String string = beanArrayList1.get(i).getName();
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + string + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                editTextSearch.setText(beanArrayList1.get(i).getName());
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(searchUrl);
            }
        });
    }

    @Override
    protected void initData() {

        LiteOrmSingleton.getIntstance().queryAllData(new LiteOrmSingleton.OnQueryListenerAll<SearchCarNameBean>() {
            @Override
            public void onQuery(List list) {
                beanArrayList1 = (ArrayList<SearchCarNameBean>) list;
                Log.d("SearchActivity", "list:" + list);
                if (list == null || list.size() == 0) {
                    linearLayoutHistory.setVisibility(View.GONE);
                }
                historyAdapter = new HistoryAdapter();
                Collections.reverse(beanArrayList1);
                for (int i = 0; i < beanArrayList1.size() - 1; i++) {
                    for (int j = beanArrayList1.size() - 1; j > i; j--) {
                        if (beanArrayList1.get(j).getName().equals(beanArrayList1.get(i).getName())) {
                            beanArrayList1.remove(i);
                        }
                    }
                }

                historyAdapter.setBeanArrayList(beanArrayList1);
                historyListView.setAdapter(historyAdapter);
                historyListView.setVisibility(View.VISIBLE);
            }
        }, SearchCarNameBean.class);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        historyListView.setVisibility(View.GONE);
        innitRequestInternet(editable);
    }

    private void innitRequestInternet(Editable s) {
        if (!s.toString().equals("")) {
            String str = s.toString();
            String url = "http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + str + "&t=4";
            GsonRequest<CarSesrchBean> gsonrequest = new GsonRequest<CarSesrchBean>(CarSesrchBean.class, url, new Response.Listener<CarSesrchBean>() {
                @Override
                public void onResponse(CarSesrchBean response) {
                    searchAdapter = new SearchAdapter(SearchActivity.this);
                    searchAdapter.setBean(response);
                    classListview.setAdapter(searchAdapter);
                    classListviewClicklisener(response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            VolleySingleton.getInstance().addRequest(gsonrequest);
        }
    }

    private void classListviewClicklisener(final CarSesrchBean response) {
        Log.d("oooooooo", "呵呵");
        classListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                linearLayoutHistory.setVisibility(View.GONE);
                ArrayList<SearchCarNameBean> beanArrayList = new ArrayList<SearchCarNameBean>();
                String str = EncodeUtil.encode(response.getResult().getWordlist().get(position).getName());
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword=" + str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                editTextSearch.setText(response.getResult().getWordlist().get(position).getName());

                SearchCarNameBean bean = new SearchCarNameBean();
                bean.setName(response.getResult().getWordlist().get(position).getName());
                beanArrayList.add(bean);

                LiteOrmSingleton.getIntstance().insertData(beanArrayList);
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(searchUrl);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                finish();
                break;
            case R.id.tv_history_delete_all:
                LiteOrmSingleton.getIntstance().deleteAllData();
                for (int i = beanArrayList1.size(); i > 0; i--) {
                    beanArrayList1.remove(0);
                }
                historyAdapter.notifyDataSetChanged();
                linearLayoutHistory.setVisibility(View.GONE);
                break;
        }
    }
}