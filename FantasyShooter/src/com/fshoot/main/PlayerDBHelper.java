package com.fshoot.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayerDBHelper  extends SQLiteOpenHelper {
	
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
		String CREATE_UserS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYER_SCORE + "(" + KEY_ID
				+ " INTEGER," + KEY_NICK_NAME + " TEXT, " + KEY_SCORE + " INTEGER, " + KEY_SURVIVAL_DAY
				+ " INTEGER" + ")";
		Log.d("Debug",CREATE_UserS_TABLE);
		db.execSQL(CREATE_UserS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("Debug", "UserDBHelper.onUpgrade()");
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_SCORE);

		// Create tables again
		onCreate(db);
	}
	
}
