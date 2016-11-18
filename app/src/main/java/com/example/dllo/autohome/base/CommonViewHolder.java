package com.example.dllo.autohome.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.autohome.sale.BusinessRvAdapter;
import com.example.dllo.autohome.sale.FoundFieldItemRVAdapter;
import com.example.dllo.autohome.sale.FoundTimeGoGoItemRVAdapter;
import com.example.dllo.autohome.sale.FoundTimeItemRVAdapter;
import com.example.dllo.autohome.tools.CircleDrawable;
import com.example.dllo.autohome.tools.GlideImageLoder;
import com.example.dllo.autohome.tools.VolleySingleton;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.List;

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
    private RecyclerView recyclerView;

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


    public CommonViewHolder setImage(int id, String url, boolean is){
        final ImageView imageView = getView(id);
        // 网络请求图片
        VolleySingleton.getInstance().getBitmap(url, new VolleySingleton.BitmapListener() {
            @Override
            public void onGetBitmap(Bitmap bitmap) {
                CircleDrawable drawable = new CircleDrawable(bitmap);
                imageView.setImageDrawable(drawable);
            }
        });
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

    public void setBanner(int sale_rv_banner, int center, int i, int circleIndicator, List<String> imgUrls) {

        Banner banner = getView(sale_rv_banner);
        banner.setBannerStyle(circleIndicator);
        banner.setImageLoader(new GlideImageLoder());
        banner.setImages(imgUrls);
        banner.setBannerAnimation(Transformer.Default);
        banner.isAutoPlay(true);
        banner.setDelayTime(i);
        banner.setIndicatorGravity(center);
        banner.start();
    }



    public void setRecyclerViewH(Context mContext, int i, int item_found_time_rv, RecyclerView.Adapter timeItemRVAdapter) {

        recyclerView = getView(item_found_time_rv);
        recyclerView.setAdapter(timeItemRVAdapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, i);
        recyclerView.setLayoutManager(manager);

    }

    public void setImg(int item_found_single_img, String imageurl) {
        ImageView imageView = getView(item_found_single_img);
        // 网络请求图片

        VolleySingleton.getInstance().getImage(imageurl, imageView );
    }

    public void setRecyclerViewTime(Context mContext, int item_found_time_gogo_rv, RecyclerView.Adapter timeGoGoItemRVAdapter) {

        recyclerView = getView(item_found_time_gogo_rv);
        recyclerView.setAdapter(timeGoGoItemRVAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

    }

    public void setRecyclerView(Context mContext, int i, int item_found_field_recyclerview, RecyclerView.Adapter fieldItemRVAdapter) {

        recyclerView = getView(item_found_field_recyclerview);
        recyclerView.setAdapter(fieldItemRVAdapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, i);
        recyclerView.setLayoutManager(manager);


    }
}
