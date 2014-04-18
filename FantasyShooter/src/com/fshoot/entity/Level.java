package com.fshoot.entity;

import java.util.ArrayList;

import android.util.Log;

public class Level {

	private ArrayList<MonsterNumberPair> mList;

	public Level(int num_of_smallM, int num_of_middleM, int num_of_bigM) {
		mList = new ArrayList<MonsterNumberPair>();
		if (num_of_smallM > 0)
			mList.add(new MonsterNumberPair("small", num_of_smallM));
		if (num_of_middleM > 0)
			mList.add(new MonsterNumberPair("middle", num_of_middleM));
		if (num_of_bigM > 0)
			mList.add(new MonsterNumberPair("big", num_of_bigM));
	}

	public ArrayList<MonsterNumberPair> getmList() {
		return mList;
	}

	public void setmList(ArrayList<MonsterNumberPair> mList) {
		this.mList = mList;
	}

	public boolean hasMoreMonster() {
		for (int i = 0; i < mList.size(); i++) {
			Log.d(mList.get(i).monsterName, "" + mList.get(i).numberOfMonster);
			if (mList.get(i).numberOfMonster > 0) {
				return true;
			}
		}

		return false;
	}

}
