package com.sword.myswordapplication.expandableView;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class ExpandableGroupClick implements OnGroupClickListener{
	
	private SwordMainAcitivity a;
	
	public ExpandableGroupClick(SwordMainAcitivity b){
		this.a = b;
	}
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		if (this.a.mListView.isGroupExpanded(groupPosition)) {
			this.a.mListView.collapseGroupWithAnimation(groupPosition);
        } else {
        	this.a.mListView.expandGroupWithAnimation(groupPosition);
        }
        return true;
	}

}
