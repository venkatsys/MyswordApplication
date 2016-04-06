package com.sword.myswordapplication.expandableView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.sword.myswordapplication.LocalUpdate;
import com.sword.myswordapplication.R;
import com.sword.myswordapplication.SectionDetailActivity;
import com.sword.myswordapplication.SectionDetailFragment;
import com.sword.myswordapplication.utility.DisplayResults;
import com.sword.myswordapplication.utility.StringManipulation;

import java.util.List;


/**
 * Created by test on 4/6/2016.
 */
public class SwordMainAcitivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{
    private LocalUpdate localUpdateff = null;
    private Uri todoUri = null;
    private Cursor SelectedSections = null;
    private List<SectionDatas> myFeedList;
    public AnimatedExpandableListView mListView;
    private ReceiptAdapter mAdapter;
    private boolean isTwoPane;
    private int pos = 0;

    public SwordMainAcitivity(){
        this.localUpdateff = new LocalUpdate(GetDisplay());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_layout);
        this.mListView = (AnimatedExpandableListView) this.findViewById(R.id.apppexpandableview);
        if(this.findViewById(R.id.expand_container) != null) {
            this.isTwoPane = true;
        }
        if(StringManipulation.getTotalRecordsCount() <=0) {
            toInsertRecords();
        }else{
            DisplayRecords();
        }
    }

    /**
     * Method to Insert Records into local DB
     */
    private void toInsertRecords() {
        this.localUpdateff.run();
    }

    /**
     * Method to Return Callbacks to Display the results
     */
    private DisplayResults GetDisplay(){
        DisplayResults displayResults = new DisplayResults() {
            @Override
            public void onSuccess(boolean status) {
                DisplayRecords();
            }
        };
        return displayResults;
    }

    /**
     * Method to Dsiplay Records
     */
    private void DisplayRecords() {
        this.myFeedList = StringManipulation.toReadCategory();
        this.mAdapter = new ReceiptAdapter(this,myFeedList);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnGroupClickListener(new ExpandableGroupClick(this));
        this.mListView.setOnGroupExpandListener((new ExpandableGroupExpand(this)));
        this.mListView.setOnChildClickListener(this);
        this.mListView.expandGroup(0);
        if(pos == 0){
            toSetRecord();
        }
        pos++;
    }

    /**
     * Method to set Record
     */
    private void toSetRecord() {
        if(this.isTwoPane) {
            SectionValues msectionValues = (SectionValues) myFeedList.get(0).getSectionValues().get(0);
            Bundle b = msectionValues.toBundle();
            SectionDetailFragment fragment = new SectionDetailFragment();
            fragment.setArguments(b);
            FragmentManager mfragmentManager = getSupportFragmentManager();
            mfragmentManager.beginTransaction().replace(R.id.expand_container, fragment).commit();
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        SectionValues msectionValues = (SectionValues) myFeedList.get(groupPosition).getSectionValues().get(childPosition);
        Bundle b = msectionValues.toBundle();
        Log.i("childclick", "click" + b);
        if(this.isTwoPane){
            SectionDetailFragment fragment = new SectionDetailFragment();
            fragment.setArguments(b);
            FragmentManager mfragmentManager= getSupportFragmentManager();
            mfragmentManager.beginTransaction().replace(R.id.expand_container,fragment).commit();
        }else{
            Intent myintent = new Intent(this , SectionDetailActivity.class);
            myintent.putExtra(StringManipulation.NEWS_TASK, b);
            startActivity(myintent);
        }
        Log.i("childclick", "click" + msectionValues.getmTitle() + msectionValues.getmDesc());
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        pos = 0;
    }
}
