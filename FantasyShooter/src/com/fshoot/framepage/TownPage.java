package com.fshoot.framepage;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fshoot.entity.Player;
import com.fshoot.framepage.BattlePage;
import com.example.fantasyshooter.R;
import com.fshoot.main.MainActivity;
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
		
		// background sound
		if(MainActivity.bgm!=null){
			MainActivity.bgm.stop();
		}
		MainActivity.bgm = new MediaPlayer();
		MainActivity.bgm = MediaPlayer.create(activity, R.raw.the_town_music);
		MainActivity.bgm.setLooping(true);
		MainActivity.bgm.start();
		
		// Show the Day
		TextView tv = (TextView) (activity.findViewById(R.id.textView1));
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		Player player;
		player = myapp.getPlayer();
		tv.setText("Day: "+player.getSurvival_day()+"\nHP: "+player.getHp()+"\nATK: "+player.getTotalAtk());

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

	}

}
