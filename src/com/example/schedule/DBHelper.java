package com.example.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "dbForTest.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "listv";
	public static final String ID = "_id";
	public static final String CHECK = "ischeck";
	public static final String THINGS = "things";
	public static final String TIME = "times";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY," 
				+ CHECK + " int," + THINGS + " text not null,"+ TIME + " text);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
