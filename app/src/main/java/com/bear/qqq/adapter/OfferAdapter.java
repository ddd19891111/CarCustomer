package com.bear.qqq.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bear.qqq.R;
import com.bear.qqq.model.Item;
import java.util.List;

public class OfferAdapter extends ArrayAdapter<Item> {
    private List<Item> list;
    private class ViewHolder {
        TextView name;
        TextView offer;
        TextView content;
    }
    public OfferAdapter(Context context, List<Item> list) {
        super(context, 0, list);
        this.list = list;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Item info = getItem(position);
        if(null == info){
            return convertView;
        }
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(getContext(),
                    R.layout.item_offer, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.tv_offer_name);
            holder.offer = convertView.findViewById(R.id.tv_offer_offer);
            holder.content = convertView.findViewById(R.id.tv_offer_content);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText("厂家："+info.getNickname());
        holder.offer.setText("报价："+info.getOffer());
        holder.content.setText("备注："+info.getContent());
        return convertView;
    }
}
