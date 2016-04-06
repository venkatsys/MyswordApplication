package com.sword.myswordapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sword.myswordapplication.contentprovider.DatabaseHelper;
import com.sword.myswordapplication.expandableView.SectionValues;
import com.sword.myswordapplication.expandableView.SwordMainAcitivity;

/**
 * Created by Venkat on 06-04-2016.
 */
public class SectionDetailFragment extends Fragment implements View.OnClickListener {
    public SectionDetailFragment(){}
    private SectionValues msectionValues;
    private TextView sectionName, sectiondesc;
    private Button updateButton;
    private Uri todoUri = null;
    private String mtitle , mDesc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        this.msectionValues = new SectionValues(b);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_detail , container , false);
        if(this.msectionValues != null){
            sectionName = (TextView) view.findViewById(R.id.sectionName);
            sectiondesc = (TextView) view.findViewById(R.id.sectionDesc);
            sectionName.setText(this.msectionValues.getmTitle());
            sectiondesc.setText(this.msectionValues.getmDesc());
            mtitle = this.msectionValues.getmTitle();
            mDesc = this.msectionValues.getmDesc();

        }
        this.updateButton = (Button) view.findViewById(R.id.button);
        this.updateButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        toUpdateDB();
    }

    /**
     * Method to Update Database
     */
    private void toUpdateDB() {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_SECTIONTITLE, (this.sectionName.getText().toString().isEmpty() ? mtitle : this.sectionName.getText().toString()));
        cv.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, (this.sectiondesc.getText().toString().isEmpty() ? mDesc : this.sectiondesc.getText().toString()));
        todoUri = Uri.parse("content://com.swordfeed/sword/" + msectionValues.getSectionID() + "/update");
        SwordApplication.getAppContext().getContentResolver().update(todoUri, cv, null, null);
        Intent intent = new Intent(getActivity(), SwordMainAcitivity.class);
        startActivity(intent);
    }
}
