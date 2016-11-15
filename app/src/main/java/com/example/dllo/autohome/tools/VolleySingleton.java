package com.example.dllo.autohome.tools;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.MyApp;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {

    private  static  VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;// 用来请求图片的

    private  VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
        // 初始化ImageLoader
        imageLoader = new ImageLoader(mRequestQueue, new MemoryCache());
    }

    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {

        synchronized (VolleySingleton.class) {
        if (volleySingleton == null) {
        volleySingleton = new VolleySingleton();
             }
        }
        }
        return volleySingleton;
    }

    // 获得RequestQueue
    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    public void getImage(String url, ImageView imageView) {
        imageLoader.get(url, ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }

    public void getBitmap(String url,BitmapListener bitmapListener){
        imageLoader.get(url,new BitmapImageListener(bitmapListener));

    }

    class BitmapImageListener implements ImageLoader.ImageListener{
        private BitmapListener bitmapListener;

        public BitmapImageListener(BitmapListener bitmapListener) {
            this.bitmapListener = bitmapListener;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if(bitmap!=null){
                bitmapListener.onGetBitmap(bitmap);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            bitmapListener.onGetBitmap(null);
        }
    }
    public interface BitmapListener{
        void onGetBitmap(Bitmap bitmap);
    }

    public <T> void addRequest(Request<T> request) {
        mRequestQueue.add(request);
    }

}
