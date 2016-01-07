package com.qufei.adapter;

import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.addproduct.AddProductActivity;
import com.qufei.addproduct.AddedProduct;
import com.qufei.androidapp.R;
import com.qufei.factory.TrueOrFalseJsonResultFactory;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.tools.JsonResult;
import com.qufei.tools.QufeiSocketClient;

public class AddedListAdapter extends BaseAdapter{
	Context context;
	List<Map<String,String>> data;
	ProgressDialog pd;
	public AddedListAdapter(Context context,List<Map<String,String>> data){
		this.context=context;
		this.data=data;
		pd=new ProgressDialog(context);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在加载");
		pd.setCancelable(false);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup arg2) {
		
		if(convertView!=null){
			return convertView;
		}
		 convertView = LayoutInflater.from(context).inflate(com.qufei.androidapp.R.layout.item_added_product, null);
		 TextView tv_product_id=(TextView) convertView.findViewById(R.id.product_id);
		 TextView tv_product_name=(TextView) convertView.findViewById(R.id.product_name);
		 TextView tv_add_time=(TextView) convertView.findViewById(R.id.add_time);
		 
		
		 final String  pTypeID=data.get(index).get("pTypeID");
		 tv_add_time.setText(data.get(index).get("writeTime"));
		 String att=data.get(index).get("attribute");
		 String name="";
		 String id = null;
		 try {
			JSONArray jsa=new JSONArray(att);
			int n=0,m = 0;
			for(int i=jsa.length()-1;i>=0;i--){
				if(jsa.getString(i).contains("名称")){
					n=i;
				}
				if(jsa.getString(i).contains("编号")){
					m=i;
				}
			}
			//名称
			 JSONObject j=jsa.getJSONObject(n);
			 //编号
			 JSONObject no=jsa.getJSONObject(m);
			 //名称
			 id=no.getString("default");
			 //编号
			 name=j.getString("default");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 tv_product_id.setText(id);
		 TextView tv_type=(TextView)convertView.findViewById(R.id.product_type);
		 tv_type.setText(data.get(index).get("typeName"));
		 tv_product_name.setText(name);
		 final int pos=index;
		 convertView.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				
				
				Intent intent=new Intent(context,AddProductActivity.class);
				
				
				intent.putExtra("pTypeID", pTypeID);
				intent.putExtra("attribute", data.get(pos).get("attribute"));
				intent.putExtra("id", data.get(pos).get("id"));
				intent.putExtra("mod", 1);
				Log.i("attribute",pos+"");
				Log.i("attribute",data.get(pos).get("attribute"));
				((Activity)context).startActivityForResult(intent, 0);
			}
		});	
	convertView.setOnLongClickListener(new OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder dialog=new AlertDialog.Builder(context);
	                   dialog.setTitle("提示").setIcon(android.R.drawable.ic_dialog_info).setMessage("是否要删除该记录").setPositiveButton("确定", new DialogInterface.OnClickListener() {
	                     Handler hand=new Handler(){
	                    	 public void handleMessage(android.os.Message msg) {
	                    		 if(msg.what==1){
	                    			 data.remove(pos);
	                    			  AddedProduct.lvAdded.setAdapter( AddedListAdapter.this);
	     	                    	AddedListAdapter.this.notifyDataSetChanged();
	     	                    	Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();
	                    		 }else if(msg.what==0){
	                    			 Toast.makeText(context, "删除失败！", Toast.LENGTH_SHORT).show();
	                    		 }
	                    		 else if(msg.what==2){
	                    			 Toast.makeText(context, "请检查网络！", Toast.LENGTH_SHORT).show();
	                    		 }
	                    	 };
	                     };
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        //转跳到另外一个Activity
	                        // TODO Auto-generated method stub
	                    	new Thread(new Runnable() {
								Message msg=hand.obtainMessage();
								@Override
								public void run() {
									// TODO Auto-generated method stub
									TrueOrFalseJsonResultFactory jf=new TrueOrFalseJsonResultFactory();
									JSONArray jsa=new JSONArray();
									JSONObject j=new JSONObject();
									try {
										if(pos>data.size()){
											msg.what=0;
											msg.sendToTarget();
										}
										j.put("ID", data.get(pos).get("id"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsa.put(j);	
									jf.setJsonStr(QufeiSocketClient.getInstance().send("DeleteProduct",jsa));
								  
									 	JsonResult re=jf.createResult();
									 	if(re==null)
									 	{
									 		msg.what=2;
											msg.sendToTarget();
											return ;
									 		
									 	}
									 	TrueOrFalseModel m=(TrueOrFalseModel) re.getModels().get(0);
									if(m==null){
										msg.what=2;
										msg.sendToTarget();
									}
									if(m.getIsOk().equals("true")){
										msg.what=1;
									}
									else{
										msg.what=0;
									}
									msg.sendToTarget();
									
									
								}
							}){
	                    		
	                    	}.start();
	                    	
	                        
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
	public void getData(){
		pd.show();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}){}.start();
	}


}
