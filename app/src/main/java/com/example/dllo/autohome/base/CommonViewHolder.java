package com.example.dllo.autohome.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.autohome.tools.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 * 通用的ViewHolder
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{
    // SparseArray 用法和HashMap一样
    // 但是key 固定是int类型
    // 用它来存放所有的View, key就是View的id
    private SparseArray<View> views;
    private View itemView; // 行布局

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    /**
     * 通过View的id来获得指定View
     * 如果该View没有赋值, 就先执行findViewById
     * 然后把它放到View的集合里
     * 使用泛型来取消强转
     * @param id
     * @return  指定View
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            // 证明SparseArray里没有这个View
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }
    // 返回行布局
    public View getItemView() {
        return itemView;
    }

    // 专门给ListView使用的方法
    public static CommonViewHolder getViewHolder(View itemView, ViewGroup viewGroup, int itemId){
        CommonViewHolder viewHolder;
        if (itemView == null) {
            Context context = viewGroup.getContext();
            itemView = LayoutInflater.from(context).inflate(itemId, viewGroup, false);
            viewHolder = new CommonViewHolder(itemView);
            itemView.setTag(viewHolder);
        }else {
            viewHolder = (CommonViewHolder) itemView.getTag();
        }
        return viewHolder;

    }

    // 专门给RecycleView使用的方法
    public static CommonViewHolder getViewHolder(ViewGroup parent, int itemId) {
        return getViewHolder(null, parent, itemId);
    }

    /*********ViewHolder 设置数据的方法***********/
    // 设置文字
    public CommonViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        return this;
    }

    public CommonViewHolder setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }

    public CommonViewHolder setImage(int id, String url){
        ImageView imageView = getView(id);
        // 网络请求图片
        VolleySingleton.getInstance().getImage(url, imageView );
        return this;
    }

    public CommonViewHolder setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setViewClick(int id, View.OnClickListener listener) {

        getView(id).setOnClickListener(listener);

        return this;
    }

}