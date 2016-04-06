package com.sword.myswordapplication;

import android.content.ContentValues;
import android.net.Uri;

import com.sword.myswordapplication.contentprovider.DatabaseHelper;
import com.sword.myswordapplication.utility.DisplayResults;


/**
 * Created by test on 4/6/2016.
 */
public class LocalUpdate implements Runnable {
    private ContentValues insertSectionContentValues = null;
    private Uri todoUri= null;
    private DisplayResults callback;

    public LocalUpdate(DisplayResults listener){
        this.callback = listener;
    }

    @Override
    public void run() {
        this.BulkInsert();
    }

    /**
     * Method to insert datas into the local database
     */
    private void BulkInsert() {
        this.insertSectionContentValues = new ContentValues();
        for(int i = 0 ; i < 5 ; i++) {
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONSID, "1");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONTITLE, "Section Title" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, "Morning" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONAUTHOR, "Sword Author");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDATE, String.valueOf(System.currentTimeMillis()));
            this.todoUri = Uri.parse("content://com.swordfeed/sword/section/add");
            SwordApplication.getAppContext().getContentResolver().insert(this.todoUri, insertSectionContentValues);
        }
        this.insertSectionContentValues.clear();
        this.insertSectionContentValues = new ContentValues();
        for(int i = 0 ; i < 10 ; i++) {
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONSID, "2");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONTITLE, "Section Title" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, "Afternoon" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONAUTHOR, "Sword Author");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDATE, String.valueOf(System.currentTimeMillis()));
            this.todoUri = Uri.parse("content://com.swordfeed/sword/section/add");
            SwordApplication.getAppContext().getContentResolver().insert(this.todoUri, insertSectionContentValues);
        }
        this.insertSectionContentValues.clear();
        this.insertSectionContentValues = new ContentValues();
        for(int i = 0 ; i < 15 ; i++) {
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONSID, "3");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONTITLE, "Section Title" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, "Evening" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONAUTHOR, "Sword Author");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDATE, String.valueOf(System.currentTimeMillis()));
            this.todoUri = Uri.parse("content://com.swordfeed/sword/section/add");
            SwordApplication.getAppContext().getContentResolver().insert(this.todoUri, insertSectionContentValues);
        }
        this.callback.onSuccess(true);
        this.todoUri = null;
        this.insertSectionContentValues.clear();
    }
}
