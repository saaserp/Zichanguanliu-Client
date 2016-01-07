package com.qufei.confirm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.factory.ImageJsonResultFactory;
import com.qufei.factory.XtResultFactory;
import com.qufei.imageloader.ProductImages;
import com.qufei.login.LoginActivity;
import com.qufei.model.ImageModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.NetworkDetector;
import com.qufei.tools.QufeiSocketClient;

public class ConfirmProductInfoActivity extends Activity {

	ListView lst_products;
	RelativeLayout layout;
	List<String>srcList;
	List<Map<String,String>>list;
	String keyword=null;
	ProgressDialog pd;
	 
	ArrayList<String>urlList;

	//对应的map里的键的名字
	String [] from={"name","value"};
	int []to={R.id.item_name,R.id.item_value};
	String url;
	 
	int type=1;
	 
	JsonResult json=null;
	JsonResultFactory jsfactory=null;
	JSONArray mjsa;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_confirm_product_info);
		
		
		
		
	 
		Intent intent=getIntent();
		lst_products=(ListView)this.findViewById(R.id.lst_products);
		 
		pd=new ProgressDialog(this);
		pd.setMessage("正在查询...");
		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//获取关键词
		keyword=intent.getStringExtra("keyWord");
		 
		urlList=new ArrayList<String>();
		String userID=LoginActivity.getmUserID();
		userID="";
		
		mjsa=new JSONArray();
		JSONObject mjson=new JSONObject();
		try {
			mjson.put("userID", userID);
			mjson.put("keyWord", keyword);
		 
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mjsa.put(mjson);
		pd.show();

		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ConfirmProductInfoActivity.this.finish();
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int i=0;i<list.size();i++)
				{
					if(list.get(i).get("name").equals("验收单编号"))
					{
						yanshoudanID=list.get(i).get("value");
					}
				}
				if(yanshoudanID!=""&&yanshoudanID!=null){
				Intent intent=new Intent(ConfirmProductInfoActivity.this,Confirm.class);
				intent.putExtra("yanshoudanID", yanshoudanID);
				startActivity(intent);
				ConfirmProductInfoActivity.this.finish();
				}else
				{
					Toast.makeText(ConfirmProductInfoActivity.this,"参数错误",Toast.LENGTH_SHORT).show();
				}
			}
		});
		 


		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=handler.obtainMessage();
				Map<String,String>map = null;
				Set<String> keSet;
				String keyName;
				try{
					 
					 
				 
				 
					 
					XtResultFactory xtFac=new XtResultFactory();
					JSONArray ja2=new JSONArray();
					JSONObject j2=new JSONObject();
					j2.put("userID", AppConfig.LoginUser.getUserid());
					j2.put("key", keyword);
					ja2.put(j2);
					xtFac.setJsonStr(QufeiSocketClient.getInstance().send("GetProductDetail", ja2));
					xtFac.createResult();					
					map=xtFac.getMapList().get(0);
					
					//
					if(map==null){
						msg.arg1=0;

					}
					keSet=map.keySet();

					list=new ArrayList<Map<String,String>>();
					for (Iterator<String> iterator = keSet.iterator(); iterator.hasNext();) {

						keyName = iterator.next(); 
						Map<String,String>map2=new HashMap<String, String>();
						map2.put("name", keyName);
						map2.put("value", map.get(keyName));

						list.add(map2);
					}
					msg.arg1=1;

				}catch(Exception e){
					msg.arg1=0;
				}


				msg.sendToTarget();
			}
		});
		thread.start();








	}
String yanshoudanID;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			 pd.dismiss();
			if(msg.arg1==1){
				SimpleAdapter adapter=new SimpleAdapter(ConfirmProductInfoActivity.this,list,R.layout.layout_product_info_item,from,to);       
				lst_products.setAdapter(adapter); 
				yanshoudanID=list.get(0).get("验收单编号");
			 
				 if(list.size()==0){
					 Toast.makeText(ConfirmProductInfoActivity.this, "查询不存在", Toast.LENGTH_SHORT).show();
					  
					 ConfirmProductInfoActivity.this.finish();
				 }
			}else{
				
				Toast.makeText(ConfirmProductInfoActivity.this, "查询不存在", Toast.LENGTH_SHORT).show();
				 ConfirmProductInfoActivity.this.finish();
				 
				
			}
			
		}

	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
