package com.qufei.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qufei.androidapp.R;

public class DaishengheAdapter extends BaseAdapter{
	Context context;
	List<Map<String,String>>data;
	Map<String,String>prem;
	String mod="1";//0表示查询,1表示修改
	public DaishengheAdapter(Context context,List<Map<String,String>>data,Map<String,String>pram){
		this.context=context;
		this.data=data;
		this.prem=pram;
		mod=prem.get("mod");
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
	public String changeName(String oldName)
	{
		String newName="";
		if(oldName.contains("sbxt")){
			newName="设备";
		}else if(oldName.contains("jjxt")){
			newName="家具";
		}else if(oldName.contains("tdxt")){
			newName="土地";
		}else if(oldName.contains("fwxt")){
			newName="房屋";
		}else if(oldName.contains("gzxt")){
			newName="构筑";
		}else if(oldName.contains("clxt")){
			newName="车辆";
		}else if(oldName.contains("wwxt")){
			newName="陈列品";
		}else if(oldName.contains("bfxt")){
			newName="被服";
		}else if(oldName.contains("dwxt")){
			newName="动植物";
		}else if(oldName.contains("dzxt")){
			newName="低值";
		}else if(oldName.contains("ckxt")){
			newName="库房";
		}else if(oldName.contains("cgjh")){
			newName="采购物品";
		}
		return newName+oldName.substring(5);


	}
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.item_daishenghe, null);
		TextView itemName=(TextView)convertView.findViewById(R.id.typeName);
		TextView itemValue=(TextView)convertView.findViewById(R.id.count);
		String strName= data.get(arg0).get("typeName");
		String strValue=data.get(arg0).get("number");
		itemName.setText(changeName(strName));
		itemValue.setText(strValue);	
		final String tableName=strName;
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,com.qufei.verify.DiashengheSonActivity.class);
				intent.putExtra("tableName", tableName);
				intent.putExtra("mod", mod);
				context.startActivity(intent);
				
			}
		});
		return convertView;
	}

	 

}
