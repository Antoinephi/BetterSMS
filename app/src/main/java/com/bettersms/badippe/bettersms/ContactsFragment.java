package com.bettersms.badippe.bettersms;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by philippe on 24/09/15.
 */
public class ContactsFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.article_view, container, false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
