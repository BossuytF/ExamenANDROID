package com.example.fabrice.a9reddit.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;import java.lang.String;

public class RedditDB {

    public static final String TAG = RedditDB.class.getName();
    private SQLiteDatabase db;
    private final Context context;
    private final RedditDBHelper dbHelper;

    /**
     * Initialises this DB. Make sure the dbHelper object is also initialised.
     * @param context
     */
    public RedditDB(Context context) {
        this.context = context;
        dbHelper = new RedditDBHelper(context,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
    }

    public void close(){
        db.close();
    }

    public void open(){
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            Log.e(TAG,"Could not create a writeable database. Readable database has been opened");
            Log.e(TAG,e.getMessage());
            db = dbHelper.getReadableDatabase();
        }
    }


    public long insertSubreddit(ContentValues contentValue){
        try{
            return db.insert(Constants.TABLE_NAME_LISTINGS,null,contentValue);
        }catch(SQLiteException e){
            Log.e(TAG, "Inserting into database did not work");
            Log.e(TAG,e.getMessage());
            return -1;
        }
    }

    public void deleteAllElements(){
        try{
            db.execSQL("DELETE FROM "+Constants.TABLE_NAME_LISTINGS);
            Log.i("DBA","Removed alle elements");
        }catch(SQLiteException e){
            e.printStackTrace();
        }
    }

    public Cursor getSubRedditss(){
        Cursor c = db.query(Constants.TABLE_NAME_LISTINGS,null,null,null,null,null,null);
        return c;
    }
}
