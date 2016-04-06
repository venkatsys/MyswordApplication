package com.sword.myswordapplication.expandableView;

import android.widget.ExpandableListView.OnGroupExpandListener;

public class ExpandableGroupExpand implements OnGroupExpandListener {

	private SwordMainAcitivity a;
	private int lastExpandedPosition = -1;

	public ExpandableGroupExpand(SwordMainAcitivity b) {
		this.a = b;
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
			this.a.mListView.collapseGroup(lastExpandedPosition);
		}
		lastExpandedPosition = groupPosition;
	}
}
