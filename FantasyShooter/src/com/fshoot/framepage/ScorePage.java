package com.fshoot.framepage;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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
		PlayerDBHelper db = new PlayerDBHelper(activity);
		db.createTablePlayerIfNotExists(db.getWritableDatabase());
		ArrayList<Player> player_list = db.get_Players();
	    TableLayout ll = (TableLayout) (activity.findViewById(R.id.displayLinear));


	    for (int i = 0; i <player_list.size(); i++) {

	        TableRow row= new TableRow(activity);
	        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
	        row.setLayoutParams(lp);
	        TableRow.LayoutParams tlp1 = new TableRow.LayoutParams(100,50);
	        
	        TextView tv = new TextView(activity);tv.setGravity(Gravity.CENTER);
	        TextView qty = new TextView(activity);qty.setGravity(Gravity.CENTER);
	        TextView day = new TextView(activity);day.setGravity(Gravity.CENTER);
	        
	        tv.setText(""+player_list.get(i).getScore());
	        tv.setLayoutParams(tlp1);
	        qty.setText(player_list.get(i).getNick_name());
	        qty.setLayoutParams(tlp1);
	        day.setText(""+player_list.get(i).getSurvival_day());
	        day.setLayoutParams(tlp1);
	        row.addView(qty);
	        row.addView(tv);
	        row.addView(day);
	        ll.addView(row,i);
	    	
	    }

		//start page button
		Button start = (Button) (activity.findViewById(R.id.button1));
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new StartPage().show((Activity)v.getContext(),true);
			}
		});
		

	}
}
