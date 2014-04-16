package com.fshoot.entity;

import android.app.Activity;
import android.view.View;

public interface Monster {

	
	static int rowY[] = {156,312,468};
	
	public void initial(Activity activity);
	
	public void deductHP(Activity activity);
	
	public void moveToLeft();
}
