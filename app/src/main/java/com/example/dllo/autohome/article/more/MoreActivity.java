package com.example.dllo.autohome.article.more;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseActivity;
import com.example.dllo.autohome.bean.MoreRvEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/26.
 */
public class MoreActivity extends BaseActivity{
    private static final String [] s = {"游记", "优创+", "说客", "视频", "快报", "行情", "新闻", "评测", "导购", "用车", "技术", "文化", "改装"};
    private RecyclerView moreRc;
    private MoreRvAdapter adapter;
    private ImageView moreIv;

    @Override
    protected int getLayout() {
        return R.layout.activity_more;
    }

    @Override
    protected void initViews() {
        moreRc = bindView(R.id.activity_more_rv);
        moreIv = bindView(R.id.more_activity_return);

        moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

        adapter = new MoreRvAdapter();

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            arrayList.add(s[i]);
        }
        ItemTouchHelper.Callback callback = mCallback();
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(moreRc);

        adapter.setArrayList(arrayList);
        moreRc.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        moreRc.setLayoutManager(manager);
    }

    @Subscribe
    public void getPosition(MoreRvEvent event) {
        if (event.getI() >= 0) {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private ItemTouchHelper.Callback mCallback() {
        return new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //instanceof

                if (layoutManager instanceof GridLayoutManager) {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0);
                } else {
                    return ItemTouchHelper.Callback.makeMovementFlags(
                            ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                adapter.move(from, to);
                return false;
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    View view = viewHolder.itemView;
                    view.setBackgroundColor(0xFF0000FF);//把背景设置为红色
                }

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0x000000);

            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
    }
}
