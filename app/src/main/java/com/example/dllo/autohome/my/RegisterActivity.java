package com.example.dllo.autohome.my;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/11/14.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView registerReturn;
    private EditText registerUsername;
    private EditText registerPassword;
    private Button registerBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        registerReturn = bindView(R.id.register_return);
        registerUsername = bindView(R.id.register_username);
        registerPassword = bindView(R.id.register_password);
        registerBtn = bindView(R.id.register_register);

        registerReturn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_return:
                finish();

                break;
            case R.id.register_register:

                if (registerUsername.getText() == null && registerPassword.getText() == null){
                    Toast.makeText(this, "用户名或密码未填写", Toast.LENGTH_SHORT).show();
                }else {
                    BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(registerUsername.getText() + ""); // 用户名
                    bmobUser.setPassword(registerPassword.getText() + ""); // 密码
                    bmobUser.signUp(new SaveListener<BmobUser>() { // 泛型手动变 BmobUser
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null){ // 注册成功
                                EventBus.getDefault().post(bmobUser);
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else { // 注册失败
                                Log.d("RegisterActivity", e.getMessage());
                                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                break;
        }
    }
}
