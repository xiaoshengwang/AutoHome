package com.example.dllo.autohome.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.autohome.R;


public class SearchAdapter extends BaseAdapter {
    private Context mContext;
    public int i = 1;
    private SearchBean bean;


    public void setBean(SearchBean bean) {
        this.bean = bean;
    }

    public SearchAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getResult().getWordlist().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getResult().getWordlist().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.tv.setText(bean.getResult().getWordlist().get(position).getName());
        return convertView;
    }

    class ViewHolder {
        private TextView tv;
        private ImageView img;

        public ViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.searchactivity_tv);
        }
    }
}
