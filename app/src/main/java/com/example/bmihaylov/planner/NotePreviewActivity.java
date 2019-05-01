package com.example.bmihaylov.planner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.applandeo.materialcalendarview.EventDay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotePreviewActivity extends AppCompatActivity {

    private Intent intent;
    private TextView note, title;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    private String date, time;

    List events;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_preview);

        intent = getIntent();

        note = (TextView) findViewById(R.id.note);
        title = (TextView) findViewById(R.id.title);

        events = new ArrayList<>();

        if (intent != null) {
            Object event = intent.getParcelableExtra(MonthlyFragment.EVENT);

            if(event instanceof MyEventDay){
                MyEventDay myEventDay = (MyEventDay) event;

                getSupportActionBar().setTitle(getFormattedDate(myEventDay.getDate()));
                note.setText(myEventDay.getNote());

                return;
            }

            if(event instanceof EventDay){
                EventDay eventDay = (EventDay)event;
                getSupportActionBar().setTitle(getFormattedDate(eventDay.getCalendar().getTime()));
            }

            database.child("event").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        MyEventDay myEventDay = noteDataSnapshot.getValue(MyEventDay.class);
                        events.add(myEventDay);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("READ", "Error trying to get classified ad for update " +
                            ""+databaseError);
                }
            });
        }

//        calendar = Calendar.getInstance();
//        getSupportActionBar().setTitle(getFormattedDate(calendar.getTime()));
//        note.setText(date);

    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
