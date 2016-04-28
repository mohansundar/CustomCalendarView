package com.customcalendarview;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muthusrinivasan on 4/15/16.
 */
public class DayViewAdapter extends BaseAdapter {

    MyCalendarActivity myCalendarActivity;
    List<CustomCalendarModel> monthsList ;
    ArrayList<StoreSplitStringModel> yearList = new ArrayList<>();
    private static LayoutInflater inflater=null;


    public DayViewAdapter(MyCalendarActivity myCalendarActivity, List<CustomCalendarModel> monthsList) {
        this.myCalendarActivity = myCalendarActivity ;
        this.monthsList = monthsList ;

        inflater = ( LayoutInflater )myCalendarActivity.
                getSystemService(myCalendarActivity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return monthsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final CalenderViewHolder holder;
        StoreSplitStringModel storeSplitStringModel ;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.calendar_view_holder, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new CalenderViewHolder();

            holder.date_view_number = (TextView) vi.findViewById(R.id.date_view_number);
            holder.date_view_letter = (TextView) vi.findViewById(R.id.date_view_letter);


            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);

        } else {
            holder = (CalenderViewHolder) vi.getTag();
        }

        String dates = String.valueOf(monthsList.get(position).getGroupOfDate());



        //TextView tv= (TextView) findViewById(R.id.textview);
       // tv.setText(ss1);

        String[] parts = dates.split(",");
        String part1 = parts[0];
        String part2 = parts[1];
        String[] splitmonthNdate = part2.split(" ");
        String splitmonthNdatepart1 = splitmonthNdate[0];
        String splitmonthNdatepart2 = splitmonthNdate[1];
        String splitmonthNdatepart3 = splitmonthNdate[2];
        String part3 = parts[2];
      //  storeSplitStringModel = new StoreSplitStringModel(part1,part2,splitmonthNdatepart1,splitmonthNdatepart2,splitmonthNdatepart3,part3);

      //  yearList.add(storeSplitStringModel);
    //    holder.date_view.setText(Html.fromHtml(String.format("<font size=\"18\">"+splitmonthNdatepart3+"th"+"\n"+"</font><font size=\"10\">"+part1+"</font>")));

        holder.date_view_number.setText(part2);
        holder.date_view_letter.setText(part3);
        Log.v("DayViewAdapter ", "dates " + part2+" "+part3);

        return vi;

    }
    public class CalenderViewHolder{
        TextView date_view_number ;
        TextView date_view_letter ;
    }

}
