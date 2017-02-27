package com.example.marmm.reminderdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marmm on 2/27/17.
 */

public class DataSource {

    //Local variables and constants
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] REMINDERS_ALL_COLUMNS = {DBHelper.REMINDER_ID, DBHelper.REMINDER_NAME,
            DBHelper.REMINDER_TIMESTAMP};


    public DataSource(Context context) {
        mContext = context;
        mDBHelper = new DBHelper(context);
        mDatabase = mDBHelper.getWritableDatabase();

    }

    // Opens the database to use it
    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        mDBHelper.close();
    }


    public void createReminder(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.REMINDER_NAME, reminder.getmReminderText());
        values.put(DBHelper.REMINDER_TIMESTAMP, reminder.getmReminderTimeStamp());
        mDatabase.insert(DBHelper.TABLE_REMINDERS, null, values);

    }

    public Cursor getAllReminders() {
        return mDatabase.query(DBHelper.TABLE_REMINDERS, REMINDERS_ALL_COLUMNS, null, null, null, null, null);
    }


    public void deleteReminder(Long id) {
        mDatabase.delete(DBHelper.TABLE_REMINDERS, DBHelper.REMINDER_ID + " =?",
                new String[]{Long.toString(id)});

    }

    public void deleteAllReminders() {
        mDatabase.delete(DBHelper.TABLE_REMINDERS, null, null);

    }


    public void updateReminder(Long id, String name) {
        ContentValues args = new ContentValues();
        args.put(DBHelper.REMINDER_NAME, name);
        mDatabase.update(DBHelper.TABLE_REMINDERS, args, DBHelper.REMINDER_ID + "=?",
                new String[]{Long.toString(id)});

    }


}
