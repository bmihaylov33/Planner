package com.example.bmihaylov.planner;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;


import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyFragment extends Fragment {

    private CalendarView calendarView;
    private FloatingActionButton fab;

    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    public WeeklyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_weekly, container, false);

        calendarView = (CalendarView) mView.findViewById(R.id.calendarView);
        fab = (FloatingActionButton) mView.findViewById(R.id.fab);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               // previewNote();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        return mView;
    }

    private void addNote() {
        Intent intent = new Intent(getActivity(), AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

//    private void previewNote(EventsDay eventDay) {
//        Intent intent = new Intent(getActivity(), NotePreviewActivity.class);
//        if(eventDay instanceof MyEventDay){
//            intent.putExtra(EVENT, (MyEventDay) eventDay);
////            intent.putExtra("DATE", );
//        }
//        startActivity(intent);
//    }

}
