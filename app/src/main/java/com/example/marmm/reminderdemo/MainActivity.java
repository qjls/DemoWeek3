package com.example.marmm.reminderdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String INTENT_DETAIL_ROW_NUMBER = "Row number";
    public static final String INTENT_DETAIL_REMINDER_TEXT = "Reminder text";
    public static final int REQUESTCODE = 2;
    private ListView mListView;
    private EditText mEditText;
    private ReminderCursorAdapter mAdapter;
    private Cursor mCursor;
    private DataSource mDataSource;
    private List<Reminder> mReminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.listView);
        mEditText = (EditText) findViewById(R.id.editText);

        mDataSource = new DataSource(this);
        mDataSource.open();

        //mReminders = new ArrayList<Reminder>();
        updateUI();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(INTENT_DETAIL_ROW_NUMBER, i);
                intent.putExtra(INTENT_DETAIL_REMINDER_TEXT, ((Reminder) adapterView.getItemAtPosition(i)).getmReminderText());
                startActivityForResult(intent, REQUESTCODE);

            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mReminders.remove(i);
                updateUI();
                return true;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDataSource.createReminder(new Reminder(mEditText.getText().toString()));
                updateUI();
                Snackbar.make(view, "Reminder added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void updateUI() {
        mCursor = mDataSource.getAllReminders();
        if (mAdapter == null) {
            mAdapter = new ReminderCursorAdapter(this, mCursor);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.changeCursor(mCursor);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_delete) {
            mReminders.clear();
            updateUI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                int positionOfReminder = data.getIntExtra(INTENT_DETAIL_ROW_NUMBER, -1);
                //-1 being the default value in case of failure. Can be used to detect an issue.

                Reminder updatedReminder = new Reminder(data.getStringExtra(INTENT_DETAIL_REMINDER_TEXT));
                // New timestamp: timestamp of update

                mReminders.set(positionOfReminder, updatedReminder);
                updateUI();
            }
        }

    }
}

