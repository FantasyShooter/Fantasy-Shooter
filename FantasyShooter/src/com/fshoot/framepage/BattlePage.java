package com.fshoot.framepage;

import java.util.ArrayList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Level;
import com.fshoot.entity.Monster;
import com.fshoot.entity.SmallM;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class BattlePage implements FramePage {

	private Activity activity;
	private ArrayList<Level> level_list;
	private Handler handler;

	public BattlePage() {
		level_list = new ArrayList<Level>();
		level_list.add(new Level(10, 0, 0));
		level_list.add(new Level(10, 15, 1));
		level_list.add(new Level(10, 15, 5));
	}

	@Override
	public void show(Activity activity) {
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity
				.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_battle, frame,
				true);

		// 2. setup event listener

		// 3. event start when created
		startNextDay();
	}

	public void startNextDay() {
		handler = new Handler();

		MyApp myapp = ((MyApp) activity.getApplicationContext());
		int survival_day = myapp.getPlayer().getSurvival_day();

		Level level = level_list.get(survival_day);

		while (level.hasMoreMonster()) {
			try {
				// int ran = (int)Math.random()*3;
				Monster monster = null;

				// if(ran==0){
				monster = new SmallM();
				level.deductSmallM();
				// }else if(ran==1){
				// monster = new MiddleM();
				//level.deductMiddleM();
				// }else if(ran==2){
				// monster = new BigM();
				//level.deductBigM();
				// }
				monster.create(activity);
				monster.startMove();

				// 0.25 - 1 seconds
				int waitTime = (int) Math.random() * 100 + 25;

				// handler.postDelayed(r, waitTime*10);
				
				Thread.currentThread().sleep(waitTime * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}