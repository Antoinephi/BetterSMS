package com.bettersms.badippe.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by badache on 19/10/15.
 */
public interface DataSource {

    public SQLiteDatabase getDB();
    public void open();
    public void close();
}
