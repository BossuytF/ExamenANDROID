package com.example.fabrice.a9reddit.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;import java.lang.IllegalArgumentException;import java.lang.Override;import java.lang.String;

public class RedditContentProvider extends ContentProvider{

    public static final String TAG = RedditContentProvider.class.getName();

    //Properties
    private RedditDB dba;
    private static final UriMatcher uriMatcher;
    private static final int SUBREDDITS = 1;

    public static final String AUTHORITY = "com.example.fabrice.a9reddit";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + Constants.TABLE_NAME_LISTINGS);
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Constants.TABLE_NAME_LISTINGS, SUBREDDITS);
    }

    @Override
    public boolean onCreate() {
        dba = new RedditDB(this.getContext());
        dba.open();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch(uriMatcher.match(uri)){
            case SUBREDDITS:
                c = dba.getSubRedditss();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        dba.insertSubreddit(values);
        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        dba.deleteAllElements();
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
