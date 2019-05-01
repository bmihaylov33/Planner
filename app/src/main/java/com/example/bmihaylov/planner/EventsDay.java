package com.example.bmihaylov.planner;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class EventsDay{
    private Date mDay;
    private int mImageResource;
    private String mTime, userName;

    public EventsDay(Date day) {
        mDay = day;
    }

    public EventsDay(Date day, int imageResource, String time, String user) {
        mDay = day;
        mImageResource = imageResource;
        mTime = time;
        userName = user;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public Date getDate() {
        return mDay;
    }

    public String getTime() {
        return mTime;
    }

    public String getUser() {return userName;}
}
