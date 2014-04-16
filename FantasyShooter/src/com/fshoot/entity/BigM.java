package com.fshoot.entity;

import android.app.Activity;
import android.widget.ImageView;

public class BigM implements Monster{

	private int hp;
	private int atk;
	private int speed;
	private ImageView image;
	
	public BigM(){
		hp = 300;
		atk = 3;
		speed = 1;
	}

	@Override
	public void create(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deductHP(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMove(Activity activity) {
		// TODO Auto-generated method stub
		
	}
	
}
