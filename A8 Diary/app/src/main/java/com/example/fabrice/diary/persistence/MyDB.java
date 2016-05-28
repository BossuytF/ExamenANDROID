package com.example.fabrice.diary.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB {

    private static final String TAG = "MyDB.class";
    private SQLiteOpenHelper helper;
    private SQLiteDatabase myDb;


    public MyDB(Context context) {
        this.helper  = new MyDBHelper(context, Constants.DATABASE_NAME, null, 1);
    }

    public void close(){
        myDb.close();
    }

    public void open(){
        try{
            myDb = helper.getWritableDatabase();
        }catch(SQLiteException e){
            Log.e(TAG,"Could not create a writeable database. Readable database has been opened");
            Log.e(TAG,e.getMessage());
            myDb = helper.getReadableDatabase();
        }
    }


    public long insertDiary(ContentValues newDiaryValue){
        try{
            return myDb.insert(Constants.DIARY_TABLE_NAME, null,newDiaryValue);
        }catch (SQLiteException e){
            Log.e(TAG, "Inserting into database did not work");
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }

    public Cursor getDiaries(){
        return myDb.rawQuery(Constants.DIARY_ALL_COLUMNS, null);
    }
}
