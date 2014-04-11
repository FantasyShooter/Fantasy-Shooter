package com.fshoot.framepage;

import com.example.fantasyshooter.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class BattlePage implements FramePage {

	private Activity activity;

	@Override
	public void show(Activity activity) {
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_battle, frame,true);
		
		// 2. setup event listener
		
		// 3. event start when created
		startGame();
	}

	public void startGame(){
		
	}
}
