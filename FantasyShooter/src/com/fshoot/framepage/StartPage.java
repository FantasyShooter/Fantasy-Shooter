package com.fshoot.framepage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;

import com.example.fantasyshooter.R;

public class StartPage implements FramePage{
	
	@Override
	public void show(Activity activity) {
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_start, frame,true);
		
		// 2. setup event listener
		
		// 3. event start when created
		//create data base
		//start button
		Button start = (Button) (activity.findViewById(R.id.button1));
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity)v.getContext());
            }
		});
		//score button
		Button score = (Button) (activity.findViewById(R.id.button2));
		score.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//new ScorePage().show((Activity)v.getContext());
            }
		});
		
	}

}
