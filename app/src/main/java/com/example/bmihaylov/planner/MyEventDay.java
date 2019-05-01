package com.example.bmihaylov.planner;

import android.app.DatePickerDialog;
import android.os.Parcel;
import android.os.Parcelable;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;
import java.util.Date;

class MyEventDay extends EventsDay implements Parcelable {
    private String mNote, mTitle;

    public MyEventDay(Date day, int imageResource, String time, String note, String title, String user) {
        super(day, imageResource, time);
        mNote = note;
        mTitle = title;
    }

    String getNote() {
        return mNote;
    }

    String getTitle() {return mTitle;}

    private MyEventDay(Parcel in) {
        super((Date) in.readSerializable(), in.readInt(), in.readString());
        mNote = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<MyEventDay> CREATOR = new Creator<MyEventDay>() {
        @Override
        public MyEventDay createFromParcel(Parcel in) {
            return new MyEventDay(in);
        }

        @Override
        public MyEventDay[] newArray(int size) {
            return new MyEventDay[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(getDate());
        parcel.writeInt(getImageResource());
        parcel.writeSerializable(getTime());
        parcel.writeString(mNote);
        parcel.writeString(mTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
