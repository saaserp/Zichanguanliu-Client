package com.qufei.confirm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.adapter.DaishengheSonAdapter;
import com.qufei.adapter.YanshoudanListAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.XtResultFactory;
import com.qufei.query.QueryActivity;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.QufeiSocketClient;
import com.zbar.lib.CaptureActivity;

public class YanshoudanListActivity extends Activity {

	 
	private ProgressDialog pd;
	private static String jsonArrayStr;
	List<LinkedHashMap<String,String>>data;
	List<Map<String,String>>pram;
	 Spinner sp;
  final int  QUERYACTIVITYCODE=3;
	ListView lv;
	Handler hand =new Handler(){
		public void handleMessage(android.os.Message msg) {

			if(msg.what==1){
				Map<String,String>prame=new HashMap<String, String>();
				
				YanshoudanListAdapter adapter=new YanshoudanListAdapter(YanshoudanListActivity.this, data,prame);
				//DaishengheSonAdapter adapter=new DaishengheSonAdapter(YanshoudanListActivity.this, data,prame);
				lv.setAdapter(adapter);
			}else{
				Toast.makeText(YanshoudanListActivity.this,"服务器繁忙,请稍后再试!", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};
	
	public void receive(){
		final String key= tv_query.getText().toString();
		final int condition=sp.getSelectedItemPosition();
		data=new ArrayList<LinkedHashMap<String,String>>();
		pd.show();
		new Thread(new Runnable() {

			Message msg=hand.obtainMessage();
			private JsonResult json;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSONArray jsa=new JSONArray();
				JSONObject j=new JSONObject();

				try {
					j.put("userID", AppConfig.LoginUser.getUserid());
					j.put("key", key);
					j.put("condition", condition);
					 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsa.put(j);
				XtResultFactory xtFac=new XtResultFactory();
				xtFac.setJsonStr(QufeiSocketClient.getInstance().send("Getyanshoudan", jsa));
				json=xtFac.createResult();
				if(json==null){
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				jsonArrayStr=json.getJsonStr();
				
				if(jsonArrayStr==null){
					msg.what=0;
					msg.sendToTarget();
				}
				data=xtFac.getMapList();
				if(data!=null){
					msg.what=1;
				}else{
					msg.what=0;
				}
				msg.sendToTarget();

			}
		}){}.start();
	}
	TextView tv_right;
	TextView tv_query;
	TextView Query;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yanshoudan_list);
		
		pd=new ProgressDialog(this);
		pd.setMessage("正在刷新数据");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		tv_query=(TextView) this.findViewById(R.id.edt_keywords);
		
		lv=(ListView)findViewById(R.id.listviewyanshoudan);
		
		Query=(TextView)this.findViewById(R.id.submit);
		sp=(Spinner) this.findViewById(R.id.Spinner02);
		receive();
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				receive();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				receive();
			}
		});
		 this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					YanshoudanListActivity.this.finish();
				}
			});
		   tv_right=(TextView) this.findViewById(R.id.title_right);
		   tv_right.setText("刷新");
			 this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					receive();
				}
			});
			 
			 
			 this.findViewById(R.id.imageView1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(YanshoudanListActivity.this,CaptureActivity.class);
					startActivityForResult(intent, QUERYACTIVITYCODE);
				}
			});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		receive();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case QUERYACTIVITYCODE:{
			if(resultCode==Activity.RESULT_OK){

				tv_query.setText(data.getStringExtra("codestr"));
			}
			else
				//Toast.makeText(CircleActivity.this, "未登录", Toast.LENGTH_LONG).show();;
				;
		}
		break;
		default:
			break;
		}
	}
	

}
