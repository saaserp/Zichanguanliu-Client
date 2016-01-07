package com.qufei.addproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.qufei.adapter.AddedListAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.AddedProductJSONResultFactory;
import com.qufei.mapper.AddedProductRowMapper;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class AddedProduct extends Activity {
	List<Map<String,String>>data;
	public static ListView lvAdded;
	AddedListAdapter adapter;
	ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_added_product);
		lvAdded=(ListView) this.findViewById(R.id.tv_added);
		
		pd=new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		pd.setMessage("正在加载数据");
		loadData();
		
this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AddedProduct.this.finish();
			}
		});
this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		loadData();
	}
});
		
	}
	Handler   hand =new Handler(){
		public void handleMessage(android.os.Message msg) {
			//data=(List<Map<String,String>>)msg.obj;
			if(msg.what==1){
				adapter=new AddedListAdapter(AddedProduct.this, data);
				lvAdded.setAdapter(adapter);
				
			}else if(msg.what==2){
				Toast.makeText(AddedProduct.this, "您还没有录入信息", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(AddedProduct.this, "数据获取失败，请重试", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
			
		};
	};
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK){
			loadData();
		}else{
			Toast.makeText(this, "未做任何变动", Toast.LENGTH_SHORT).show();
		}
	};
	public   void loadData(){
		pd.show();
		new Thread(new Runnable() {
			Message msg=hand.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
						
				JsonResultFactory fac=new AddedProductJSONResultFactory();
				JSONArray jsa=new JSONArray();
				JSONObject j=new JSONObject();
				try {
					j.put("userID", AppConfig.LoginUser.getUserid());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsa.put(j);
				 
				
				fac.setJsonStr( QufeiSocketClient.getInstance().send("GetAddedList",jsa));
				JsonResult res=fac.createResult();
				if(res==null)
				{
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				msg=hand.obtainMessage();
				List<Model>mdlst=res.getModels();
				if(mdlst==null){
					msg.what=0;
					msg.sendToTarget();
				}
				
				List<Map<String,String>> data=new ArrayList<Map<String,String>>();
				for(Model m:mdlst){
					Map<String,String> mp=new AddedProductRowMapper().mappingRow(m);
					data.add(mp);
				}
				if(data.size()==0){
					msg.what=2;
				}else{
					msg.what=1;
					AddedProduct.this.data=data;
				}
				msg.sendToTarget();
			}
		}){}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.added_product, menu);
		return true;
	}

}
