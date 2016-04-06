package com.sword.myswordapplication.expandableView;

import android.os.Bundle;

/**
 * Created by test on 4/6/2016.
 */
public class SectionValues {
    private String mTitle;
    private String mDesc;
    private String sectionID;

    public static final String SECTITLE = "title";
    public static final String SECDESCRIPTION= "desc";
    public static final String SECID= "id";

    public SectionValues(){}
    public SectionValues(Bundle b){
        this.mTitle = b.getString(SECTITLE);
        this.mDesc = b.getString(SECDESCRIPTION);
        this.sectionID = b.getString(SECID);
    }
    public Bundle toBundle(){
        Bundle b = new Bundle();
        b.putString(SECTITLE , this.mTitle);
        b.putString(SECDESCRIPTION , this.mDesc);
        b.putString(SECID , this.sectionID);
        return b;
    }
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }
}
