package com.fshoot.framepage;

import java.util.ArrayList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Level;
import com.fshoot.entity.Monster;
import com.fshoot.entity.SmallM;
import com.fshoot.main.MainActivity;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class BattlePage implements FramePage {

	private Activity activity;
	private Handler handler;

	@Override
	public void show(Activity activity, boolean isSaveToRam) {
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity
				.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_battle, frame,
				true);
		// Save this FramePage into Ram
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList()
					.add(this);
		}

		// Reset level data
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		ArrayList<Level> level_list = new ArrayList<Level>();
		level_list.add(new Level(1, 0, 0));
		level_list.add(new Level(2, 0, 0));
		level_list.add(new Level(3, 0, 0));
		myapp.setLevel_list(level_list);

		// 2. setup event listener

		// 3. event start when created
		startNextDay();
	}

	public void startNextDay() {
		Log.d("Debug", "startNextDay()");
		releaseMonster();
	}

	public void releaseMonster() {
		Log.d("debug", "releaseMonster()");
		handler = new Handler();

		MyApp myapp = ((MyApp) activity.getApplicationContext());
		int survival_day = myapp.getPlayer().getSurvival_day();
		Level level = myapp.getLevel_list().get(survival_day);
		level.deductSmallM();
		
		int delayTime =  ((int) (Math.random() * 100 + 25)) * 10;
		Log.d("delayTime",""+delayTime);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Monster monster = null;
				// int ran = (int)Math.random()*3;

				// if(ran==0){
				monster = new SmallM();

				// }else if(ran==1){
				// monster = new MiddleM();
				// level.deductMiddleM();
				// }else if(ran==2){
				// monster = new BigM();
				// level.deductBigM();
				// }
				monster.initial(activity);
				monster.moveToLeft();

				MyApp myapp = ((MyApp) activity.getApplicationContext());
				int survival_day = myapp.getPlayer().getSurvival_day();
				Level level = myapp.getLevel_list().get(survival_day);

				
				if (level.hasMoreMonster()) {
					releaseMonster();
				}
			}
		},delayTime);

	}
}