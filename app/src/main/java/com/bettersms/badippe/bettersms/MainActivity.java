package com.bettersms.badippe.bettersms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bettersms.badippe.databasehelper.MessagingDBHelper;
import com.bettersms.badippe.entites.Message;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private int id;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if( extras != null){
            contact = new Contact(extras.getString("name"), extras.getString("phone"));
            setPhoneNumber(extras.getString("name"));
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

        return super.onOptionsItemSelected(item);
    }

    public void setPhoneNumber(String name){
        EditText sendTo = (EditText)findViewById(R.id.contactSearchField);
        sendTo.setText(name, TextView.BufferType.EDITABLE);
    }

    public void searchContact(View v){
        Intent intent = new Intent(this, ListViewLoader.class);
        startActivity(intent);
    }

    public void sendMessage(View v) {
        Button b = (Button) findViewById(v.getId());
        String send_date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        EditText text = (EditText) findViewById(R.id.messageField);
        EditText sendTo = (EditText) findViewById(R.id.contactSearchField);

        Message m = new Message(send_date, sendTo.getText().toString(), text.getText().toString());
        SmsManager sms = SmsManager.getDefault();

        if(!sendTo.getText().toString().equals("") && !text.getText().toString().equals("")) {
            sms.sendTextMessage(contact.getPhone(), null, text.getText().toString(), null, null);
            text.setText("");
        }
        MessagingDBHelper dbHelper = MessagingDBHelper.getHelper(this);

        try {
            dbHelper.getMessagesDao().create(m);


        } catch (SQLException e) {
            Log.e("MainActivity", "Fail create message : " + e);
        }
    }

    // Debug
    public void reviewMessages(View v) {
        MessagingDBHelper dbHelper = MessagingDBHelper.getHelper(this);

        try {
            List<Message> messages = dbHelper.getMessagesDao().queryForAll();

            for (Message message : messages) {

                Log.e("MainActivity", "Je suis le message n° " + message.getId());
            }
        } catch (SQLException e) {
            Log.e("MainActivity", "Fail getting message : " + e);
        }
    }

    // Debug
    public void cleanLogs(View v) {

        MessagingDBHelper dbHelper = MessagingDBHelper.getHelper(this);

        try {
            List<Message> messages = dbHelper.getMessagesDao().queryForAll();

            for (Message message : messages) {

                Log.e("MainActivity", "Accessing  message n° " + message.getId());
                dbHelper.getMessagesDao().delete(message);
            }
        } catch (SQLException e) {
            Log.e("MainActivity", "Fail getting message : " + e);
        }
    }
}