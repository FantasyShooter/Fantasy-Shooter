package com.fshoot.entity;

public class Player {

	private int id;
	private int atk;
	private int hp;
	private Weapon weapon;
	private String nick_name;
	private int score;
	private int survival_day;

	public Player() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player(int id, String nick_name, int score, int survival_day) {
		super();
		this.id = id;
		this.nick_name = nick_name;
		this.score = score;
		this.survival_day = survival_day;
	}

	public int getTotalAtk() {
		if (weapon == null) {
			return atk;
		} else {
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

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSurvival_day() {
		return survival_day;
	}

	public void setSurvival_day(int survival_day) {
		this.survival_day = survival_day;
	}
}
