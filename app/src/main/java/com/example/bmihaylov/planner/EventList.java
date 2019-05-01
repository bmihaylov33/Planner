package com.example.bmihaylov.planner;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventList extends ArrayAdapter<MyEventDay> {

    private Activity context;
    List<MyEventDay> events;

    public EventList(Activity context, List<MyEventDay> events) {
        super(context, R.layout.layout_events_list, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_events_list, null, true);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewNote = (TextView) listViewItem.findViewById(R.id.textViewNote);
        ImageView iconView = (ImageView) listViewItem.findViewById(R.id.imageViewIcon);

        MyEventDay myEventDay = events.get(position);
        textViewDate.setText(myEventDay.getDate().toString());
        iconView.setImageResource(myEventDay.getImageResource());
        textViewNote.setText(myEventDay.getNote());
        return listViewItem;
    }

}
