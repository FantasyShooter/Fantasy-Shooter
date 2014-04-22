package com.fshoot.framepage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Player;
import com.fshoot.main.MyApp;

public class ShopPage implements FramePage {

	private Activity activity;

	// 1. GUI
	public void show(Activity activity, boolean isSaveToRam) {
		this.activity = activity;

		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity
				.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_shop, frame, true);

		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList()
					.add(this);
		}

		// 2. setup event listener

		// 3. event start when created

		// show the score
		TextView tv = (TextView) (activity.findViewById(R.id.textView1));
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		Player player;
		player = myapp.getPlayer();
		tv.setText(" Score: " + player.getScore());

		// buy button click
		Button buy = (Button) (activity.findViewById(R.id.button2));
		buy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = (Activity) v.getContext();
				MyApp myapp = ((MyApp) activity.getApplicationContext());
				Player player;
				player = myapp.getPlayer();
				if (player.getScore() >= 100) {
					Builder myAlertDialog;
					// change player atk
					int atk = player.getWeapon().getAtk();
					atk = atk + 20;
					player.getWeapon().setAtk(atk);
					player.setScore(player.getScore()-100);
					// set back the player
					myapp.setPlayer(player);
					// Update Score UI
					TextView tv = (TextView) (activity
							.findViewById(R.id.textView1));
					tv.setText(" Score: " + player.getScore());
					// Show dialogue for choose
					myAlertDialog = new AlertDialog.Builder(activity);
					myAlertDialog.setTitle("Information");
					myAlertDialog.setMessage(" Attack added to "
							+ player.getTotalAtk());
					DialogInterface.OnClickListener doneClick3 = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					};

					myAlertDialog.setNeutralButton("Done", doneClick3);
					myAlertDialog.setCancelable(false);
					myAlertDialog.show();
				} else {
					// Show dialogue for choose
					Builder myAlertDialog2 = new AlertDialog.Builder(activity);
					myAlertDialog2.setTitle("Information");
					myAlertDialog2
							.setMessage(" You need 100 score to add the attack");
					DialogInterface.OnClickListener doneClick3 = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					};

					myAlertDialog2.setNeutralButton("Done", doneClick3);
					myAlertDialog2.setCancelable(false);
					myAlertDialog2.show();
				}
			}
		});
		// back to town button click
		Button town = (Button) (activity.findViewById(R.id.button1));
		town.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TownPage().show((Activity) v.getContext(), true);
			}
		});
	}

}
