package com.cxh.keyboarddemo.keyboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.cxh.keyboarddemo.R;

import java.util.List;


/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/24 0024 12:00
 */

public class IdcardKeyboardAdapter extends BaseAdapter {

    private List<String> keyList;

    private Context mContext;

    public IdcardKeyboardAdapter(@NonNull Context context, List<String> keyList) {
        this.mContext = context;
        this.keyList = keyList;
    }

    @Override
    public int getCount() {
        return keyList.size();
    }

    @Override
    public Object getItem(int position) {
        return keyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.keyboard_grid_item_layout, null);
            holder.keyText = (TextView) convertView.findViewById(R.id.btn_keys);
            holder.keyImage = (ViewGroup) convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 设置键盘
        // 最后一位为删除键
        holder.keyText.setVisibility(View.GONE);
        holder.keyImage.setVisibility(View.GONE);
        if (position == keyList.size() - 1) {
            holder.keyImage.setVisibility(View.VISIBLE);
        } else {
            holder.keyText.setVisibility(View.VISIBLE);
            holder.keyText.setText(keyList.get(position));
        }
        return convertView;
    }

    protected class ViewHolder {
        TextView keyText;
        ViewGroup keyImage;
    }
}
