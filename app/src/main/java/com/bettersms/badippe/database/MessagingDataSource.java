package com.bettersms.badippe.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by badache on 19/10/15.
 */
public class MessagingDataSource implements DataSource {

    private final MessagingDBHelper helper;
    private SQLiteDatabase db;

    public MessagingDataSource (Context context)
    {
        helper = new MessagingDBHelper(context);
    }

    public SQLiteDatabase getDB()
    {
        if (db == null)
            open();
        return db;
    }

    public void open() throws SQLException
    {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }
}
