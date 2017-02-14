package com.example.marmm.reminderdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marmm on 2/13/17.
 */

public class ReminderAdapter extends ArrayAdapter<Reminder> {


    private Context context;
    private int layoutResourceId;


    public ReminderAdapter(Context context, int layoutResourceId, List<Reminder> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        //Retrieve all Views of a Reminder
        TextView reminder = (TextView) row.findViewById(R.id.reminderText);
        TextView timeStamp = (TextView) row.findViewById(R.id.reminderTimeStamp);


        //Retrieve Reminder at the position
        Reminder item = getItem(position);

        //Set the description for this row
        reminder.setText(item.getmReminderText());
        timeStamp.setText(item.getmReminderTimeStamp());

        return row;
    }


}

