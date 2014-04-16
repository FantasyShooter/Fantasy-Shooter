package com.fshoot.framepage;

import java.util.ArrayList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Level;
import com.fshoot.entity.Monster;
import com.fshoot.entity.SmallM;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class BattlePage implements FramePage {

	private Activity activity;
	private Handler handler;

	@Override
	public void show(Activity activity, boolean isSaveToRam) {
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_battle, frame, true);
		// Save this FramePage into Ram
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList().add(this);
		}

		// Reset level data
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		ArrayList<Level> level_list = new ArrayList<Level>();
		level_list.add(new Level(10, 0, 0));
		level_list.add(new Level(10, 15, 1));
		level_list.add(new Level(10, 15, 5));
		myapp.setLevel_list(level_list);

		// 2. setup event listener

		// 3. event start when created
		startNextDay();
	}

	public void startNextDay() {
		Log.d("Debug", "startNextDay()");
		handler = new Handler();

		MyApp myapp = ((MyApp) activity.getApplicationContext());
		int survival_day = myapp.getPlayer().getSurvival_day();
		Level level = myapp.getLevel_list().get(survival_day);

		Handler handler = new Handler();

		while (level.hasMoreMonster()) {
			handler.postDelayed(new PrepareMonster(activity),
					((int) Math.random() * 100 + 25) * 10); // 0.25 - 1 seconds

			level.deductSmallM();
			// level_list.set(survival_day, level);
		}
	}

}

class PrepareMonster extends Thread {

	private Activity activity;

	public PrepareMonster(Activity activity) {
		this.activity = activity;
	}

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
		monster.create(activity);
		monster.startMove(activity);
	}
}