package com.customcalendarview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by muthusrinivasan on 4/18/16.
 */
public class Header implements Item {
    private String  month;
    private String  year;

    public Header(String month,String  year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public int getViewType() {
        return TwoTextArrayAdapter.RowType.HEADER_ITEM.ordinal();
    }



    @Override
    public View getView(LayoutInflater inflater, View convertView) {

        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.task_detail_header, null);
            // Do some initialization
        } else {
            view = convertView;
        }


        TextView month = (TextView) view.findViewById(R.id.month);
        TextView year = (TextView) view.findViewById(R.id.year);
        //month.setText(month);
       // month.setText(year);


        return view;
    }


}