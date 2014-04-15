package com.fshoot.framepage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import com.fshoot.framepage.BattlePage;
import com.example.fantasyshooter.R;

public class MenuPage implements FramePage{
	
	private Activity activity;
	
	@Override
	public void show(Activity activity) {
		this.activity = activity;
		
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_menu, frame,true);
		//battle button
		ImageButton battle = (ImageButton) (activity.findViewById(R.id.imageButton1));
		battle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity)v.getContext());
            }
		});
		//player button
		ImageButton player = (ImageButton) (activity.findViewById(R.id.imageButton2));
		player.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity)v.getContext());
            }
		});


		
	}

}
