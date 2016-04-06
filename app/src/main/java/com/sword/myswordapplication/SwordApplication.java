package com.sword.myswordapplication;

import android.app.Application;
import android.content.Context;

import com.sword.myswordapplication.contentprovider.SwordContentProvider;


/**
 * Created by test on 4/6/2016.
 */
public class SwordApplication extends Application {
    private static Context context;
    private SwordContentProvider dbContentProvider = null;

    @Override
    public void onCreate() {
        super.onCreate();
        SwordApplication.context = getApplicationContext();
        dbContentProvider = new SwordContentProvider();
    }

    public static Context getAppContext() {
        return SwordApplication.context;
    }
}
