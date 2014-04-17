package com.fshoot.main;

import java.util.ArrayList;
import java.util.LinkedList;

import com.fshoot.entity.Level;
import com.fshoot.entity.Player;
import com.fshoot.framepage.FramePage;

import android.app.Application;

// To get status memory, use -> MyApp myapp = (MyApp) activity.getApplicationContext();
public class MyApp extends Application {

	private Player player;
	private LinkedList<FramePage> screenList = new LinkedList<FramePage>();
	private ArrayList<Level> level_list;
	private boolean lucklyDraw[] = new boolean[]{false, false, false};
	
	public boolean[] getLucklyDraw() {
		return lucklyDraw;
	}

	public void setLucklyDraw(boolean[] lucklyDraw) {
		this.lucklyDraw = lucklyDraw;
	}

	
	public void cleanScreenList(){
		screenList.clear();
	}
	
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

	public ArrayList<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(ArrayList<Level> level_list) {
		this.level_list = level_list;
	}

}