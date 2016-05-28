package com.example.fabrice.diary.persistence;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class Constants {

    public static final String DATABASE_NAME = "DiaryDb.db";
    public static final  String DIARY_TABLE_NAME = "diary";
    public static final  String DIARY_COLUMN_ID = "id";
    public static final  String DIARY_COLUMN_TITLE = "title";
    public static final  String DIARY_COLUMN_CONTENT = "content";
    public static final  String DIARY_COLUMN_DATE = "recordDate";
    public static final  String DIARY_ALL_COLUMNS = "SELECT * FROM " + DIARY_TABLE_NAME;
    public static final int DIARY_ID = 1;
    public static final int DIARY_LIST = 2;
    public static final String AUTHORITY = "com.example.fabrice.diary";
    public static final String PROVIDER_NAME = "com.example.fabrice.provider.DiaryContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/diaries";
    public static final Uri CONTENT_URL = Uri.parse(URL);
    public static final String[] DIARY_SUMMARY_COLUMNS = new String[]{
            DIARY_COLUMN_ID,
            DIARY_COLUMN_TITLE,
            DIARY_COLUMN_CONTENT,
            DIARY_COLUMN_DATE
    };


}
