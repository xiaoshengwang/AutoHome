package com.example.dllo.autohome.article;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.bean.ArticleTopBean;
import com.example.dllo.autohome.bean.LobbyistsBean;
import com.example.dllo.autohome.tools.GsonRequest;
import com.example.dllo.autohome.tools.Point;
import com.example.dllo.autohome.tools.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/10/25.
 */
public class ArticleTopFragment extends BaseFragment {

    private static final String KEY = "pos";
    private ViewPager articleTopViewPager;
    private ArrayList<String> arrayList;
    private Handler handler;
    private ArticleTopVpAdapter vpAdapter;
    private List<Point> points;
    private LinearLayout linearLayout;
    private int type;
    private TextView tv1;
    private PullToRefreshListView lvArticle;
    private View listViewHeadView;

    public static ArticleTopFragment getInstance(int pos) {
        ArticleTopFragment topFragment = new ArticleTopFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, pos);
        topFragment.setArguments(bundle);
        return topFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_article_top;
    }

    @Override
    protected void initView() {
        lvArticle = bindView(R.id.article_lv);
        listViewHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.article_listview_headview, null);
        linearLayout = bindView(listViewHeadView, R.id.ll);
        articleTopViewPager = bindView(listViewHeadView, R.id.article_top_vp);




        arrayList = new ArrayList<>();
//        arrayList.add("http://pic29.nipic.com/20130506/3822951_102005116000_2.jpg");
//        arrayList.add("http://pic36.nipic.com/20131125/8821914_090743677000_2.jpg");
//        arrayList.add("http://img.boqiicdn.com/Data/BK/A/1411/26/img77931416972193.jpg");

    }


    @Override
    protected void initData() {



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (articleTopViewPager != null && msg.what == 1){
                    int num = articleTopViewPager.getCurrentItem();
                    articleTopViewPager.setCurrentItem(num + 1);
                }
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        };

        articleTopViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPage = position % vpAdapter.getImgCount();
                for (Point point : points) {
                    point.setSelected(false);
                }
                points.get(currentPage).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvArticle.setMode(PullToRefreshBase.Mode.BOTH);
        if (getArguments() != null){
            type = getArguments().getInt(KEY);

            switch (type) {
                case 0:
//                    tv1.setText("推荐");
                    GsonRequest<ArticleTopBean> gsonRequest = new GsonRequest<>(ArticleTopBean.class, ArticleUrlValues.ARTICLE_TOP_URL
                            , new Response.Listener<ArticleTopBean>() {

                        @Override
                        public void onResponse(ArticleTopBean response) {
                            ArticleLvAdapter adapter = new ArticleLvAdapter(getActivity());
                            adapter.setRecommendPageListViewBean(response);
                            ListView listView = lvArticle.getRefreshableView();
                            listView.addHeaderView(listViewHeadView);
                            lvArticle.setAdapter(adapter);
                            for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                                arrayList.add(response.getResult().getFocusimg().get(i).getImgurl());
                            }
                            vpAdapter = new ArticleTopVpAdapter();
                            vpAdapter.setArrayList(arrayList);
                            articleTopViewPager.setAdapter(vpAdapter);

                            initPoints();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingleton.getInstance().addRequest(gsonRequest);
                    break;
                case 1:
//                    tv1.setText("优创+");
                    break;
                case 2:
//                    tv1.setText("说客");
                    GsonRequest<LobbyistsBean> gsonRequestLobby = new GsonRequest<>(LobbyistsBean.class, ArticleUrlValues.LOBBYISTS_URL
                            , new Response.Listener<LobbyistsBean>() {

                        @Override
                        public void onResponse(LobbyistsBean response) {

                            ArticleLobbyAdapter adapter = new ArticleLobbyAdapter(getActivity());
                            adapter.setLobbyistsBean(response);
                            Log.d("ArticleTopFragment", "啊啊啊啊啊");
                            lvArticle.setAdapter(adapter);


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingleton.getInstance().addRequest(gsonRequestLobby);
                    lvArticle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                            GsonRequest<LobbyistsBean> gsonRequestLobby = new GsonRequest<>(LobbyistsBean.class, ArticleUrlValues.LOBBYISTS_URL
                                    , new Response.Listener<LobbyistsBean>() {

                                @Override
                                public void onResponse(LobbyistsBean response) {

                                    ArticleLobbyAdapter adapter = new ArticleLobbyAdapter(getActivity());
                                    adapter.setLobbyistsBean(response);
                                    lvArticle.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    lvArticle.onRefreshComplete();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            VolleySingleton.getInstance().addRequest(gsonRequestLobby);
                        }

                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                            lvArticle.onRefreshComplete();
                        }
                    });

                    break;
                case 3:
//                    tv1.setText("视频");
                    break;
                case 4:
//                    tv1.setText("快报");
                    break;
                case 5:
//                    tv1.setText("行情");
                    gsonRequest(ArticleUrlValues.QUOTATION_URL);
                    break;
                case 6:
//                    tv1.setText("新闻");
                    gsonRequest(ArticleUrlValues.NEWS_URL);
                    break;
                case 7:
//                    tv1.setText("评测");
                    gsonRequest(ArticleUrlValues.EVALUATING_URL);
                    break;
                case 8:
//                    tv1.setText("导购");
                    gsonRequest(ArticleUrlValues.SHOPPING_URL);
                    break;
                case 9:
//                    tv1.setText("用车");
                    gsonRequest(ArticleUrlValues.USED_CAR_URL);
                    break;
                case 10:
//                    tv1.setText("技术");
                    gsonRequest(ArticleUrlValues.TECHNOLOGY_URL);
                    break;
                case 11:
//                    tv1.setText("文化");
                    gsonRequest(ArticleUrlValues.CULTURE_URL);
                    break;
                case 12:
//                    tv1.setText("改装");
                    gsonRequest(ArticleUrlValues.REFIT_URL);
                    break;
            }
        }

    }

    private void gsonRequest(String lobbyistsUrl) {
        GsonRequest<ArticleTopBean> gsonRequestQuatation = new GsonRequest<>(ArticleTopBean.class, lobbyistsUrl
                , new Response.Listener<ArticleTopBean>() {

            @Override
            public void onResponse(ArticleTopBean response) {
                ArticleLvAdapter adapter = new ArticleLvAdapter(getActivity());
                adapter.setRecommendPageListViewBean(response);
                lvArticle.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequestQuatation);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(1);
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public void initPoints() {
        points = new ArrayList<>();
        for (int i = 0; i < vpAdapter.getImgCount(); i++) {
            Point point = new Point(getActivity());
            points.add(point);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
            linearLayout.addView(point, layoutParams);
        }
    }

}
