package com.qufei.adapter;

import java.util.List;
import java.util.Map;

import com.qufei.androidapp.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VerifyDataAdapter  extends BaseAdapter{
	Context context;
	List<Map<String,String>>data;
	public VerifyDataAdapter(Context context,List<Map<String,String>> data){
		this.data=data;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stubd
		convertView = LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.verify_item, null);
		TextView item_name=(TextView)convertView.findViewById(R.id.item_name);
		TextView item_value=(TextView)convertView.findViewById(R.id.item_value);
		
		String str_name=data.get(arg0).get("key");
		String str_value=data.get(arg0).get("value");
		Log.i("verify","name:"+str_name);
		Log.i("verify","value:"+str_value);
		item_name.setText(str_name);
		item_value.setText(str_value);
		
		return convertView;
	}


}
