package com.fshoot.framepage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fshoot.entity.Player;
import com.fshoot.main.MyApp;

import com.example.fantasyshooter.R;

public class LuckyDrawPage implements FramePage{
	
	private Activity activity;
	// 1. GUI
	public void show(Activity activity) {
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_luckydraw, frame,true);
		
		// 2. setup event listener
		
		// 3. event start when created
		//lucky draw go  button
		Button go = (Button) (activity.findViewById(R.id.button1));
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = (Activity)v.getContext();
				int random = (int)Math.ceil(Math.random()*3+1);
				MyApp myapp = ((MyApp) activity.getApplicationContext());
				Player player;
				Builder myAlertDialog;
				switch(random){
					case 1: 
						player = myapp.getPlayer();
						// change player hp 
						int hp = player.getHp();
						hp = hp+20;
						// set back the player
						player.setHp(hp);
						myapp.setPlayer(player);
						// Show dialogue for choose
						myAlertDialog = new AlertDialog.Builder(activity);
						myAlertDialog.setTitle("Information");
						myAlertDialog.setMessage(" Hp added "+player.getHp());
						DialogInterface.OnClickListener doneClick = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							}
						};
						
						myAlertDialog.setNeutralButton("Done", doneClick);
						myAlertDialog.show();
					break;
					case 2:
						player = myapp.getPlayer();
						// change player atk
						int atk = player.getAtk();
					    atk = atk+20;
						// set back the player
						player.setAtk(atk);
						myapp.setPlayer(player);
						// Show dialogue for choose
						myAlertDialog = new AlertDialog.Builder(activity);
						myAlertDialog.setTitle("Information");
						myAlertDialog.setMessage(" Attack added "+player.getAtk());
						DialogInterface.OnClickListener doneClick2 = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							}
						};
						
						myAlertDialog.setNeutralButton("Done", doneClick2);
						myAlertDialog.show();
					break;
					case 3:
						player = myapp.getPlayer();
						// change player atk
						int score = player.getScore();
					    score = score+20;
						// set back the player
						player.setScore(score);
						myapp.setPlayer(player);
						// Show dialogue for choose
						myAlertDialog = new AlertDialog.Builder(activity);
						myAlertDialog.setTitle("Information");
						myAlertDialog.setMessage(" Score added "+player.getScore());
						DialogInterface.OnClickListener doneClick3 = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							}
						};
						
						myAlertDialog.setNeutralButton("Done", doneClick3);
						myAlertDialog.show();
					break;
				}
            }
		});
		//back to town button
		Button town = (Button) (activity.findViewById(R.id.button2));
		town.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TownPage().show((Activity)v.getContext());
		    }
		});
	}
}