package com.example.dllo.autohome.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;
import com.example.dllo.autohome.tools.CircleDrawable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import cn.bmob.v3.BmobUser;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/10/24.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView userpicImg;
    private RelativeLayout loginRl;
    private TextView loginTv;
    private Intent intent;
    private TextView backTv;
    private ImageView qq;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        userpicImg = bindView(R.id.iv_userpic);
        loginRl = bindView(R.id.login_rl);
        loginTv = bindView(R.id.login_tv);
        backTv = bindView(R.id.back_tv);
        qq = bindView(R.id.qq);

        loginRl.setOnClickListener(this);
        backTv.setOnClickListener(this);
        qq.setOnClickListener(this);
        intent = new Intent();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ahlib_userpic_default);

        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        userpicImg.setImageDrawable(circleDrawable);

        if (BmobUser.getObjectByKey("username") != null){
            loginTv.setText(BmobUser.getObjectByKey("username").toString());
        }

    }

    @Subscribe
    public void getUsername(BmobUser bmobUser){
        loginTv.setText(bmobUser.getUsername());
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_rl:
                if ("账号登录".equals(loginTv.getText())){
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getActivity(), MyProfileActivity.class);
                    intent.putExtra("username", loginTv.getText());
                    startActivity(intent);
                }
                break;
            case R.id.back_tv:
                loginTv.setText("账号登录");
                break;
            case R.id.qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        String icon = platformDb.getUserIcon();

                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        getActivity().setResult(0, intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
