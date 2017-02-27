package com.example.marmm.reminderdemo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marmm on 2/27/17.
 */


public class ReminderCursorAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public ReminderCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.reminder_row_layout, viewGroup, false);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor c) {

        String reminderData = c.getString(c.getColumnIndexOrThrow(DBHelper.REMINDER_NAME));
        String reminderTimeStamp = c.getString(c.getColumnIndexOrThrow(DBHelper.REMINDER_TIMESTAMP));

        /**
         * Next set the reminder text.
         */

        TextView title_text = (TextView) v.findViewById(R.id.reminderText);
        if (title_text != null) {
            title_text.setText(reminderData);
        }
        TextView date_text = (TextView) v.findViewById(R.id.reminderTimeStamp);
        if (date_text != null) {
            date_text.setText(reminderTimeStamp);
        }

        /**
         * Display the star icon
         */
        ImageView item_image = (ImageView) v.findViewById(R.id.imageView);
        item_image.setVisibility(ImageView.VISIBLE);
    }
}