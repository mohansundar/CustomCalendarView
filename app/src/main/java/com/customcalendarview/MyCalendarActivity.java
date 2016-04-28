package com.customcalendarview;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

/**
 * Created by muthusrinivasan on 4/13/16.
 */
public class MyCalendarActivity extends Activity {

    Calendar calender ;
    SimpleDateFormat format ;
    String date ;
    int j = 0;
    String dates[] ;
    List<CustomCalendarModel> monthsList = new ArrayList<CustomCalendarModel>();
    StoreSplitStringModel storeSplitStringModel ;
    ListView day_view_list ;
    TextView month ,year;
    String monthAndYear = null ;
    Context mContext;
    String dateValue = null;
    int removeTHFromDateValue ;
    ArrayList<StoreSplitStringModel> yearList = new ArrayList<>();
    DayViewAdapters dayViewAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_calendar_view);
        getLayoutID() ;
        currentDate();
        dayViewAdapter = new DayViewAdapters(MyCalendarActivity.this , monthsList);
        day_view_list.setAdapter(dayViewAdapter);
        dayViewAdapter.notifyDataSetChanged();
        

        day_view_list.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


                TextView c = (TextView) view.findViewById(R.id.date_view_number);
                dateValue = (c.getText().toString());
                removeTHFromDateValue = Integer.parseInt(dateValue.replace("th", ""));
                month.invalidate();
                year.invalidate();
                Log.i("SCROLLING DOWN", "onScrollStateChanged " + dateValue + "hellWrld " + removeTHFromDateValue);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (mLastFirstVisibleItem < firstVisibleItem) {

                    j++;
                    Log.i("SCROLLING DOWN", "TRUE " + j);

                    if (removeTHFromDateValue >= 28) {

                        Log.i("removeTHFromDateValue ", "TRUE inside ");
                        getPostionValue(j);


                    }
                }
                if (mLastFirstVisibleItem > firstVisibleItem) {
                    j--;

                    if (removeTHFromDateValue >= 28) {

                        Log.i("removeTHFromDateValue ", "TRUE inside ");
                        getPostionValue(j);
                    }
                    Log.i("SCROLLING UP", "TRUE " + j);
                }
                mLastFirstVisibleItem = firstVisibleItem;
            }


        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        getPostionValue(j);


    }

    private void getPostionValue(int j) {
        Log.i("getPostionValue DOWN", "getPostionValue "+j);
        monthAndYear = monthsList.get(j).getGroupOfDate();

        String[] parts = monthAndYear.split(",");
        String part1 = parts[0];
        String part2 = parts[1];
        String[] splitmonthNdate = part2.split(" ");
        String splitmonthNdatepart1 = splitmonthNdate[0];
        String splitmonthNdatepart2 = splitmonthNdate[1];
        String splitmonthNdatepart3 = splitmonthNdate[2];
        String part3 = parts[2];

        storeSplitStringModel = new StoreSplitStringModel(part1,part2,splitmonthNdatepart1,splitmonthNdatepart2,splitmonthNdatepart3,part3);

        yearList.add(storeSplitStringModel);
        month.setText(storeSplitStringModel.getPart4());
        year.setText(storeSplitStringModel.getPart6());
        month.invalidate();
        year.invalidate();
        dayViewAdapter.notifyDataSetChanged();

    }

    private void getLayoutID(){
        day_view_list = (ListView)findViewById(R.id.day_view_list);
        month = (TextView) findViewById(R.id.month);
        year = (TextView) findViewById(R.id.year);
    }
    private void currentDate(){
        calender = Calendar.getInstance();

        format = new SimpleDateFormat("EEEE, MMM d, yyyy");
        date = format.format(new Date());
        CustomCalendarModel customCalendarModels = new CustomCalendarModel();
        customCalendarModels.setGroupOfDate(date);
        monthsList.add(0, customCalendarModels);
        Log.v("MyCalendarActivity", "date " + date);

        getNextDate(date);

    }



    public String getNextDate(String  curDate) {
        try {
            calender.setTime(format.parse(curDate));

            calender.add(Calendar.DATE, 1);  // number of days to add
            curDate = format.format(calender.getTime());

            CustomCalendarModel customCalendarModels = new CustomCalendarModel();
            customCalendarModels.setGroupOfDate(curDate);
            monthsList.add(1, customCalendarModels);
            Log.v("MyCalendarActivity", "curDate " + curDate);
            date = curDate ;
            //monthsList.add(date) ;
            for (int i = 0 ;i <= 2000; i++){
                CustomCalendarModel customCalendarModel = new CustomCalendarModel();
                getDate(date);

                //Date dates = format.parse(date);
                customCalendarModel.setGroupOfDate(date);
                monthsList.add(customCalendarModel);

            }


            Log.v("MyCalendarActivity", "curDate date getNextDate " + monthsList.get(0).getGroupOfDate());
            //getNextDate(date);
            Log.v("MyCalendarActivity", "curDate date " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDate ;

    }
    private String getDate(String nextDate){
        try {
            calender.setTime(format.parse(nextDate));

            calender.add(Calendar.DATE, 1);  // number of days to add
            nextDate = format.format(calender.getTime());
            Log.v("MyCalendarActivity", "getDate " + nextDate);
            date = nextDate ;


            Log.v("MyCalendarActivity", "curDate date " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextDate ;
    }




    public Date getDateBeforeTwoWeeks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -14); //2 weeks
        return calendar.getTime();
    }

    public class DayViewAdapters extends BaseAdapter {

        MyCalendarActivity myCalendarActivity;
        List<CustomCalendarModel> monthsList ;
        ArrayList<StoreSplitStringModel> yearList = new ArrayList<>();
        private LayoutInflater inflater=null;


        public DayViewAdapters(MyCalendarActivity myCalendarActivity, List<CustomCalendarModel> monthsList) {
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

            holder.date_view_number.setText(splitmonthNdatepart3+"th");
            holder.date_view_letter.setText(part1);
            Log.v("DayViewAdapter ","dates "+dates);

            return vi;

        }
        public class CalenderViewHolder{
            public TextView date_view_number ;
            public TextView date_view_letter ;
        }

    }
}
