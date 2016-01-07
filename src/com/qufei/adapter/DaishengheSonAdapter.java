package com.qufei.adapter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qufei.androidapp.R;
import com.qufei.verify.VerifyActivity;

public class DaishengheSonAdapter  extends BaseAdapter{
	Context context;
	 Map<String,String> prame;
	List<LinkedHashMap<String,String>>data;
	String mod="0";//0表示只能查询,1表示可以修改
	String tableName;
	String key;
	String value;
	public DaishengheSonAdapter(Context context,List<LinkedHashMap<String,String>> data,Map<String,String>prame){
	this.context=context;
	this.data=data;
	this.prame=prame;
	tableName=prame.get("tableName");
	mod=prame.get("mod");
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
		view=LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.verify_son_item, null);
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
		tvState.setText(data.get(index).get("审核状态"));
		 final String fKey=key;
		 final String fValue=value;
		 
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,VerifyActivity.class);
				intent.putExtra("tableName", tableName);
				intent.putExtra("key", fKey);
				intent.putExtra("value", fValue);
				intent.putExtra("mod", mod);
				context.startActivity(intent);
			
			}
		});
		 
		
		
		return view;
	}

	

}
