package com.sword.myswordapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sword.myswordapplication.utility.StringManipulation;

/**
 * Created by Venkat on 06-04-2016.
 */
public class SectionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_detail);
        if(savedInstanceState == null) {
            SectionDetailFragment fragment = new SectionDetailFragment();
            Bundle b = getIntent().getBundleExtra(StringManipulation.NEWS_TASK);
            fragment.setArguments(b);
            Log.i("SectionDetailActivity", "click" + b);
            FragmentManager mfragmentManager= getSupportFragmentManager();
            mfragmentManager.beginTransaction().replace(R.id.detailContainer,fragment).commit();
        }
    }
}
