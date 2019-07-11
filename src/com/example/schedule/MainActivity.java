package com.example.schedule;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static Context mContext;
	private List<Event> mData = null;
	//private Context mContext;
	private EventAdapter mAdapter = null;
	private ListView list_event;
	OnClickListener listener1 = null;
	OnClickListener listener2 = null;
	OnClickListener listener3 = null;
	Button button1;
	Button button2;
	Button button3;

	DBHelper mOpenHelper;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = getApplicationContext();
		init();
		initListener();
		mOpenHelper = new DBHelper(this);
		display();
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		
		list_event.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int position, long l) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
				Bundle bd = new Bundle();
				bd.putInt("id", mData.get(position).getId());
				bd.putString("things", mData.get(position).getThings());
				bd.putString("time", mData.get(position).getData());
				intent.putExtras(bd);

				startActivityForResult(intent, 0x001);
			}
		});
	}

	public void display() {
		mData.clear();
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		String col[] = { DBHelper.ID, DBHelper.THINGS, DBHelper.CHECK,DBHelper.TIME };
		Cursor cursor = db.query(DBHelper.TABLE_NAME, col, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				// 遍历Cursor对象，取出数据并打印
				int id=cursor.getInt(cursor.getColumnIndex("_id"));
				int ischeck =cursor.getInt(cursor.getColumnIndex("ischeck"));
				boolean check=(ischeck!=0)?true:false;
				String key = cursor.getString(cursor.getColumnIndex("things"));
				String time = cursor.getString(cursor.getColumnIndex("times"));
				mData.add(new Event(id,check, key,time));
				mAdapter = new EventAdapter((LinkedList<Event>) mData, mContext);
				list_event.setAdapter(mAdapter);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
	}

	public void init() {
		mContext = MainActivity.this;
		new CheckBox(mContext);
		list_event = (ListView) findViewById(R.id.listView1);
		mData = new LinkedList<Event>();
		mAdapter = new EventAdapter((LinkedList<Event>) mData, mContext);
		list_event.setAdapter(mAdapter);
		TextView txt_empty = (TextView) findViewById(R.id.txt_empty);
		txt_empty.setText("无待办事项~");
		list_event.setEmptyView(txt_empty);
	}

	public void initListener() {
		listener1 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(MainActivity.this, AddeventActivity.class);
				startActivityForResult(intent1, 0x123);
			}

		};
		listener2 = new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = mOpenHelper.getWritableDatabase();
				try {
//					db.execSQL("DROP TABLE IF EXISTS listv");
//					db.execSQL(sql);
					String[] args= {"1"};
					db.delete(DBHelper.TABLE_NAME, "ischeck = ?", args);
					Toast.makeText(MainActivity.this, "已删除",Toast.LENGTH_LONG).show();
				} catch (SQLException e) {
					// TODO: handle exception
					Toast.makeText(MainActivity.this, "删除失败",Toast.LENGTH_LONG).show();
				}
				display();
				mAdapter.notifyDataSetChanged();
			}
			
		};
		listener3 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = mOpenHelper.getWritableDatabase();
				String sql = "CREATE TABLE " + DBHelper.TABLE_NAME + "(_id INTEGER PRIMARY KEY," + 
						DBHelper.CHECK + " int," + DBHelper.THINGS+ " text not null ,"+ DBHelper.TIME + " text);";
				try {
					db.execSQL("DROP TABLE IF EXISTS listv");
					Toast.makeText(MainActivity.this, "已清空",Toast.LENGTH_LONG).show();
					db.execSQL(sql);
				} catch (SQLException e) {
					// TODO: handle exception
					Toast.makeText(MainActivity.this, "清空失败",Toast.LENGTH_LONG).show();
				}
				display();
				mAdapter.notifyDataSetChanged();
			}

		};
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		menu.add(1,GREEN,2,"绿色");
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0x123 && resultCode == 0x123) {
			Bundle bd = data.getExtras();
			String key = bd.getString("key");
			int s = 0;
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("ischeck", s);
			values.put("things", key);
			db.insert(DBHelper.TABLE_NAME, null, values);
			mAdapter.notifyDataSetChanged();
		}
		else if(requestCode == 0x123 && resultCode == 0x124) {
			Bundle bd = data.getExtras();
			String key = bd.getString("key");
			String time = bd.getString("time");
			int s = 0;
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("ischeck", s);
			values.put("things", key);
			values.put("times", time);
			db.insert(DBHelper.TABLE_NAME, null, values);
			mAdapter.notifyDataSetChanged();
		}
		else if(requestCode == 0x001 && resultCode == 0x001) {
			Bundle bd = data.getExtras();
			String id=String.valueOf(bd.getInt("id"));
			String[] args={id};
			String things = bd.getString("things");
			String time = bd.getString("time");
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("things", things);
			values.put("times", time);
			db.update(DBHelper.TABLE_NAME, values, "_id = ?", args);
			mAdapter.notifyDataSetChanged();
		}
	}
	public static Context getContext(){
        return mContext;
    }
	

}
