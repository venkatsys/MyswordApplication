package com.sword.myswordapplication;

import android.app.Application;
import android.content.ContentValues;
import android.net.Uri;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.sword.myswordapplication.contentprovider.DatabaseHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TEST_SECTION_PREFIX = "test_";
    private DatabaseHelper databaseHelper;
    private ContentValues insertSectionContentValues = null;
    private Uri todoUri= null;
    private RenamingDelegatingContext _mcontext = null;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _mcontext = new RenamingDelegatingContext(getContext(), TEST_SECTION_PREFIX);
        databaseHelper = new DatabaseHelper(_mcontext);
    }

    @Override
    public void tearDown() throws Exception {
        databaseHelper.close();
        super.tearDown();
    }

    public void testAddEntry(){
        this.insertSectionContentValues = new ContentValues();
        for(int i = 0 ; i < 5 ; i++) {
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONSID, "1");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONTITLE, "Section Title" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, "Morning" + i);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONAUTHOR, "Sword Author");
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDATE, String.valueOf(System.currentTimeMillis()));
            this.todoUri = Uri.parse("content://com.swordfeed/sword/section/add");
            _mcontext.getContentResolver().insert(this.todoUri, insertSectionContentValues);
        }
        Log.i("database","created");
    }
}