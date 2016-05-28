package com.example.fabrice.diary.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class MyDB {

    private static final String TAG = "MyDB.class";
    private SQLiteOpenHelper helper;
    private SQLiteDatabase myDb;

    /**
     * Initialises this DB. Make sure the dbHelper object is also initialised.
     * @param context
     */
    public MyDB(Context context) {
        this.helper  = new MyDBHelper(context, Constants.DATABASE_NAME, null, 1);
    }

    /**
     * Closes the database
     */
    public void close(){
        helper.close();
    }
    /**
     * Tries to open a writable database. If this is not possible, open a readable database.
     * Log the necessary messages for debugging purposes.
     */
    public void open(){
        myDb = helper.getWritableDatabase();

        if (myDb == null){
            myDb = helper.getReadableDatabase();
        }
    }

    /**
     * Adds an diary entry to the database.
     * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert%28java.lang.String,%20java.lang.String,%20android.content.ContentValues%29
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insertDiary(ContentValues newDiaryValue){
        if (myDb == null){
            open();
        }
        return myDb.insert(Constants.DIARY_TABLE_NAME, null,newDiaryValue);
    }

    /**
     * Fetches all diary entries from the database
     * @return The diary entries from the database.
     */
    public Cursor getDiaries(){
        if(myDb == null){
            open();
        }
        return myDb.rawQuery(Constants.DIARY_ALL_COLUMNS, null);
    }
}
