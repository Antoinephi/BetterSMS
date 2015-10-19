package com.bettersms.badippe.bettersms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bettersms.badippe.database.Message;
import com.bettersms.badippe.database.MessagingDAO;
import com.bettersms.badippe.database.MessagingDataSource;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private int id;
    private MessagingDAO messagingDAO;
    private MessagingDataSource messagingDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Creating database and DAO to access Messages
        id = 0;
        messagingDataSource = new MessagingDataSource(this.getApplicationContext());
        messagingDAO = new MessagingDAO(messagingDataSource);
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

    public AlertDialog showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Invalid phone number, 10 numbers only")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void sendMessage(View v)   {
        Button b = (Button) findViewById (v.getId());
        String send_date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        EditText text = (EditText) findViewById (R.id.messageField);
        EditText sendTo = (EditText) findViewById (R.id.contactSearchField);
        id = id + 1;

        Message m = new Message(id, send_date, sendTo.getText().toString(), text.getText().toString());

        messagingDAO.create(m);

        SmsManager sms = SmsManager.getDefault();

        // J'ai tenté un regex
        if ((sendTo.getText().toString().equals("")) || (sendTo.getText().toString().matches("/^((\\+|00)33\\s?|0)[1-5](\\s?\\d{2}){4}$/")))
        {
            showDialog();
        }

        // Pour éviter que ça plante si vide
        else
            sms.sendTextMessage (sendTo.getText().toString(), null, text.getText().toString(), null, null);


        text.setText("");
    }

    // Debug
    public void reviewMessages (View v)
    {
        for (Message message : messagingDAO.readAll())
        {
            System.out.println("Je suis le message n°" + message.getId());
        }
    }

    // Debug
    public void cleanLogs (View v)
    {
        for (Message message : messagingDAO.readAll())
        {
            System.out.println("Accessing message n°" + message.getId());

            messagingDAO.delete(message);
        }
    }
}