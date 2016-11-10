package com.example.dllo.autohome.tools;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/10/24.
 * 自定义的请求, 直接返回数据类
 */
public class GsonRequest<T> extends Request<T>{
    private final Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mTClass;

    public GsonRequest(int method
            , Class<T> tClass // 用于Geson解析的, 数据类的类型
            , String url, Response.Listener<T> mListener, Response.ErrorListener listener) {
        super(method, url, listener);
        // 对成功的监听进行赋值
        this.mListener = mListener;
        mGson = new Gson();
        this.mTClass = tClass;
    }

    public GsonRequest(
            Class<T> tClass // 用于Geson解析的, 数据类的类型
            , String url, Response.Listener<T> mListener, Response.ErrorListener listener) {

        this(Method.GET, tClass, url, mListener, listener);

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;// 请求成功的字符串
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        // 解析
        T t = mGson.fromJson(parsed, mTClass);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
