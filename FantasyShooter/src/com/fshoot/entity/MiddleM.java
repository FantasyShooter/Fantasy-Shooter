package com.fshoot.entity;

import com.example.fantasyshooter.R;

import android.app.Activity;
import android.widget.ImageView;

public class MiddleM extends Monster{
	
	public MiddleM(Activity activity){
		this.activity = activity;
		hp = 300;
		atk = 2;
		speed = 2;

		step = -10 * speed;
		score = 20;
		image = new ImageView(activity);
		image.setImageResource(R.drawable.middle);
	}
	
}
