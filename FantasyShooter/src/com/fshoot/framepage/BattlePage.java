package com.fshoot.framepage;

import java.util.ArrayList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.BigM;
import com.fshoot.entity.Level;
import com.fshoot.entity.MiddleM;
import com.fshoot.entity.Monster;
import com.fshoot.entity.Player;
import com.fshoot.entity.SmallM;
import com.fshoot.main.MainActivity;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BattlePage implements FramePage {

	private Activity activity;
	private Handler handler;
	private TextView txtStartGame;
	private AlphaAnimation fadeOut;
	public static boolean gameRunning = true;

	@Override
	public void show(Activity activity, boolean isSaveToRam) {
		// 1. GUI
		this.activity = activity;
		FrameLayout frame = (FrameLayout) (activity.findViewById(R.id.content_frame));
		// Remove the old one
		frame.removeAllViewsInLayout();
		// Show a new screen
		LayoutInflater.from(activity).inflate(R.layout.frame_battle, frame, true);
		
		MyApp myapp = ((MyApp) activity.getApplicationContext());
		// Save this FramePage into Ram
		if (isSaveToRam) {
			myapp.getScreenList().add(this);
		}
		
		// background sound
		if(MainActivity.bgm!=null){
			MainActivity.bgm.stop();
		}
		MainActivity.bgm = new MediaPlayer();
		MainActivity.bgm = MediaPlayer.create(activity, R.raw.battle_music);
		MainActivity.bgm.setLooping(true);
		MainActivity.bgm.start();

		// Reset level data
		ArrayList<Level> level_list = new ArrayList<Level>();
		level_list.add(new Level(1, 0, 0));
		level_list.add(new Level(2, 0, 0));
		level_list.add(new Level(3, 0, 0));
		myapp.setLevel_list(level_list);

		// 2. setup event listener

		// 3. event start when created
		// 3.1 Show game start
		Player player = myapp.getPlayer();
		TextView txtHP = (TextView) activity.findViewById(R.id.txtHP);
		txtHP.setText("HP " + player.getHp());
		
		gameStartAppear(player.getSurvival_day());
	}

	public void gameStartAppear(int survival_day) {
		RelativeLayout battleRoot = (RelativeLayout) (activity.findViewById(R.id.battleRoot));
		txtStartGame = new TextView(activity);
		txtStartGame.setGravity(Gravity.CENTER);

		txtStartGame.setText("Day " + (survival_day + 1) + " start. \n");
		txtStartGame.setTextAppearance(activity, android.R.style.TextAppearance_Large);

		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rlp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		// txtStartGame.startAnimation(AnimationUtils.loadAnimation(activity,
		// android.R.anim.fade_in));

		battleRoot.addView(txtStartGame, rlp);
		// Setup animation
		AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
		fadeOut = new AlphaAnimation(1.0f, 0.0f);
		fadeIn.setDuration(1200);
		fadeOut.setDuration(1200);

		fadeIn.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				txtStartGame.startAnimation(fadeOut);
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationStart(Animation arg0) {
			}
		});

		fadeOut.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				((RelativeLayout) (activity.findViewById(R.id.battleRoot)))
						.removeView(txtStartGame);
				// After fade out, start create monster
				releaseMonster();
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationStart(Animation arg0) {
			}
		});
		// Start fade in
		txtStartGame.startAnimation(fadeIn);
	}

	public synchronized void releaseMonster() {
		Log.d("debug", "releaseMonster()");
		handler = new Handler();

		int delayTime = ((int) (Math.random() * 100 + 50)) * 10;
		Log.d("delayTime", "" + delayTime);

		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				MyApp myapp = ((MyApp) activity.getApplicationContext());
				int survival_day = myapp.getPlayer().getSurvival_day();
				
				Level level = myapp.getLevel_list().get(survival_day);

				Monster monster = null;
				int ran = (int) (Math.random() * 3);
				
				if (ran == 0) {
					monster = new SmallM(activity);
					level.deductSmallM();
				} else if (ran == 1) {
					monster = new MiddleM(activity);
					level.deductMiddleM();
				} else if (ran == 2) {
					monster = new BigM(activity);
					level.deductBigM();
				}
				monster.initial();
				monster.moveToLeft();

				if (level.hasMoreMonster()) {
					releaseMonster();
				}
			}
		}, delayTime);

	}
}
