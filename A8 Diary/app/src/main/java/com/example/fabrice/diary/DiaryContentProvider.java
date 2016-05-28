package com.example.fabrice.diary;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.fabrice.diary.persistence.Constants;
import com.example.fabrice.diary.persistence.MyDB;

import java.sql.SQLException;

public class DiaryContentProvider extends ContentProvider {

    private MyDB db;


    public static final  UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI("com.example.fabrice.provider.DiaryContentProvider", "diaries", Constants.DIARY_LIST);
        URI_MATCHER.addURI("diary", "diaries/#", Constants.DIARY_ID);
    }

    @Override
    public boolean onCreate() {
       db = new MyDB(this.getContext());
        db.open();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch(URI_MATCHER.match(uri)){
            case Constants.DIARY_LIST: {
                Cursor c = db.getDiaries();
                c.setNotificationUri(getContext().getContentResolver(),uri);
                return c;
            }
           default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        switch(URI_MATCHER.match(uri)){
            case Constants.DIARY_LIST: {
                return "vnd.android.cursor.dir/diary";
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = db.insertDiary(values);
        if (rowId > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
