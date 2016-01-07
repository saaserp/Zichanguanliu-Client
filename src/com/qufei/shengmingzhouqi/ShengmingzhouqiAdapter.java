package com.qufei.shengmingzhouqi;

import java.util.List;
import java.util.Map;

import com.qufei.androidapp.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShengmingzhouqiAdapter extends BaseAdapter{
	
	List<Map<String,String>> list;
	int group=0;
	Context context;
	ShengmingzhouqiAdapter(Context context ,List<Map<String,String>> list){
		this.list=list;
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		
		// TODO Auto-generated method stub
		view=LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.item_shengmingzhouqi2, null);
		TextView zcbhv=(TextView) view.findViewById(R.id.zcbhv);
		TextView zcmcv=(TextView) view.findViewById(R.id.zcmcv);
		TextView cznrv=(TextView) view.findViewById(R.id.cznrv);
		TextView czrv=(TextView) view.findViewById(R.id.czrv);
		TextView czsjv=(TextView) view.findViewById(R.id.czsjv);
		
		zcbhv.setText(list.get(arg0).get("zcbhv").toString());
		zcmcv.setText(list.get(arg0).get("zcmcv").toString());
		cznrv.setText(list.get(arg0).get("cznrv").toString());
		czrv.setText(list.get(arg0).get("czrv").toString());
		czsjv.setText(list.get(arg0).get("czsjv").toString());
		
		
		
		
		return view;
	}

}
