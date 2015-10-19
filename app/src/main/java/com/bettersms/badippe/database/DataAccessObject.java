package com.bettersms.badippe.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by badache on 19/10/15.
 */
public abstract class DataAccessObject {

    private final DataSource datasource;

    public DataAccessObject(DataSource datasource)
    {
        this.datasource = datasource;
    }

    public SQLiteDatabase getDB()
    {
        return datasource.getDB();
    }
}
