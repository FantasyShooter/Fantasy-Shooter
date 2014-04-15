package com.fshoot.framepage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.fshoot.framepage.BattlePage;
import com.example.fantasyshooter.R;

public class TownPage implements FramePage{
	
	private Activity activity;

	@Override
	public void show(Activity activity) {
		this.activity = activity;
		
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_town, frame,true);
		//battle button
		Button battle = (Button) (activity.findViewById(R.id.Button1));
		battle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity)v.getContext());
            }
		});
		//player button
		Button player = (Button) (activity.findViewById(R.id.Button2));
		player.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity)v.getContext());
            }
		});


		
	}

}