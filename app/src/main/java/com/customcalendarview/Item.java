package com.customcalendarview;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by muthusrinivasan on 4/18/16.
 */
public interface Item {
    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);
}
