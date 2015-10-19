package com.bettersms.badippe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by badache on 19/10/15.
 */
public class MessagingDBHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "MessagingLog.db";
    public static final int DB_VERSION = 1;

    // Constructor, needed by SQLiteOpenHelper abstract class
    public MessagingDBHelper (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static String getQueryCreate ()  {
        return "CREATE TABLE MESSAGES ("
                + "ID Integer PRIMARY KEY AUTOINCREMENT, "
                + "SEND_TIME Text NOT NULL, "
                + "SEND_TO Text NOT NULL, "
                + "MESSAGE_CONTENT Text NOT NULL "
                + ");";
    }

    public static String getQueryDrop ()    {
        return "DROP TABLE IF EXISTS Message";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getQueryCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getQueryDrop());
        db.execSQL(getQueryCreate());
    }
}
