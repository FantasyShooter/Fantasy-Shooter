package com.fshoot.entity;

import android.app.Activity;
import android.widget.ImageView;

public class MiddleM implements Monster{

	private int hp;
	private int atk;
	private int speed;
	private ImageView image;
	
	public MiddleM(){
		hp = 300;
		atk = 2;
		speed = 2;
	}

	@Override
	public void initial(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deductHP(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveToLeft() {
		// TODO Auto-generated method stub
		
	}


	
}
