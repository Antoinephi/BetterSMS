package com.bettersms.badippe.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by badache on 19/10/15.
 */
public class MessagingDAO extends DataAccessObject
{
    public static final String COL_SENDTIME = "SEND_TIME";
    public static final String COL_SENDTO = "SEND_TO";
    public static final String COL_TEXT = "MESSAGE_CONTENT";
    public static final String COL_ID = "ID";
    public static final String TABLE_NAME = "MESSAGES";


    public MessagingDAO(DataSource datasource) {
        super(datasource);
    }

    public synchronized Message create (Message m) {
        ContentValues values = new ContentValues();
        values.put(COL_SENDTIME, m.getSendingTime());
        values.put(COL_SENDTO, m.getSendingTo());
        values.put(COL_TEXT, m.getMessageContent());

        int id = (int) getDB().insert(TABLE_NAME, null, values);

        m.setId(id);

        return m;
    }

    public synchronized Message update(Message m)
    {
        ContentValues values = new ContentValues();
        values.put(COL_SENDTIME, m.getSendingTime());
        values.put(COL_SENDTO, m.getSendingTo());
        values.put(COL_TEXT, m.getMessageContent());

        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]  {   String.valueOf(m.getId())   };

        getDB().update(TABLE_NAME, values, clause, clauseArgs);

        return m;
    }

    public synchronized void delete (Message m)
    {
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]  {   String.valueOf(m.getId())   };

        getDB().delete(TABLE_NAME, clause, clauseArgs);
    }


    public Message read(Message m)
    {
        String[] allColumns = new String[]  {COL_ID, COL_SENDTIME, COL_SENDTO, COL_TEXT };
        String[] clauseArgs = new String[]  {   String.valueOf(m.getId())   };

        Cursor cursor = getDB().query(TABLE_NAME, allColumns, "ID = ?", clauseArgs, null, null, null);

        cursor.moveToFirst();
        m.setSendingTime(cursor.getString(1));
        m.setSendingTo(cursor.getString(2));
        m.setMessageContent(cursor.getString(3));
        cursor.close();

        return m;
    }

    public List<Message> readAll() {
        String[] allColumns = new String[]  {COL_ID, COL_SENDTIME, COL_SENDTO, COL_TEXT };
        Cursor cursor = getDB().query(TABLE_NAME, allColumns, null, null, null, null, null);

        List<Message> messages = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            messages.add(new Message (cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();

        return messages;
    }
}
