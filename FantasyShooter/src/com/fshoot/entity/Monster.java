package com.fshoot.entity;

import com.example.fantasyshooter.R;
import com.fshoot.framepage.BattlePage;
import com.fshoot.framepage.StartPage;
import com.fshoot.framepage.TownPage;
import com.fshoot.main.MainActivity;
import com.fshoot.main.MyApp;
import com.fshoot.main.PlayerDBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class Monster {

	protected int hp;
	protected int atk;
	protected int speed;
	protected ImageView image;
	protected Handler handler;
	protected Activity activity;
	protected int step;
	protected int score;

	public Monster() {

	}

	static int rowY[] = { 156, 312, 468 };

	public void initial() {
		Log.d("debug", "SmallM.create()");

		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = (Activity) v.getContext();
				// Play fire sound
				MainActivity.mp = MediaPlayer.create(activity, R.raw.fire);
				MainActivity.mp.start();

				deductHP((Activity) v.getContext());
			}
		});

		// Add to battle view
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
		RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
		int w = rl.getLayoutParams().width;
		// int h = rl.getLayoutParams().height;

		int random = (int) (Math.random() * 3);

		// Random pop up at a line
		lp.setMargins(w - lp.width, Monster.rowY[random] - lp.height, 0, 0);
		rl.addView(image, lp);
		Log.d("debug", "under RightRoot:" + rl.getChildCount());
	}

	public void attack() {
		if (BattlePage.gameRunning) {
			// play attack sound
			MediaPlayer kockWall = new MediaPlayer();
			kockWall = MediaPlayer.create(activity, R.raw.knock);
			kockWall.start();

			// Deduct HP
			MyApp myapp = (MyApp) activity.getApplicationContext();
			Player player = myapp.getPlayer();
			player.deductHP(atk);
			// Update the hp
			TextView txtHP = (TextView) activity.findViewById(R.id.txtHP);
			txtHP.setText("HP " + player.getHp());

			if (player.getHp() <= 0) {
				// Game over
				finishDay();
			} else {
				// wait some times and attack
				if (BattlePage.gameRunning) {
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							attack();
						}
					}, 1000);
				}
			}
		}
	}

	public void deductHP(Activity activity) {
		Log.d("Debug", "deductHP()");
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		Player player = myapp.getPlayer();
		int totalAtk = player.getTotalAtk();
		hp -= totalAtk;
		// if hp is lower then 1, delete this
		if (hp <= 0) {
			// if monster die
			Log.d("Debug", "HP <0 kill");
			// add score
			player.addScore(score);
			// Update hp ui
			TextView txtScore = (TextView) activity.findViewById(R.id.txtScore);
			txtScore.setText("Score " + player.getScore());
			// remove image
			RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
			image.clearAnimation();
			rl.removeView(image);
			// if no more monster
			if (rl.getChildCount() == 0) {
				finishDay();
			}
		}
	}

	public void finishDay() {
		Log.d("Debug", "Level finish.");
		BattlePage.gameRunning = false;

		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(activity);
		DialogInterface.OnClickListener doneClick;
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		Player player = myapp.getPlayer();

		if (player.getHp() > 0) {
			int nextday = player.getSurvival_day() + 1;

			// Finish a day
			player.setSurvival_day(nextday);

			if (nextday == 3) {
				// Save the score to db
				new PlayerDBHelper(activity).add_Player(player);

				myAlertDialog.setTitle("Information");
				myAlertDialog.setMessage("Congratulations! People are save now.");
				doneClick = new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// back to town
						new StartPage().show(activity, true);
					}
				};
			} else {

				myAlertDialog.setTitle("Information");
				myAlertDialog.setMessage("One day has been passed.\nGet some rest.");
				doneClick = new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// back to town
						new TownPage().show(activity, true);
					}
				};

			}

		} else {
			// if the game lose
			// Save the score to db
			new PlayerDBHelper(activity).add_Player(player);

			myAlertDialog.setTitle("Information");
			myAlertDialog.setMessage("You lose, monster eat all of the people.");
			doneClick = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					// Save score
					MyApp myapp = ((MyApp) activity.getApplicationContext());
					new PlayerDBHelper(activity).add_Player(myapp.getPlayer());

					// Back to start page
					myapp.cleanScreenList();
					new StartPage().show(activity, true);
				}
			};
		}
		// Show dialog
		myAlertDialog.setNeutralButton("Done", doneClick);
		myAlertDialog.setCancelable(false);
		myAlertDialog.show();
	}

	public void moveToLeft() {
		// Log.d("debug", "moveToLeft()");

		handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				TranslateAnimation animation = new TranslateAnimation(0, step, 0, 0);
				animation.setDuration(250);
				animation.setFillAfter(true);
				animation.setAnimationListener(new moveToLeftAnimationListener(image, step));
				image.startAnimation(animation);
			}
		});
	}

	class moveToLeftAnimationListener implements AnimationListener {

		private int step;
		private View v;

		public moveToLeftAnimationListener(View v, int step) {
			this.step = step;
			this.v = v;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// Log.d("debug", "onAnimationEnd()");
			v.clearAnimation();
			LayoutParams old_lp = (LayoutParams) v.getLayoutParams();
			LayoutParams lp = new LayoutParams(v.getWidth(), v.getHeight());

			int leftM = old_lp.leftMargin + step;
			int topM = old_lp.topMargin;
			int rightM = old_lp.rightMargin;
			int bottomM = old_lp.bottomMargin;

			if (leftM <= 0) {
				leftM = 0;
				lp.setMargins(leftM, topM, rightM, bottomM);
				v.setLayoutParams(lp);
				// Start attack
				attack();
			} else {
				lp.setMargins(leftM, topM, rightM, bottomM);
				v.setLayoutParams(lp);
				moveToLeft();
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

	}
}
