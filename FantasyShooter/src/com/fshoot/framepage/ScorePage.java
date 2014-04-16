package com.fshoot.framepage;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Player;
import com.fshoot.main.MyApp;
import com.fshoot.main.PlayerDBHelper;

public class ScorePage implements FramePage {
	private Activity activity;

	// 1. GUI
	public void show(Activity activity, boolean isSaveToRam) {
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_score, frame, true);
		// Save this FramePage into Ram
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList().add(this);
		}

		// 2. setup event listener

		// 3. event start when created

		// ask db to get the player score
		Player player;
		PlayerDBHelper db = new PlayerDBHelper(activity);
		db.createTablePlayerIfNotExists(db.getWritableDatabase());
		ArrayList<Player> player_list = db.get_Players();
		for (int i = 0; i < player_list.size(); i++) {
			String score = player_list.get(i).getNick_name()
					+ player_list.get(i).getScore()
					+ player_list.get(i).getSurvival_day() + "\n";
			TextView textView1 = (TextView) (activity.findViewById(R.id.textView1));
			textView1.setText(score);
		}

	}
}
