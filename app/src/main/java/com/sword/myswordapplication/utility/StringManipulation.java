package com.sword.myswordapplication.utility;

import android.database.Cursor;
import android.net.Uri;

import com.sword.myswordapplication.SwordApplication;
import com.sword.myswordapplication.contentprovider.DatabaseHelper;
import com.sword.myswordapplication.expandableView.SectionDatas;
import com.sword.myswordapplication.expandableView.SectionValues;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by test on 4/6/2016.
 */
public class StringManipulation {
    public static final String NEWS_TASK = "NEWS_TASK";
    public static List<SectionDatas> toReadCategory() {
        String[] myID = {"1","2","3"};
        String[] category = {"Morning","Afternoon","Evening"};
        List<SectionDatas> msectionDatas = new ArrayList<>();
        Uri todoUri = null;
        Cursor data =  null;
        for(int i = 0 ; i < 3 ; i++){
            SectionDatas sectionDatas = new SectionDatas();
            sectionDatas.setmTitle("Section " + category[i]);
            List<SectionValues> msectionValues = new ArrayList<>();
            todoUri = Uri.parse("content://com.swordfeed/sword/" + myID[i] + "/get");
            data = SwordApplication.getAppContext().getContentResolver().query(todoUri, null,
                    null, null, null);
            while(data.moveToNext()){
                int columnID = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONID);
                int title = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONTITLE);
                int description = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONDESCRIPTION);
                int author = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONAUTHOR);
                int date = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONDATE);
                int id = data.getInt(columnID);
                SectionValues sectionValues = new SectionValues();
                sectionValues.setmTitle(data.getString(title));
                sectionValues.setmDesc(data.getString(description));
                sectionValues.setSectionID(String.valueOf(id));
                msectionValues.add(sectionValues);
                sectionDatas.setmDesc(String.valueOf(data.getCount()));
            }
            sectionDatas.setSectionValues(msectionValues);
            msectionDatas.add(sectionDatas);
            data.close();
        }
        return msectionDatas;
    }

    public static int getTotalRecordsCount(){
        Uri todoUri = null;
        Cursor data =  null;
        todoUri = Uri.parse("content://com.swordfeed/sword/1/get");
        data = SwordApplication.getAppContext().getContentResolver().query(todoUri, null,
                null, null, null);
        return data.getCount();
    }
}
