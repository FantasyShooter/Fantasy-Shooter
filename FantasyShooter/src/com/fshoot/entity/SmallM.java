package com.fshoot.entity;

import com.example.fantasyshooter.R;

import android.app.Activity;
import android.widget.ImageView;

public class SmallM extends Monster { 

	public SmallM(Activity activity) {
		this.activity = activity;
		hp = 200;
		atk = 1;
		speed = 3;
		
		step = -10 * speed;
		score = 5;
		image = new ImageView(activity);
		image.setImageResource(R.drawable.small);
	}


	

}
