package com.example.fabrice.a9reddit.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;import java.lang.Override;import java.lang.String;

public class RedditDBHelper extends SQLiteOpenHelper {

    public static final String TAG = RedditDBHelper.class.getName();

    private static final String CREATE_TABLE ="CREATE TABLE "+Constants.TABLE_NAME_LISTINGS + " ("+
            Constants.KEY_ID+" integer primary key autoincrement, "+
            Constants.LISTING_NAME + " text not null, "+
            Constants.THUMBNAIL + " text, " +
            Constants.URL + " text not null, UNIQUE("+Constants.LISTING_NAME+"));";

    public RedditDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }catch(SQLiteException e){
            Log.e(TAG," Create table exception");
            Log.e(TAG," "+ e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG," Upgrading from version "+oldVersion + " to "+newVersion +" and will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME_LISTINGS);
        onCreate(db);
    }
}
