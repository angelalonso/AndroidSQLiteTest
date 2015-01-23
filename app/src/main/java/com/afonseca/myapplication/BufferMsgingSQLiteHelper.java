package com.afonseca.myapplication;

/**
 * Created by afonseca on 1/23/2015.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BufferMsgingSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_MSGING_BUFFER = "msging_buffer";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER_FROM = "user_from";
    public static final String COLUMN_USER_TO = "user_to";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_MESSAGE = "message";

    private static final String DATABASE_NAME = "msging.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MSGING_BUFFER + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_USER_FROM + " text not null, "
            + COLUMN_USER_TO + " text not null, "
            // TIMESTAMP is stored as "Seconds since Epoch"
            // THanks to http://stackoverflow.com/questions/6757940/convert-epoch-time-to-date-and-date-to-epoch-time-in-android
            // Get epoch time:
            //DateTime dateTimeInUtc = new DateTime( "2011-07-19T18:23:20+0000", DateTimeZone.UTC );
            //long secondsSinceUnixEpoch = ( dateTimeInUtc.getMillis() / 1000 ); // Convert milliseconds to seconds.
            // Convert epoch  to String
            // String dateTimeAsString = new DateTime( secondsSinceUnixEpoch * 1000, DateTimeZone.UTC ).toString();
            + COLUMN_TIMESTAMP + " int not null, "
            + COLUMN_STATUS + " text not null, "
            + COLUMN_MESSAGE + " text not null);";

    public BufferMsgingSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSGING_BUFFER);
        onCreate(db);
    }

}

