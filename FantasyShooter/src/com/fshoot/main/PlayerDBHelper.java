package com.fshoot.main;

import java.util.ArrayList;

import com.fshoot.entity.Player;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayerDBHelper extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "FANSTASYSHOOTER";

	// Users table name
	private static final String TABLE_PLAYER_SCORE = "PLAYER_SCORE";

	// Users Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NICK_NAME = "nick_name";
	private static final String KEY_SCORE = "score";
	private static final String KEY_SURVIVAL_DAY = "survival_day";

	public PlayerDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("Debug", "UserDBHelper.onUpgrade()");
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_SCORE);
		// Create tables again
		createTablePlayerIfNotExists(db);
	}

	public void createTablePlayerIfNotExists(SQLiteDatabase db) {
		
		String CREATE_UserS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_PLAYER_SCORE + "(" + KEY_ID + " INTEGER,"
				+ KEY_NICK_NAME + " TEXT, " + KEY_SCORE + " INTEGER, "
				+ KEY_SURVIVAL_DAY + " INTEGER" + ")";
		Log.d("Debug", CREATE_UserS_TABLE);
		db.execSQL(CREATE_UserS_TABLE);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 **/
	// Adding new Player
	public void add_Player(Player player) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "Insert into " + TABLE_PLAYER_SCORE + " VALUES ('"
				+ player.getNick_name() + "', " + player.getScore() + ", "
				+ player.getSurvival_day() + ")";
		Log.d("Debug", sql);
		db.execSQL(sql);
		db.close(); // Closing database connection
	}

	// Getting All Player
	public ArrayList<Player> get_Players() {
		ArrayList<Player> player_list = new ArrayList<Player>();

		try {
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_PLAYER_SCORE;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Player User = new Player(cursor.getInt(0),
							cursor.getString(1), cursor.getInt(2),
							cursor.getInt(3));
					// Adding User to list
					player_list.add(User);
				} while (cursor.moveToNext());
			}

			// return Player list
			cursor.close();
			db.close();
			return player_list;
		} catch (Exception e) {
			Log.e("get_Players()", "" + e.getMessage());
		}

		return player_list;
	}
}
