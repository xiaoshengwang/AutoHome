package com.example.dllo.autohome.forum;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.autohome.R;
import com.example.dllo.autohome.base.BaseFragment;

/**
 * Created by dllo on 16/10/27.
 */
public class ForumForumFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getLayout() {
        return R.layout.fragment_forum_forum;
    }

    @Override
    protected void initView() {
        ImageView forumCar = bindView(R.id.forum_forum_car);
        ImageView forumArea = bindView(R.id.forum_forum_acre);
        ImageView forumTheme = bindView(R.id.forum_forum_theme);

        forumCar.setOnClickListener(this);
        forumArea.setOnClickListener(this);
        forumTheme.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "不好意思, 没有接口~", Toast.LENGTH_SHORT).show();
    }
}
