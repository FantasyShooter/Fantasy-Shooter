package com.fshoot.entity;

import com.example.fantasyshooter.R;
import com.fshoot.main.MyApp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SmallM extends Monster {

	private ImageView image;
	
	public SmallM() {
		hp = 100;
		atk = 1;
		speed = 3;
	}

	public void create(Activity activity) {
		// Create a monster image
		ImageView im = new ImageView(activity);
		im.setImageResource(R.drawable.small);
		im.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Activity activity = (Activity)v.getContext();
				MyApp myapp = ((MyApp) activity.getApplicationContext());
				
				int totalAtk = myapp.getPlayer().getTotalAtk();
				hp -= totalAtk;
				// if hp is lower then 1, delete this
				if(hp<=0){
					LinearLayout rl = (LinearLayout) activity.findViewById(R.id.battleRoot);
					rl.removeView(image);
				}
			}
		});
		// Backup
		this.image = im;
		// Add to battle view
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
		LinearLayout rl = (LinearLayout) activity.findViewById(R.id.battleRoot);
		lp.setMargins(0, 0, 0, 0);
		//lp.setMargins(rl.getWidth()-lp.width, (rl.getHeight()-lp.height)/2, 0, 0);
		rl.addView(im, lp);
	}

	public void deductHP(int totalATK){
		
	}
}
