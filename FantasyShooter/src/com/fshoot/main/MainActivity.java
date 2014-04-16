package com.fshoot.main;

import java.util.LinkedList;

import com.example.fantasyshooter.R;
import com.fshoot.entity.Player;
import com.fshoot.framepage.StartPage;
import com.fshoot.main.MyApp;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Ask for user input the name
		Player player = new Player("Player");
		player.initialPlayer(); // You must initial it
		
		PlayerDBHelper db = new PlayerDBHelper(this);
		db.createTablePlayerIfNotExists(db.getWritableDatabase());		
		// add to db
		db.add_Player(player);
		
		// add to ram
		((MyApp) getApplicationContext()).setPlayer(player);
		
		// Get
//		ArrayList<Player> player_list = db.get_Players();
//		for(int i=0;i<player_list.size();i++){
//			Log.d("debug",player_list.get(i).getNick_name());
//		}
		
		
		// Create the main menu here
		new StartPage().show(this, true);
		//new BattlePage().show(this);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.test
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	@Override
	public void onBackPressed() {
		// Change back button to pause game or quit game or save game
		MyApp myapp = ((MyApp) this.getApplicationContext());
		LinkedList<com.fshoot.framepage.FramePage> screenList = myapp.getScreenList();
		screenList.removeLast();
		com.fshoot.framepage.FramePage page = null;
		if ((page = screenList.peekLast()) != null) {
			Log.d("Debug", "not null screenlist");
			String result = "";
			for (int i = 0; i < screenList.size(); i++) {
				result += screenList.get(i).getClass().toString() + "\n";
			}
			Log.d("Debug", result);
			page.show(this,false);
		} else {
			Log.d("Debug", "Null screenlist");
			super.onBackPressed();
		}
	}
}
