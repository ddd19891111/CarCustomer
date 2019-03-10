package com.bear.qqq.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bear.qqq.R;
import com.bear.qqq.model.Item;


import java.util.List;

public class MessageListAdapter extends ArrayAdapter<Item> {

    private List<Item> list;
    private class ViewHolder {
        ImageView ivProfilePhoto;
        TextView tvNick;
        TextView tvSignature;
    }

    public MessageListAdapter(Context context, List<Item> list) {
        super(context, 0, list);
        this.list = list;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Item info = getItem(position);// 从集合中取得数据
        if(null == info){
            return convertView;
        }
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(getContext(),
                    R.layout.item_friend_list, null);
            holder = new ViewHolder();
            holder.ivProfilePhoto = convertView.findViewById(R.id.ivProfilePhoto);
            holder.tvNick = convertView.findViewById(R.id.tvNick);
            holder.tvSignature = convertView.findViewById(R.id.tvSignature);
            //holder.btnDelFriend = convertView.findViewById(R.id.btnDelFriend);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivProfilePhoto.setImageResource(R.mipmap.p1);
        holder.tvNick.setText(info.getUsername());
        holder.tvSignature.setText(info.getContent());

        return convertView;
    }

}
