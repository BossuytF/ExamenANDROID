package com.example.fabrice.diary.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class MyDBHelper extends SQLiteOpenHelper{
    private static final String TAG =" MyDBHelper.class";


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name, null, 1);
    }

    /**
     * Generates the tables in the  de SQLiteDatabase
     * @param db The SQLIteDatabase to add the tables to
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" +
                Constants.DIARY_TABLE_NAME + "(" +
                Constants.DIARY_COLUMN_ID + " integer primary key autoincrement, " +
                Constants.DIARY_COLUMN_TITLE + " text not null," +
                Constants.DIARY_COLUMN_CONTENT + " text not null," +
                Constants.DIARY_COLUMN_DATE + " String)"
        );
    }

    /**
     * Performs an upgrade of the database in case of version mismatch.
     * Implementation here is to drop all the tables and generate new tables.
     * For actual implementations this should perform a clean data migration
     * @param db The database to update
     * @param oldVersion The old version of the database
     * @param newVersion The new version to perform the update for
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DIARY_TABLE_NAME);
        onCreate(db);
    }
}
