package com.fshoot.entity;

import com.example.fantasyshooter.R;
import com.fshoot.main.MainActivity;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

public class SmallM implements Monster {

	private int hp;
	private int atk;
	private int speed;
	private ImageView image;
	private Handler handler;

	public SmallM() {
		hp = 200;
		atk = 1;
		speed = 3;
	}

	public void initial(Activity activity) {
		Log.d("debug", "SmallM.create()");
		// Create a monster image
		ImageView im = new ImageView(activity);
		im.setImageResource(R.drawable.small);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = (Activity) v.getContext();
				deductHP((Activity) v.getContext());
				// Play fire sound
				MainActivity.mp = MediaPlayer.create(activity, R.raw.fire);
				MainActivity.mp.start();
			}
		});
		// Backup
		this.image = im;
		// Add to battle view
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100,
				100);
		RelativeLayout rl = (RelativeLayout) activity
				.findViewById(R.id.rightRoot);
		int w = rl.getLayoutParams().width;
		// int h = rl.getLayoutParams().height;

		int random = (int) (Math.random() * 3);

		// Random pop up at a line
		lp.setMargins(w - lp.width, Monster.rowY[random] - lp.height, 0, 0);
		rl.addView(im, lp);
		Log.d("debug", "under RightRoot:" + rl.getChildCount());
	}

	public void deductHP(Activity activity) {
		Log.d("Debug", "deductHP()");
		MyApp myapp = ((MyApp) activity.getApplicationContext());

		int totalAtk = myapp.getPlayer().getTotalAtk();
		hp -= totalAtk;
		// if hp is lower then 1, delete this
		if (hp <= 0) {
			Log.d("Debug", "HP <0 kill");
			RelativeLayout rl = (RelativeLayout) activity
					.findViewById(R.id.rightRoot);
			image.clearAnimation();
			rl.removeView(image);
		}
	}

	public void moveToLeft() {
		Log.d("debug", "moveToLeft()");
		handler = new Handler();

		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) image
				.getLayoutParams();

		handler.post(new Runnable() {
			@Override
			public void run() {
				int step = -10 * speed;

				TranslateAnimation animation = new TranslateAnimation(0, step,
						0, 0);
				animation.setDuration(500);
				animation.setFillAfter(false);
				animation.setAnimationListener(new MyAnimationListener(image,
						step));
				image.startAnimation(animation);
			}
		});

	}

	class MyAnimationListener implements AnimationListener {

		private int step;
		private View v;

		public MyAnimationListener(View v, int step) {
			this.step = step;
			this.v = v;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			Log.d("debug", "onAnimationEnd()");
			v.clearAnimation();
			LayoutParams old_lp = (LayoutParams) v.getLayoutParams();
			Log.d("debug", "v.getWidth():" + v.getWidth());
			Log.d("debug", "v.getHeight():" + v.getHeight());
			LayoutParams lp = new LayoutParams(v.getWidth(), v.getHeight());

			int leftM = old_lp.leftMargin + step;
			int topM = old_lp.topMargin;
			int rightM = old_lp.rightMargin;
			int bottomM = old_lp.bottomMargin;

			if (leftM <= 0) {
				leftM = 0;
				lp.setMargins(leftM, topM, rightM, bottomM);
				v.setLayoutParams(lp);
			} else {
				// Log.d("debug","leftM:"+leftM);
				// Log.d("debug","topM:"+topM);
				// Log.d("debug","rightM:"+rightM);
				// Log.d("debug","bottomM:"+bottomM);
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
