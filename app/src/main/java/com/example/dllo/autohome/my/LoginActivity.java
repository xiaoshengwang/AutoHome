package com.example.dllo.autohome.my;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/11/14.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView loginReturn;
    private ImageView loginGif;
    private TextView loginLogo;
    private EditText loginUserName;
    private EditText loginPassword;
    private TextView loginRegister;
    private Button login;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        loginReturn = bindView(R.id.login_return);
        loginGif = bindView(R.id.login_gif);
        loginLogo = bindView(R.id.login_logo);
        loginUserName = bindView(R.id.login_user_name);
        loginPassword = bindView(R.id.login_password);
        loginRegister = bindView(R.id.login_register);
        login = bindView(R.id.login);

        EventBus.getDefault().register(this);

        loginReturn.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        AnimationDrawable drawable = (AnimationDrawable) loginGif.getDrawable();
        drawable.start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(loginLogo, "translationX", -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270
                , -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270
                , -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270
                , -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270, -270, 270);
        animator.setDuration(200000);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(loginLogo, "translationY", -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100
                , -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100
                , -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100
                , -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100, -100, 100);
        animator1.setDuration(200000);
        animator1.start();

    }

    @Subscribe
    public void getUserName(BmobUser userBean){
        loginUserName.setText(userBean.getUsername());
        Log.d("LoginActivity", userBean.getUsername());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_return:
                finish();
                break;
            case R.id.login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:

                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(loginUserName.getText() + "");
                bmobUser.setPassword(loginPassword.getText() + "");
                bmobUser.login( new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            EventBus.getDefault().post(bmobUser);
                        }else {
                            Log.d("LoginActivity", "e:" + e);
                        }
                    }
                });
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
