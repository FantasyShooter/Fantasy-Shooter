package com.fshoot.entity;

import android.app.Activity;
import android.widget.ImageView;

public class BigM extends Monster{

	private int hp;
	private int atk;
	private int speed;
	private ImageView image;
	
	public BigM(){
		hp = 400;
		atk = 3;
		speed = 1;
	}

	@Override
	public void initial(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	
}
