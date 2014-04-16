package com.fshoot.framepage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.fshoot.framepage.BattlePage;
import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

public class TownPage implements FramePage {

	private Activity activity;

	@Override
	public void show(Activity activity, boolean isSaveToRam) {
		this.activity = activity;

		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_town, frame, true);

		// Save this FramePage into Ram
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList().add(this);
		}

		// battle button
		Button battle = (Button) (activity.findViewById(R.id.Button1));
		battle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new BattlePage().show((Activity) v.getContext(), true);
			}
		});
		// lucky draw button
		Button luckyDraw = (Button) (activity.findViewById(R.id.Button3));
		luckyDraw.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new LuckyDrawPage().show((Activity) v.getContext(), true);
			}
		});
		// Shop button
		Button shop = (Button) (activity.findViewById(R.id.Button2));
		shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 new ShopPage().show((Activity)v.getContext(),true);
			}
		});
		// Score button
		Button score = (Button) (activity.findViewById(R.id.button4));
		score.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new ScorePage().show((Activity)v.getContext(),true);
			}
		});

	}

}
