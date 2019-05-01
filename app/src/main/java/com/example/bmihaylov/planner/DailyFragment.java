package com.example.bmihaylov.planner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {

    private TextView textView;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mview = inflater.inflate(R.layout.fragment_daily, container, false);

        textView = (TextView) mview.findViewById(R.id.textViewDate);
        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        date = dateFormat.format(calendar.getTime());
        textView.setText(date);

        return mview;
    }

}
