package com.bettersms.badippe.bettersms;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by philippe on 28/09/15.
 */
public class ListViewLoader extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {



    ArrayList<Contact> contactList;
    // This is the Adapter being used to display the list's data
    ContactAdapter mAdapter;

    // These are the Contacts rows that we will retrieve
    static final String[] PROJECTION = new String[] {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

    // This is the select criteria
    static final String SELECTION = "((" +
            ContactsContract.Contacts.IN_VISIBLE_GROUP + " = 1) AND (" +
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " NOTNULL) AND (" +
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY + " != '' ) AND (" +
            ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER + " != 0))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.list_contact_activity);
        contactList = new ArrayList<Contact>();


        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        mAdapter = new ContactAdapter(this, R.layout.item_contact, contactList);

        getLoaderManager().initLoader(0, null, this);
        setListAdapter(mAdapter);
    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.

        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI ,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)

        while(data.moveToNext()){
            mAdapter.add(new Contact(data.getString(1), data.getString(2)));
        }

    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
       mAdapter.clear();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
        super.onListItemClick(l, v, position, id);
        Contact c = mAdapter.getItem(position);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", c.getName());
        intent.putExtra("phone", c.getPhone());
        startActivity(intent);

    }
}
