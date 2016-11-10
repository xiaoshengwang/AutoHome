package com.example.dllo.autohome;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.autohome.article.ArticleFragment;
import com.example.dllo.autohome.base.BaseActivity;
import com.example.dllo.autohome.findcar.FindcarFragment;
import com.example.dllo.autohome.forum.ForumFragment;
import com.example.dllo.autohome.my.MyFragment;
import com.example.dllo.autohome.sale.SaleFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private RadioButton mainArticleBtn;
    private RadioButton mainForumBtn;
    private RadioButton mainFindCarBtn;
    private RadioButton mainSaleBtn;
    private RadioButton mainMyBtn;
    private FragmentManager manager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mainArticleBtn = bindView(R.id.main_btn_article);
        mainForumBtn = bindView(R.id.main_btn_forum);
        mainFindCarBtn = bindView(R.id.main_btn_findcar);
        mainSaleBtn = bindView(R.id.main_btn_sale);
        mainMyBtn = bindView(R.id.main_btn_my);

    }

    @Override
    protected void initData() {
        mainArticleBtn.setChecked(true);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment, new ArticleFragment());
        transaction.commit();

        setClick(this, mainArticleBtn, mainForumBtn, mainFindCarBtn, mainSaleBtn, mainMyBtn);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.main_btn_article:
                transaction.replace(R.id.main_fragment, new ArticleFragment());
                break;
            case R.id.main_btn_forum:
                transaction.replace(R.id.main_fragment, new ForumFragment());
                break;
            case R.id.main_btn_findcar:
                transaction.replace(R.id.main_fragment, new FindcarFragment());
                break;
            case R.id.main_btn_sale:
                transaction.replace(R.id.main_fragment, new SaleFragment());
                break;
            case R.id.main_btn_my:
                transaction.replace(R.id.main_fragment, new MyFragment());
                break;
        }
        transaction.commit();
    }
}
