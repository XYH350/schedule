package com.example.schedule;

import java.util.LinkedList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventAdapter extends BaseAdapter{
	private LinkedList<Event> mData;
    private Context mContext;
    public EventAdapter(LinkedList<Event> mData,Context mContext) {
    	this.mData = mData;
        this.mContext = mContext;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item,parent,false);
		
		CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);
		check.setChecked(mData.get(position).isCheck());
		check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {		
			@Override
			public void onCheckedChanged(CompoundButton compoundbutton, boolean flag) {
				// TODO Auto-generated method stub
				String s= String.valueOf(mData.get(position).getId());
				String[] args={s};
				DBHelper mOpenHelper=new DBHelper(MainActivity.getContext());
				ContentValues cv = new ContentValues();
				cv.put("ischeck", flag?1:0);
				SQLiteDatabase db = mOpenHelper.getWritableDatabase();
				db.update(DBHelper.TABLE_NAME, cv, "_id = ? ",args); 
			}
		});
        TextView txt_things = (TextView) convertView.findViewById(R.id.text_things);
		txt_things.setText(mData.get(position).getThings());
		TextView text_time = (TextView) convertView.findViewById(R.id.text_time);
		String tm=mData.get(position).getData();
		if(tm==""||tm==null)
		{
			text_time.setVisibility(8);
		}
		text_time.setText(mData.get(position).getData());

		return convertView;
	}

}
