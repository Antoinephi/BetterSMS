package com.bettersms.badippe.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bettersms.badippe.bettersms.R;
import com.bettersms.badippe.entites.Message;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Badache on 25/09/2015.
 */
public class MessagingDBHelper extends OrmLiteSqliteOpenHelper {

     private static final String DATABASE_NAME = "feedMe";
     private static final int DATABASE_VERSION = 1;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
     private Dao<Message, Long> MessageDao;

     private static MessagingDBHelper instance;

    public MessagingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                /**
                 * R.raw.ormlite_config is a reference to the ormlite_config.txt file in the
                 * /res/raw/ directory of this project
                 * */
                R.raw.ormlite_config);
    }


    public static synchronized MessagingDBHelper getHelper(Context context)
    {
        if (instance == null)
            instance = new MessagingDBHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates the database tables
             */
            TableUtils.createTable(connectionSource, Message.class);


        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Echec de la création de la database" +e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */

            TableUtils.dropTable(connectionSource, Message.class, false);


            onCreate(database, connectionSource);


        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Echec de la mise à jour de la database");
        }
    }

    /**
     * Returns an instance of the data access object
     *
     * @return
     * @throws SQLException
     */

    //Getting Message
    public Dao<Message, Long> getMessagesDao() throws SQLException {
        if (MessageDao == null) {
            MessageDao = getDao(Message.class);
        }
        return MessageDao;
    }


}

