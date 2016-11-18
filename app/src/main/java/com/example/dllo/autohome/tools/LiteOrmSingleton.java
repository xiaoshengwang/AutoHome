package com.example.dllo.autohome.tools;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.autohome.base.MyApp;
import com.example.dllo.autohome.search.SearchCarNameBean;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by dllo on 16/11/8.
 */
public class LiteOrmSingleton {
    private static LiteOrmSingleton base;
    private LiteOrm mLiteOrm;  //初始化的时候一般都要写在单例里 不然会提示关闭之前数据库或者出奇怪的问题
    private Handler mHandler;//用来做线程之间的切换的
    private Executor mExecutorPool;//线程池

    private LiteOrmSingleton() {
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getContext(), "carHome.db");
        mHandler = new Handler(Looper.getMainLooper());
        int coreNum = Runtime.getRuntime().availableProcessors();
        mExecutorPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    public static LiteOrmSingleton getIntstance() {

        if (base == null){
            synchronized (LiteOrmSingleton.class){
                if (base == null) {
                    base = new LiteOrmSingleton();
                }
            }
        }

        return base;
    }

    public <T> void insertData(final List<T> t) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(t);
            }
        }).start();
    }

    public void deleteAllData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(SearchCarNameBean.class);
            }
        }).start();
    }

    public <T> void queryAllData(OnQueryListenerAll<T> monQueryListener, Class<T> tClass) {
        mExecutorPool.execute(new QueryRunnable(monQueryListener, tClass));
    }

    class CallBackRunnable<T> implements Runnable {

        OnQueryListenerAll<T> mOnQueryListener;
        List<T> tList;

        public CallBackRunnable(OnQueryListenerAll<T> mOnQueryListener, List<T> tList) {
            this.mOnQueryListener = mOnQueryListener;
            this.tList = tList;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(tList);
        }
    }

    class QueryRunnable<T> implements Runnable {
        private OnQueryListenerAll<T> mOnQueryListener;
        private Class<T> tClass;

        public QueryRunnable(OnQueryListenerAll<T> mOnQueryListener, Class<T> tClass) {
            this.mOnQueryListener = mOnQueryListener;
            this.tClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = mLiteOrm.query(tClass);
            mHandler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }

    public interface OnQueryListenerAll<T> {
        void onQuery(List<T> tList);
    }
}
