package com.fshoot.framepage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

public class ShopPage implements FramePage {
	
	private Activity activity;
	// 1. GUI
	public void show(Activity activity, boolean isSaveToRam) {
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_shop, frame,true);
		
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList().add(this);
		}
		
		// 2. setup event listener
		
		// 3. event start when created
	}

}
