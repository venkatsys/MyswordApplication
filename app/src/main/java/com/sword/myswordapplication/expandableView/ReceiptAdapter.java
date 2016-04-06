package com.sword.myswordapplication.expandableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sword.myswordapplication.R;

import java.util.List;



/**
 * Created by test on 4/6/2016.
 */
public class ReceiptAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;
    private List<SectionDatas> msectionDatas;
    private List<SectionValues> msectionValues;

    public ReceiptAdapter(Context context, List<SectionDatas> msectionDatas) {
        super();
       this.inflater = LayoutInflater.from(context);
        this.msectionDatas = msectionDatas;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SectionValues msectionValues = (SectionValues) msectionDatas.get(groupPosition).getSectionValues().get(childPosition);
        getChild(groupPosition, childPosition);
        ChildViewHolder cvh;
        if(null == convertView){
            cvh = new ChildViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_child, null);
            cvh.childName = (TextView) convertView.findViewById(R.id.fChildCourierName);
            convertView.setTag(cvh);
        }else{
            cvh = (ChildViewHolder) convertView.getTag();
        }
        cvh.childName.setText(msectionValues.getmTitle());
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return this.msectionDatas.get(groupPosition).getSectionValues().size();
    }

    @Override
    public int getGroupCount() {
        return this.msectionDatas.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.msectionDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.msectionDatas.get(groupPosition).getSectionValues().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final SectionDatas sectionResponse =  (SectionDatas) getGroup(groupPosition);
        HeaderViewHolder hvh;
        if(null == convertView) {
            hvh = new HeaderViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_group, null);
            hvh.courierName = (TextView) convertView.findViewById(R.id.fcourierName);
            hvh.courierCount = (TextView) convertView.findViewById(R.id.fcourierCount);
            convertView.setTag(hvh);
        }else{
            hvh = (HeaderViewHolder) convertView.getTag();
        }
        hvh.courierName.setText(sectionResponse.getmTitle());
        hvh.courierCount.setText(sectionResponse.getmDesc());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class HeaderViewHolder{
        TextView courierName,courierCount;
    }

    class ChildViewHolder{
        TextView childName;
    }
}
