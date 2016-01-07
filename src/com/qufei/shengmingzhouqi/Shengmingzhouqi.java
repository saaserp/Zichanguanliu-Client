package com.qufei.shengmingzhouqi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.androidapp.R;
import com.qufei.androidapp.R.layout;
import com.qufei.androidapp.R.menu;
import com.qufei.tools.QufeiSocketClient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Shengmingzhouqi extends Activity {

	private String id;
	private List<Map<String,String>>data;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shengmingzhouqi);
		id=getIntent().getStringExtra("id");//资产id
		listView=(ListView) this.findViewById(R.id.lv_shengmingzhouqi);
		thread();
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Shengmingzhouqi.this.finish();
			}
		});
	}
	Handler hand =new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0){
				Toast.makeText(Shengmingzhouqi.this, "没有查到数据,请稍后再试", Toast.LENGTH_SHORT).show();
				Shengmingzhouqi.this.finish();
			}else{
				ShengmingzhouqiAdapter adapter=new ShengmingzhouqiAdapter(Shengmingzhouqi.this, data);
				listView.setAdapter(adapter);
			}
		};
	};
	void thread()
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=hand.obtainMessage();
				JSONArray ja=new JSONArray();
				JSONObject jo=new JSONObject();
				data=new ArrayList<Map<String,String>>();
				try {
					jo.put("id",id);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//资产id
				ja.put(jo);

				String result=QufeiSocketClient.getInstance().send("ShengmingzhouqiProcesser",ja);
				if(result==null){
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				try {
					JSONArray jaa=new JSONArray(result);
					for(int i=0;i<jaa.length();i++){
						JSONObject joo=jaa.getJSONObject(i);

 
						
						Map<String,String>map=new LinkedHashMap<String, String>();
						map.put("zcbhv", joo.getString("资产编号"));
						map.put("zcmcv", joo.getString("资产名称"));
						map.put("cznrv", joo.getString("操作内容"));
						map.put("czrv", joo.getString("操作人"));
						map.put("czsjv", joo.getString("操作时间"));
						 
						data.add(map);


					}
					msg.what=1;
					msg.sendToTarget();
					return ;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					msg.what=0;
					msg.sendToTarget();
					return ;
				}

			}
		}){}.start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shengmingzhouqi, menu);
		return true;
	}

}
