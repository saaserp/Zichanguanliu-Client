package com.qufei.adapter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qufei.androidapp.R;
import com.qufei.confirm.ConfirmProductInfoActivity;

public class YanshoudanListAdapter  extends BaseAdapter{
	Context context;
	Map<String,String> prame;
	List<LinkedHashMap<String,String>>data;
	 
	String key;
	String value;
	public YanshoudanListAdapter(Context context,List<LinkedHashMap<String,String>> data,Map<String,String>prame){
	this.context=context;
	this.data=data;
	this.prame=prame;
	 
	
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
	public View getView(int index, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		view=LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.item_yanshoudan, null);
		TextView tvIDName=(TextView) view.findViewById(R.id.item_name);
		TextView tvIDValue=(TextView)view.findViewById(R.id.tv_id);
		TextView tvPrice=(TextView)view.findViewById(R.id.tv_price);
		TextView tvName=(TextView)view.findViewById(R.id.tv_name);
		TextView tvTime=(TextView)view.findViewById(R.id.tv_shijian);
		TextView tvState=(TextView)view.findViewById(R.id.state);
		
		String strTvIDName="";
		Set<String>keySet=data.get(index).keySet();
		for(String str:keySet){
			if(str.contains("编号")){
			strTvIDName=str;
			break;
			}
		}
		key=strTvIDName;
		
		
		
		tvIDName.setText(key);
		
		value=data.get(index).get(key);

		tvIDValue.setText(value);
		tvPrice.setText(data.get(index).get("合计"));
		tvName.setText(data.get(index).get("填报人"));
		tvTime.setText(data.get(index).get("生成日期"));
		String state=data.get(index).get("财务凭单号");
		if(state==null||state.equals("")){
			tvState.setText("未对账");
			tvState.setBackgroundColor(Color.RED);
		}else
		{
			tvState.setText("已对账");	
			tvState.setBackgroundColor(Color.GREEN);
		}
		 final String fValue=value;
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,ConfirmProductInfoActivity.class);
				
				intent.putExtra("keyWord", fValue);
				
				context.startActivity(intent);
			
			}
		});
		
		
		return view;
	}

	

}
