package com.customcalendarview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by muthusrinivasan on 4/18/16.
 */
public class TwoTextArrayAdapter extends ArrayAdapter<Item> {
    private LayoutInflater mInflater;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }

    public TwoTextArrayAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;

    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);
        View View;
        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.my_calendar_view, null);
                    holder.View = getItem(position).getView(mInflater, convertView);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.task_detail_header, null);
                    holder.View = getItem(position).getView(mInflater, convertView);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder {
        public View View;
    }
}


