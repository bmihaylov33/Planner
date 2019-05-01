package com.example.bmihaylov.planner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyFragment extends Fragment {

    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    private CalendarView mCalendarView;
    private List<EventDay> mEventDays = new ArrayList<>();

    public MonthlyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_monthly, container, false);

        mCalendarView = (CalendarView) mView.findViewById(R.id.calendarView);

        FloatingActionButton fab = (FloatingActionButton) mView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                //previewNote(eventDay);
            }
        });

        return mView;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
//            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
//            mCalendarView.setDate(myEventDay.getDate());
//            mEventDays.add(myEventDay);
//            mCalendarView.setEvents(mEventDays);
//        }
//    }


    private void addNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
        Snackbar.make(Objects.requireNonNull(getView()), "Add new note!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

//    private void previewNote(EventDay eventDay) {
//        Intent intent = new Intent(getActivity(), NotePreviewActivity.class);
//        if(eventDay instanceof MyEventDay){
//            intent.putExtra(EVENT, (MyEventDay) eventDay);
////            intent.putExtra("DATE", );
//        }
//        startActivity(intent);
//    }

}
