package com.example.dllo.autohome.sale;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.CommonViewHolder;
import com.example.dllo.autohome.bean.SaleBean;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/10.
 */
public class SaleRvAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private SaleBean saleBean;
    Context mContext;

    public SaleRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private int width,height;
    private CommonViewHolder mCommonViewHolder;
    //焦点图
    private final static int FOCUS_TYPE=0;
    //业务接口
    private final static int ENTRANCE_TYPE=1;
    //图文时限专区
    private final static int TIME_TYPE=2;
    //单帧小号横栏
    private final static int SMALL_LINE_TYPE=3;
    //限时抢购
    private final static int TIME_GOGO_TYPE=4;
    //田字小号专区
    private final static int FIELD_TYPE=5;
    //活动专区
    private final static int ACTIVITY_TYPE=6;
    //模块列表
    private final static int MODE_TYPE=7;
    //商品列表
    private final static int PRODUCE_TYPE=8;

    public void setSaleBean(SaleBean saleBean) {
        this.saleBean = saleBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return saleBean.getResult().getCardlist().get(position).getcType();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case FOCUS_TYPE:
                CommonViewHolder focusVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_focus);
                return focusVH;
            case ENTRANCE_TYPE:
                CommonViewHolder entranceVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_entrance);
                return entranceVH;
            case TIME_TYPE:
                CommonViewHolder timeVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_time);
                return timeVH;
            case SMALL_LINE_TYPE:
                CommonViewHolder smallLineVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_single);
                return smallLineVH;
            case TIME_GOGO_TYPE:
                CommonViewHolder timeGoGoVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_time_gogo);
                return timeGoGoVH;
            case FIELD_TYPE:
                CommonViewHolder fieldVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_field);
                return fieldVH;
            case ACTIVITY_TYPE:
                CommonViewHolder activityVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_activity);
                return activityVH;
            case PRODUCE_TYPE:
                CommonViewHolder produceVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_produce);
                return produceVH;
            case MODE_TYPE:
                CommonViewHolder modeVH = CommonViewHolder.getViewHolder(parent, R.layout.item_found_mode);
                return modeVH;
            default:
                return null;
             }
        }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case FOCUS_TYPE://焦点图
                List<String> imgUrls=new ArrayList<>();
                for (int i = 0; i < saleBean.getResult().getCardlist().get(position).getData().size(); i++) {
                    imgUrls.add(saleBean.getResult().getCardlist().get(position).getData().get(i).getImageurl());
                }
                holder.setBanner(R.id.sale_rv_banner, BannerConfig.CENTER,3000,BannerConfig.CIRCLE_INDICATOR,imgUrls);
                break;
            case ENTRANCE_TYPE://业务接口
                BusinessRvAdapter entranceItemRVAdapter=new BusinessRvAdapter();
                entranceItemRVAdapter.setBean(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerViewTime(mContext,R.id.item_found_entrance_rv,entranceItemRVAdapter);
                break;
            case SMALL_LINE_TYPE://单帧小号横栏
                holder.setImg(R.id.item_found_single_img,
                        saleBean.getResult().getCardlist().get(position).getData().get(0).getImageurl());
                break;
            case FIELD_TYPE://田字小号专区
                FoundFieldItemRVAdapter fieldItemRVAdapter=new FoundFieldItemRVAdapter();
                fieldItemRVAdapter.setDatas(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerView(mContext,2,R.id.item_found_field_recyclerview,fieldItemRVAdapter);
                break;
            case ACTIVITY_TYPE://活动专区
                holder.setImage(R.id.item_found_activity_one, saleBean.getResult().getCardlist().get(position).getData().get(0).getImageurl());
                holder.setImage(R.id.item_found_activity_two, saleBean.getResult().getCardlist().get(position).getData().get(1).getImageurl());
                holder.setImage(R.id.item_found_activity_three, saleBean.getResult().getCardlist().get(position).getData().get(2).getImageurl());
                break;
            case PRODUCE_TYPE://商品列表
                FoundProduceItemRVAdapter produceItemRVAdapter=new FoundProduceItemRVAdapter();
                produceItemRVAdapter.setDatas(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerView(mContext,1,R.id.item_found_produce,produceItemRVAdapter);
                break;
            case TIME_GOGO_TYPE://限时抢购
                FoundTimeGoGoItemRVAdapter timeGoGoItemRVAdapter=new FoundTimeGoGoItemRVAdapter();
                timeGoGoItemRVAdapter.setDataBean(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerViewTime(mContext,R.id.item_found_time_gogo_rv,timeGoGoItemRVAdapter);
                break;
            case TIME_TYPE://图文限时专区
                FoundTimeItemRVAdapter timeItemRVAdapter=new FoundTimeItemRVAdapter();
                timeItemRVAdapter.setDataBeen(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerViewH(mContext,4,R.id.item_found_time_rv,timeItemRVAdapter);
                break;
            case MODE_TYPE://模块列表
                ModeItemAdapter modeItemAdapter = new ModeItemAdapter();
                modeItemAdapter.setDataBeen(saleBean.getResult().getCardlist().get(position).getData());
                holder.setRecyclerViewH(mContext,2,R.id.item_found_mode_rv,modeItemAdapter);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return saleBean == null ? 0 : saleBean.getResult().getCardlist().size();
    }
}
