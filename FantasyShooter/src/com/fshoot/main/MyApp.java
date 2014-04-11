package com.fshoot.main;

import java.util.LinkedList;

import com.fshoot.entity.Player;
import com.fshoot.framepage.FramePage;

import android.app.Application;

// To get status memory, use -> MyApp myapp = (MyApp) activity.getApplicationContext();
public class MyApp extends Application {

	private Player player;
	private LinkedList<FramePage> screenList = new LinkedList<FramePage>();
	private int day;
	
	public LinkedList<FramePage> getScreenList() {
		return screenList;
	}

	public void setScreenList(LinkedList<FramePage> screenList) {
		this.screenList = screenList;
	}

	// Getter and Setter
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}