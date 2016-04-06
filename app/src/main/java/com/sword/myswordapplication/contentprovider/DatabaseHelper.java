package com.sword.myswordapplication.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by test on 4/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * Database Name
     */
    public static String DATABASENAME = "sword.db";
    public static int DATABASEVERSION = 1;

    /**
     * Table Definition for Section Home
     */
    public static final String DONE_SWORD_SECTION = "swordSsection";
    public static final String COLUMN_SECTIONID = "_id";
    public static final String COLUMN_SECTIONSID = "cid";
    public static final String COLUMN_SECTIONTITLE = "title";
    public static final String COLUMN_SECTIONLINK = "link";
    public static final String COLUMN_SECTIONDESCRIPTION = "description";
    public static final String COLUMN_SECTIONAUTHOR = "author";
    public static final String COLUMN_SECTIONDATE = "pubdate";

    /**
     * Create Table for Sections Definition and Declaration
     */
    private static final String TABLE_CREATESECTION = "CREATE TABLE "
            + DONE_SWORD_SECTION + "(" + COLUMN_SECTIONID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_SECTIONSID
            + " TEXT NULL, " + COLUMN_SECTIONTITLE + " TEXT NULL, "
            + COLUMN_SECTIONDESCRIPTION + " TEXT NULL, " + COLUMN_SECTIONLINK
            + " TEXT NULL, " + COLUMN_SECTIONAUTHOR + " TEXT NULL, "
            + COLUMN_SECTIONDATE + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ddddddddddd", "onCreate>>>>");
        db.execSQL(TABLE_CREATESECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ddddddddddd","onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CREATESECTION);
        onCreate(db);
    }
}
