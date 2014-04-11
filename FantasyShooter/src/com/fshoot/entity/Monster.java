package com.fshoot.entity;

import android.view.View;

public class Monster {

	protected int hp;
	protected int atk;
	protected int speed;
	protected View imageView;
	
	public int getHp() {
		return hp;
	}
	public int getAtk() {
		return atk;
	}
	public int getSpeed() {
		return speed;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
