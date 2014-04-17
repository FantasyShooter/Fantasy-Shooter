package com.fshoot.framepage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Button;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Player;
import com.fshoot.main.MainActivity;
import com.fshoot.main.MyApp;
import com.fshoot.main.PlayerDBHelper;

public class StartPage implements FramePage {

	private Activity activity;

	@Override
	public void show(Activity act, boolean isSaveToRam) {

		this.activity = act;
		// 1. GUI
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_start, frame, true);

		// Save this FramePage into Ram
		if (isSaveToRam) {
			((MyApp) activity.getApplicationContext()).getScreenList().add(this);
		}

		// 2. setup event listener

		// 3. event start when created
		
		// background sound
		if(MainActivity.bgm!=null){
			MainActivity.bgm.stop();
		}
		MainActivity.bgm = new MediaPlayer();
		MainActivity.bgm = MediaPlayer.create(activity, R.raw.the_town_music);
		MainActivity.bgm.setLooping(true);
		MainActivity.bgm.start();

		// start button
		Button start = (Button) (activity.findViewById(R.id.button1));
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// final Activity activity = (Activity) v.getContext();

				AlertDialog.Builder alert = new AlertDialog.Builder(activity);

				alert.setTitle("Information");
				alert.setMessage("Please input your user name");

				// Set an EditText view to get user input
				final EditText input = new EditText(activity);
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						String value = input.getText().toString();
						// Do something with value!
						MyApp myapp = ((MyApp) activity.getApplicationContext());
						// Ask for user input the name
						Player player = new Player(value);
						player.initialPlayer(); // You must initial it

						PlayerDBHelper db = new PlayerDBHelper(activity);
						db.createTablePlayerIfNotExists(db.getWritableDatabase());
						// add to db
						db.add_Player(player);
						// add to ram
						myapp.setPlayer(player);

						new TownPage().show(activity, true);
					}
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});
				
				alert.setCancelable(false);
				alert.show();
				// see
				// http://androidsnippets.com/prompt-user-input-with-an-alertdialog

				// new TownPage().show((Activity)v.getContext());
			}
		});
		// start game

		// score button
		Button score = (Button) (activity.findViewById(R.id.button2));
		score.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new ScorePage().show((Activity) v.getContext(), true);
			}
		});

	}

}
