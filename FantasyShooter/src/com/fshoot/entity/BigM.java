package com.fshoot.entity;

import com.example.fantasyshooter.R;

import android.app.Activity;
import android.widget.ImageView;

public class BigM extends Monster{

	public BigM(Activity activity){
		this.activity = activity;
		hp = 400;
		atk = 3;
		speed = 1;
		
		step = -10 * speed;
		image = new ImageView(activity);
		image.setImageResource(R.drawable.boss);
	}
	
}
