package com.bettersms.badippe.bettersms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

/**
 * Created by Antoine on 18/10/2015.
 */
public class ContactAdaptater  extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private String contactName;
    private String contactNumber;

    public ContactAdaptater(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
