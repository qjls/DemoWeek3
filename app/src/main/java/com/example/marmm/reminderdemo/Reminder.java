package com.example.marmm.reminderdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marmm on 2/6/17.
 */

public class Reminder {

private String mReminderText;
    private String mReminderTimeStamp;


    public Reminder(String mReminderText) {
        this.mReminderText = mReminderText;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss ");
        this.mReminderTimeStamp = sdf.format(new Date());

    }

    public String getmReminderText() {
        return mReminderText;
    }

    public void setmReminderText(String mReminderText) {
        this.mReminderText = mReminderText;
    }

    public String getmReminderTimeStamp() {
        return mReminderTimeStamp;
    }

    public void setmReminderTimeStamp(String mReminderTimeStamp) {
        this.mReminderTimeStamp = mReminderTimeStamp;
    }

    @Override
    public String toString() {
        return  mReminderText;
    }


}
