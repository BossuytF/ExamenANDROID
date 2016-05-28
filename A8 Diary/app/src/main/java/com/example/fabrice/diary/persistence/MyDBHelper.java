package com.example.fabrice.diary.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper{
    private static final String TAG =" MyDBHelper.class";


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE " +
                            Constants.DIARY_TABLE_NAME + "(" +
                            Constants.DIARY_COLUMN_ID + " integer primary key autoincrement, " +
                            Constants.DIARY_COLUMN_TITLE + " text not null," +
                            Constants.DIARY_COLUMN_CONTENT + " text not null," +
                            Constants.DIARY_COLUMN_DATE + " String)"
            );
        }catch (SQLiteException e){
            Log.e(TAG," Create table exception");
            Log.e(TAG," "+ e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DIARY_TABLE_NAME);
        onCreate(db);
    }
}
