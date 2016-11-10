package com.example.dllo.autohome.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/24.
 *
 * !!!!!!!!!  写完 MyApp  一定一定要注册!!!!!!!!!!
 */
public class MyApp extends Application{
    // 所有跟界面无关的都可以使用这个全局的Context

    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }

}
