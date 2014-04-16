package com.fshoot.entity;

import android.app.Activity;

public interface Monster {

	
	static int rowY[] = {156,312,468};
	
	public void create(Activity activity);
	
	public void deductHP(Activity activity);
	
	public void startMove(Activity activity);
}
