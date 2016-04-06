package com.sword.myswordapplication.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by test on 4/6/2016.
 */
public class SwordContentProvider  extends ContentProvider {

    /**
     * Database Helper Instance Class
     */
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase dbDatabase;
    private SQLiteQueryBuilder queryBuilder = null;
    /**
     * DATABASE AUTHORITY
     */
    public static final String AUTHORITY = "com.swordfeed";

    /**
     * TO BUILD BASE URI
     */
    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * To add URL CODE to identify the path
     */
    private static final int SECTIONINSERT = 100;
    private static final int FEEDSECTION = 101;
    private static final int SECTIONDELETE = 102;
    private static final int SECTIONUPDATECODE=103;

    static {
        final UriMatcher matcher = uriMatcher;
        matcher.addURI(AUTHORITY, "sword/section/add", SECTIONINSERT);
        matcher.addURI(AUTHORITY, "sword/*/get", FEEDSECTION);
        matcher.addURI(AUTHORITY, "sword/section/delete", SECTIONDELETE);
        matcher.addURI(AUTHORITY, "sword/*/update", SECTIONUPDATECODE);
    }

    /**
     * field to Declare category ID
     */
    private String CategoryID;

    @Override
    public boolean onCreate() {
        this.dbHelper = new DatabaseHelper(getContext());
        this.dbDatabase = this.dbHelper.getWritableDatabase();
        return (this.dbHelper != null);
    }
    /**
     * To return db object
     */
    public synchronized SQLiteDatabase getDb() {
        return dbDatabase;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = uriMatcher.match(uri);
        Cursor cursor = null;
        queryBuilder = new SQLiteQueryBuilder();
        switch (uriType) {
            case FEEDSECTION:
                CategoryID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(DatabaseHelper.COLUMN_SECTIONSID + " = '" + CategoryID + "'");
                queryBuilder.setTables(DatabaseHelper.DONE_SWORD_SECTION);
                break;
        }
        cursor = queryBuilder.query(this.getDb(), projection, selection,
                selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues cv) {
        long id = 0L;
        switch (uriMatcher.match(uri)) {
            case SECTIONINSERT:
                id = this.getDb().insert(DatabaseHelper.DONE_SWORD_SECTION, null, cv);
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int row = 0;
        int matched = uriMatcher.match(uri);
        try {
            switch (uriMatcher.match(uri)) {
                case SECTIONDELETE:
                    this.getDb().execSQL("delete from " + DatabaseHelper.DONE_SWORD_SECTION);
                    break;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int row = 0;
        int matched = uriMatcher.match(uri);
        switch (uriMatcher.match(uri)){
            case SECTIONUPDATECODE:
                CategoryID = "";
                CategoryID = uri.getPathSegments().get(1);
                String where = DatabaseHelper.COLUMN_SECTIONID + " = '" + CategoryID + "'";
                row = getDb().update(DatabaseHelper.DONE_SWORD_SECTION, values, where, null);
                break;
        }
        return 0;
    }
}
