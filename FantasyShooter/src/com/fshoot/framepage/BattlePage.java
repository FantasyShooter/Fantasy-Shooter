package com.fshoot.framepage;

import java.util.ArrayList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Level;
import com.fshoot.entity.SmallM;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class BattlePage implements FramePage {

	private Activity activity;
	private ArrayList<Level> level_list;
	
	public BattlePage(){
		level_list = new ArrayList<Level>();
		level_list.add(new Level(10,5,0));
		level_list.add(new Level(10,15,1));
		level_list.add(new Level(10,15,5));
	}

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
		startNextDay();
	}

	public void startNextDay(){
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		int survival_day = myapp.getPlayer().getSurvival_day();
		
		Level level = level_list.get(survival_day);
		
		while(level.hasMoreMonster()){
			int ran = (int)Math.random()*3;
			
		}
		SmallM smallM = new SmallM();
		smallM.create(activity);
		smallM.startMove();
	}
}