package com.qufei.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qufei.androidapp.R;

public class AllProductAdapter extends BaseAdapter{

	Context context;
	List<Map<String,String>>data;
	public AllProductAdapter(Context context,List<Map<String,String>> data){
		this.context=context;
		this.data=data;
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
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.types_item, null);
		TextView tv=(TextView) convertView.findViewById(R.id.tv_name);
		tv.setText(data.get(arg0).get("typeName"));
		
		return convertView;
	}

	
}
