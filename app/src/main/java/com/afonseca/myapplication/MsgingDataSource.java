package com.afonseca.myapplication;


/**
 * Created by afonseca on 1/23/2015.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MsgingDataSource {


    // Database fields
    private SQLiteDatabase database;
    private BufferMsgingSQLiteHelper dbHelper;
    private String[] allColumns = { BufferMsgingSQLiteHelper.COLUMN_ID,
            BufferMsgingSQLiteHelper.COLUMN_USER_FROM,
            BufferMsgingSQLiteHelper.COLUMN_USER_TO,
            BufferMsgingSQLiteHelper.COLUMN_TIMESTAMP,
            BufferMsgingSQLiteHelper.COLUMN_STATUS,
            BufferMsgingSQLiteHelper.COLUMN_MESSAGE
            };

    public MsgingDataSource(Context context) {
        dbHelper = new BufferMsgingSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //public Comment createComment(String comment) {
    public Msg addMsg(String user_from, String user_to, String message) {
        Integer timestamp = 01012015;
        String status = "OK";

        ContentValues values = new ContentValues();
        values.put(BufferMsgingSQLiteHelper.COLUMN_USER_FROM, user_from);
        values.put(BufferMsgingSQLiteHelper.COLUMN_USER_TO, user_to);
        values.put(BufferMsgingSQLiteHelper.COLUMN_TIMESTAMP, timestamp);
        values.put(BufferMsgingSQLiteHelper.COLUMN_STATUS, status);
        values.put(BufferMsgingSQLiteHelper.COLUMN_MESSAGE, message);
        long insertId = database.insert(BufferMsgingSQLiteHelper.TABLE_MSGING_BUFFER, null,
                values);
        Cursor cursor = database.query(BufferMsgingSQLiteHelper.TABLE_MSGING_BUFFER,
                allColumns,
                BufferMsgingSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
            Msg newMsg = cursorToMsg(cursor);
        cursor.close();
        return newMsg;
    }

    public void deleteMsg(Msg msg) {
        long id = msg.getId();
        System.out.println("Message deleted with id: " + id);
        database.delete(BufferMsgingSQLiteHelper.TABLE_MSGING_BUFFER, BufferMsgingSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Msg> getAllMsgs() {
        List<Msg> msgs = new ArrayList<Msg>();

        Cursor cursor = database.query(BufferMsgingSQLiteHelper.TABLE_MSGING_BUFFER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Msg msg = cursorToMsg(cursor);
            msgs.add(msg);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return msgs;
    }

    private Msg cursorToMsg(Cursor cursor) {
        Msg msg = new Msg();
        msg.setId(cursor.getLong(0));
        msg.setUser_from(cursor.getString(1));
        msg.setUser_to(cursor.getString(2));
        msg.setTimestamp(Integer.parseInt(cursor.getString(3)));
        msg.setStatus(cursor.getString(4));
        msg.setMessage(cursor.getString(5));
        return msg;
    }
}

