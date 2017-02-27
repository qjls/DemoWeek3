package com.example.marmm.reminderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {


    private EditText mReminderView;
    private Long mPosInArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mReminderView = (EditText) findViewById(R.id.detail_et);

        mReminderView.setText(getIntent().getStringExtra(MainActivity.INTENT_DETAIL_REMINDER_TEXT));
        mPosInArray = getIntent().getLongExtra(MainActivity.INTENT_DETAIL_ROW_NUMBER, -1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.putExtra(MainActivity.INTENT_DETAIL_ROW_NUMBER, mPosInArray);
                intent.putExtra(MainActivity.INTENT_DETAIL_REMINDER_TEXT, mReminderView.getText().toString());
                setResult(RESULT_OK, intent);
                finish();


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
