package com.fshoot.main;

import java.util.LinkedList;

import com.fshoot.entity.Player;
import com.fshoot.framepage.FramePage;

import android.app.Application;

// To get from memory, use -> MyApp myapp = (MyApp) activity.getApplicationContext();
public class MyApp extends Application {

	private Player player;
	private LinkedList<FramePage> screenList = new LinkedList<FramePage>();

	public LinkedList<FramePage> getScreenList() {
		return screenList;
	}

	public void setScreenList(LinkedList<FramePage> screenList) {
		this.screenList = screenList;
	}

	public Player getUser() {
		return player;
	}

	public void setUser(Player player) {
		this.player = player;
	}

}