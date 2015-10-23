package com.bettersms.badippe.bettersms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void searchContact(View v){
        Intent intent = new Intent(this, ListViewLoader.class);
        startActivity(intent);
    }

    public void changeText(View v)   {
        Button b = (Button)findViewById(v.getId());
        EditText text = (EditText)findViewById(R.id.editText);
        EditText sendTo = (EditText)findViewById(R.id.contactSearchBar);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(sendTo.getText().toString(), null, text.getText().toString(), null, null);
        text.setText("");

    }
}