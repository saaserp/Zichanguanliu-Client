package com.qufei.addproduct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.factory.AddProductResultFacory;
import com.qufei.factory.SelectTypeResultFacory;
import com.qufei.model.ProductModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class SelectTypeActivity extends Activity {
	private ProgressDialog pd;
	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private Map<String,Object>SondataMap;
	// 图片封装为一个数组
	//private int[] icon = { R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6,R.drawable.f7,R.drawable.f8,R.drawable.f9,R.drawable.f10,R.drawable.f11,R.drawable.f12};

	//dilog测试
	String[] items ;

	//dilog测试
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    // TODO Auto-generated method stub
	    super.onConfigurationChanged(newConfig);
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        // 什么都不用写
	    }
	    else {
	        // 什么都不用写
	    }
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_type);
		gview = (GridView) findViewById(R.id.gview);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		pd=new ProgressDialog(this);
		pd.setCancelable(false);
		pd.setMessage("正在加载数据...");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.show();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				data_list= getData();
				Message msg=hand.obtainMessage();
				if(data_list==null){
					msg.what=0;
				}else{
				
					msg.what=1;
				}
				msg.sendToTarget();
			}
		}){}.start();

		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			SelectTypeActivity.this.finish();
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SelectTypeActivity.this,AddedProduct.class);
				startActivity(intent);
			}
		});
		
	}
	Handler hand =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==0){
				Toast.makeText(SelectTypeActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
			}else{
				//新建适配器
				String [] from ={"image","text"};
				int [] to = {R.id.image,R.id.text};
				sim_adapter = new SimpleAdapter(SelectTypeActivity.this, data_list, R.layout.select_type_item, from, to);
				//配置适配器
				gview.setAdapter(sim_adapter);
				gview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder = new AlertDialog.Builder(SelectTypeActivity.this);
						builder.setIcon(R.drawable.logo);
						builder.setTitle("选择一个项");
						final int pos=arg2;
						final int mpos=Integer.parseInt(data_list.get(pos).get("pTypeID").toString().trim());
						final List<Map<String,String>> ls=(List<Map<String,String>>)SondataMap.get(mpos+"");
						if(ls==null){
							Toast.makeText(SelectTypeActivity.this, "没有子项", Toast.LENGTH_SHORT).show();
							return ;
						}
						items=new String[ls.size()];
						for(int i=0;i<ls.size();i++){
							items[i]=ls.get(i).get("typeName");
						}
						if(items.length==1)
						{
							Intent intent=new Intent(SelectTypeActivity.this,AddProductActivity.class);
							intent.putExtra("title", ls.get(0).get("typeName"));
							intent.putExtra("attribute", ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(0).get("attribute"));
							intent.putExtra("pTypeID", ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(0).get("pTypeID"));
							String pTypeID= ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(0).get("pTypeID");
							Log.i("attribute",((List<Map<String,String>>)SondataMap.get(mpos+"")).get(0).get("attribute"));
							Log.i("pTypeID",pTypeID);
							
							startActivity(intent);	
						}else{
						builder.setItems(items, new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int which) {
								// TODO Auto-generated method stub
								//Toast.makeText(SelectTypeActivity.this, "你选择了"+pos+"选择的子项为：" + items[which], Toast.LENGTH_SHORT).show();
								Intent intent=new Intent(SelectTypeActivity.this,AddProductActivity.class);
								intent.putExtra("title", ls.get(which).get("typeName"));
								intent.putExtra("attribute", ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(which).get("attribute"));
								intent.putExtra("pTypeID", ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(which).get("pTypeID"));
								String pTypeID= ((List<Map<String,String>>)SondataMap.get(mpos+"")).get(which).get("pTypeID");
								Log.i("attribute",((List<Map<String,String>>)SondataMap.get(mpos+"")).get(which).get("attribute"));
								Log.i("pTypeID",pTypeID);
								startActivity(intent);
							}
						});
						builder.show();
						}
					}

				});
			}
			pd.dismiss();
		}

	};
	private List<Map<String,Object>> getData(){

		SelectTypeResultFacory fac=new SelectTypeResultFacory();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		JSONArray jsa=new JSONArray();
		JSONObject  jo=new JSONObject();
		try {
			jo.put("userID", AppConfig.LoginUser.getUserid());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		jsa.put(jo);
		try{
		fac.setJsonStr(QufeiSocketClient.getInstance().send("GetAllFatherType",jsa));
		}catch(Exception e){
		return null;
		}
		JsonResult s=fac.createResult();
		if(s==null)
		{
			
			return null;
		}
		List<Model>m=s.getModels();
		if(m==null){
			return null;
		}

		int i=0;
		for(Model m0:m){

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", getResourceByReflect("image"+((ProductModel)m0).getpTypeID().trim()));
			map.put("text", ((ProductModel)m0).getTypeName());
			map.put("pTypeID", ((ProductModel)m0).getpTypeID()); 
			list.add(map);
		}
		//获取子项
		fac=new SelectTypeResultFacory();
		List<Map<String,String>> list2=new ArrayList<Map<String,String>>();
		JSONArray jsa2=new JSONArray();
		JSONObject  jo2=new JSONObject();
		try {
			jo2.put("userID", AppConfig.LoginUser.getUserid());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		jsa2.put(jo2);
		fac.setJsonStr(QufeiSocketClient.getInstance().send("GetAllSonType",jsa2));
		List<Model>m2=fac.createResult().getModels();
		SondataMap =new HashMap<String, Object>();
		for(Model m00:m2){
			List<Map<String,String>>list0=new ArrayList<Map<String,String>>();
			Map<String,String>map=new HashMap<String, String>();
			String father=((ProductModel)m00).getFather();
			map.put("pTypeID", ((ProductModel)m00).getpTypeID());
			map.put("attribute", ((ProductModel)m00).getAttribute());
			map.put("typeName", ((ProductModel)m00).getTypeName());
			
			
			if(!SondataMap.containsKey(father)){
				    list0.add(map);
					SondataMap.put(father,list0);
			}
			else{
				List<Map<String,String>>list1=(List<Map<String,String>>)SondataMap.get(father);
				list1.add(map);
				SondataMap.remove(father);
				SondataMap.put(father,list1);
			}
		}

		return list;
	}

	public int getResourceByReflect(String imageName){  
	    Class drawable  =  R.drawable.class;  
	       Field field = null;  
	       int r_id ;  
	       try {  
	           field = drawable.getField(imageName);  
	           r_id = field.getInt(field.getName());  
	       } catch (Exception e) {  
	        r_id=R.drawable.logo;  
	           Log.e("ERROR", "PICTURE NOT　FOUND！");  
	       }  
	       return r_id;  
	}  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_type, menu);
		return true;
	}
	//	public List<Map<String, Object>> getData(){		
	//		//cion和iconName的长度是相同的，这里任选其一都可以
	//		for(int i=0;i<icon.length;i++){
	//			Map<String, Object> map = new HashMap<String, Object>();
	//			map.put("image", icon[i]);
	//			map.put("text", iconName[i]);
	//			data_list.add(map);
	//		}
	//			
	//		return data_list;
	//	}


}
