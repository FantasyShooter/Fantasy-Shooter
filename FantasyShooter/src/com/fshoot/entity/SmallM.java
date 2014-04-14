package com.fshoot.entity;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SmallM extends Monster {

	private ImageView image;
	static Lock lock = new ReentrantLock();


	public SmallM() {
		hp = 100;
		atk = 1;
		speed = 3;
	}

	public void create(Activity activity) {
		// Create a monster image
		ImageView im = new ImageView(activity);
		im.setImageResource(R.drawable.small);
		im.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = (Activity) v.getContext();
				MyApp myapp = ((MyApp) activity.getApplicationContext());

				int totalAtk = myapp.getPlayer().getTotalAtk();
				hp -= totalAtk;
				// if hp is lower then 1, delete this
				if (hp <= 0) {
					RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
					rl.removeView(image);
				}
			}
		});
		// Backup
		this.image = im;
		// Add to battle view
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
		RelativeLayout rl = (RelativeLayout) activity.findViewById(R.id.rightRoot);
		int w = rl.getLayoutParams().width;
		int h = rl.getLayoutParams().height;
		
		int random = (int) Math.random()*3;
		// Random pop up at a line
		lp.setMargins(w - lp.width, Monster.rowY[random]-lp.height, 0, 0);
		rl.addView(im, lp);
	}

	public void deductHP(int totalATK) {
		lock.lock(); // Acquire the lock

		try {
			hp -= totalATK;
			// Update the hp ui
		} catch (Exception ex) { // optional catch block
			
		} finally {
			lock.unlock(); // Release the lock
		}
	}

}
