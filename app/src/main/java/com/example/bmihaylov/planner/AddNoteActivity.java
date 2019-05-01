package com.example.bmihaylov.planner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private TextView textDate, textTime;
    private Button buttonSave;
    private ImageButton buttonDate, buttonTime, buttonColorl;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Calendar calendarDate, calendarTime;

    int year, month, dayOfMonth, mHour, mMinute;

    private DatabaseReference databaseEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        buttonSave = (Button) findViewById(R.id.addNoteButton);
        buttonDate = (ImageButton) findViewById(R.id.buttonDate);
        buttonTime =(ImageButton) findViewById(R.id.buttonTime);
        buttonColorl = (ImageButton) findViewById(R.id.buttonColor);
        textDate = (TextView) findViewById(R.id.textDate);
        textTime = (TextView) findViewById(R.id.textTime);

        final EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);

        databaseEvent = FirebaseDatabase.getInstance().getReference("event");

        calendarDate = Calendar.getInstance();
        year = calendarDate.get(Calendar.YEAR);
        month = calendarDate.get(Calendar.MONTH);
        dayOfMonth = calendarDate.get(Calendar.DAY_OF_MONTH);
        textDate.setText(dayOfMonth + "/" + month + "/" + year);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarDate = Calendar.getInstance();
                year = calendarDate.get(Calendar.YEAR);
                month = calendarDate.get(Calendar.MONTH);
                dayOfMonth = calendarDate.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddNoteActivity.this, R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textDate.setText(day + "/" + (month + 1) + "/" + year + 1900);
                                calendarDate.set(year + 1900,month + 1,day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarTime = Calendar.getInstance();
                mHour = calendarTime.get(Calendar.HOUR_OF_DAY);
                mMinute = calendarTime.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(AddNoteActivity.this, R.style.DialogTheme,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                textTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                MyEventDay myEventDay = new MyEventDay(calendarDate.getTime(),
                        R.drawable.ic_message, textTime.getText().toString(), noteEditText.getText().toString(), titleEditText.getText().toString(), );

                String id = databaseEvent.push().getKey();

                databaseEvent.child(id).setValue(myEventDay);

                returnIntent.putExtra(MonthlyFragment.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
