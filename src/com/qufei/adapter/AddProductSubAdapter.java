package com.qufei.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qufei.addproduct.AddProductActivity;
import com.qufei.addproduct.SetKeyValue;
import com.qufei.androidapp.R;

public class AddProductSubAdapter extends BaseAdapter {

	private List<Map<String,String>> data;
	Context mContext;
	
	public AddProductSubAdapter(Context context, List<Map<String,String>> data){
		
		 mContext = context;
		this.data = data;
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
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
	
		{
		 convertView = LayoutInflater.from(mContext).inflate(com.qufei.androidapp.R.layout.item_add_product_txt, null);
		
		 TextView txt_Key=(TextView) convertView.findViewById(R.id.txt_key);
		 TextView txt_value=(TextView) convertView.findViewById(R.id.txt_value);
		 txt_Key.setText((CharSequence) data.get(arg0).get("keyName"));
		 txt_value.setText((CharSequence) data.get(arg0).get("keyValue"));
		}
		final int pos=arg0;
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,SetKeyValue.class);
				intent.putExtra("key", "新名称");
				intent.putExtra("value", "值");
				intent.putExtra("pos", pos);
				((Activity) mContext).startActivityForResult(intent,AddProductActivity.SETKEYVALUEACTIVITYCODE );
				
			}
		});
		convertView.setOnLongClickListener(new OnLongClickListener() {
			
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				 AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);  
                 dialog.setTitle("Dialog").setIcon(android.R.drawable.ic_dialog_info).setMessage("是否删除该项").setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                    
                  @Override  
                  public void onClick(DialogInterface dialog, int which) {  
                      //转跳到另外一个Activity   
                      // TODO Auto-generated method stub
                	  AddProductActivity.productSubData.remove(pos);
                	  AddProductActivity.addProductSubAdapter=new AddProductSubAdapter(mContext, AddProductActivity.productSubData);
                	  AddProductActivity.lvSub.setAdapter(AddProductActivity.addProductSubAdapter);
              		//让listView显示最后
                	  AddProductActivity.lvSub.smoothScrollToPosition(AddProductActivity.productSubData.size() - 1);
                     
                  }  
              }).setNegativeButton("取消", new DialogInterface.OnClickListener() {  
                    
                    
                  public void onClick(DialogInterface dialog, int which) {  
                      // TODO Auto-generated method stub   
                      dialog.cancel();//取消弹出框   
                  }  
              }).create().show();  
              
				return false;
			}
		});
		 
		return convertView;
	}

}
