package com.fshoot.entity;

import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;

public class SmallM implements Monster {

	private int hp;
	private int atk;
	private int speed;
	private ImageView image;

	public SmallM() {
		hp = 100;
		atk = 1;
		speed = 3;
	}

	public void create(Activity activity) {
		Log.d("debug","SmallM.create()");
		// Create a monster image
		ImageView im = new ImageView(activity);
		im.setImageResource(R.drawable.small);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				deductHP((Activity) v.getContext());
			}
		});
		// Backup
		this.image = im;
		// Add to battle view
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
		RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
		int w = rl.getLayoutParams().width;
		//int h = rl.getLayoutParams().height;
		
		int random = (int) (Math.random()*3);
		
		// Random pop up at a line
		lp.setMargins(w - lp.width, Monster.rowY[random]-lp.height, 0, 0);
		rl.addView(im, lp);
		Log.d("debug","under RightRoot:"+rl.getChildCount());
	}

	public void deductHP(Activity activity) {
		Log.d("Debug","deductHP()");
		MyApp myapp = ((MyApp) activity.getApplicationContext());

		int totalAtk = myapp.getPlayer().getTotalAtk();
		hp -= totalAtk;
		// if hp is lower then 1, delete this
		if (hp <= 0) {
			Log.d("Debug","HP <0 kill");
			RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
			rl.removeView(image);
		}
	}
	
	public void startMove(Activity activity){
		//RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
		
	}

}
