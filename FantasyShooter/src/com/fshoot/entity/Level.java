package com.fshoot.entity;

import java.util.ArrayList;

import android.util.Log;

public class Level {

	private ArrayList<Integer> mList;

	public Level(int num_of_smallM, int num_of_middleM, int num_of_bigM) {
		super();
		mList = new ArrayList<Integer>();
		mList.add(num_of_smallM);
		mList.add(num_of_middleM);
		mList.add(num_of_bigM);
	}

	public boolean hasMoreMonster() {
		Log.d("hasMoreMonster", "s:" + mList.get(0) + " m:" + mList.get(1) + " b:" + mList.get(2));
		boolean result = false;

		if (mList.get(0) > 0 || mList.get(1) > 0 || mList.get(2) > 0) {
			result = true;
		}

		return result;
	}

	public void deductSmallM() {
		mList.set(0, mList.get(0)-1);
	}

	public void deductMiddleM() {
		mList.set(1, mList.get(1)-1);
	}

	public void deductBigM() {
		mList.set(2, mList.get(2)-1);
	}

}
