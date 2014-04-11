package com.fshoot.entity;

public class Player {

	private int atk;
	private int hp;
	private Weapon weapon;
	
	public int getTotalAtk(){
		if(weapon==null){
			return atk;
		}else{
			return weapon.getAtk() + atk;
		}
	}

	// Getter and Setter
	public int getAtk() {
		return atk;
	}
	public int getHp() {
		return hp;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
