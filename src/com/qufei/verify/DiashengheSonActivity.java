package com.qufei.verify;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.qufei.adapter.DaishengheSonAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.XtResultFactory;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.QufeiSocketClient;
import com.zbar.lib.CaptureActivity;

public class DiashengheSonActivity extends Activity {

	private String tableName;
	private String queryID;
	private ProgressDialog pd;
	private static String jsonArrayStr;
	List<LinkedHashMap<String,String>>data;
	List<Map<String,String>>pram;
	final int QUERYACTIVITYCODE=3;
	String mod="1";
	EditText edtQueryID;
	ListView lv;
	Handler hand =new Handler(){
		public void handleMessage(android.os.Message msg) {

			if(msg.what==1){
				Map<String,String>prame=new HashMap<String, String>();
				prame.put("tableName", tableName);
				prame.put("mod", mod);//0表示查询,1表示修改
			 
				DaishengheSonAdapter adapter=new DaishengheSonAdapter(DiashengheSonActivity.this, data,prame);
				lv.setAdapter(adapter);
			}else{
				//Toast.makeText(DiashengheSonActivity.this,"服务器繁忙,请稍后再试!", Toast.LENGTH_SHORT).show();
			}
			sprinner.setEnabled(true);
			pd.dismiss();
		};
	};
	public void query(  String queryID){
		final String mqueryID=queryID;
		sprinner.setSelection(0);
		data=new ArrayList<LinkedHashMap<String,String>>();
		 	final int condi=sprinner.getSelectedItemPosition();
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
					j.put("tableName", tableName);
					j.put("value", mqueryID);
					j.put("condition", condi+"");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsa.put(j);
				XtResultFactory xtFac=new XtResultFactory();
				xtFac.setJsonStr(QufeiSocketClient.getInstance().send("GetDaishengheSonByID", jsa));
				json=xtFac.createResult();
				if(json==null)
				{
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				jsonArrayStr=json.getJsonStr();
				Log.i("jsonArrayStr",jsonArrayStr);
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
	
	public void receive(){
		data=new ArrayList<LinkedHashMap<String,String>>();
		sprinner.setEnabled(false);
		pd.show();
	final	int condition=sprinner.getSelectedItemPosition();
		
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
					j.put("tableName", tableName);
					j.put("condition", condition);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsa.put(j);
				XtResultFactory xtFac=new XtResultFactory();
				xtFac.setJsonStr(QufeiSocketClient.getInstance().send("GetDaishengheSon", jsa));
				json=xtFac.createResult();
				if(json==null)
				{
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				jsonArrayStr=json.getJsonStr();
				Log.i("jsonArrayStr",jsonArrayStr);
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
	private Spinner sprinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diashenghe_son);
		tableName=getIntent().getStringExtra("tableName");
		mod=getIntent().getStringExtra("mod");
		pd=new ProgressDialog(this);
		pd.setMessage("正在刷新数据");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		lv=(ListView)findViewById(R.id.lv_daishenghe_son);
		edtQueryID=(EditText)this.findViewById(R.id.edt_keywords);
		sprinner=(Spinner)this.findViewById(R.id.Spinner01);
		sprinner.setOnItemSelectedListener(new OnItemSelectedListener() {

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
		
		this.findViewById(R.id.submit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				queryID=edtQueryID.getText().toString();
				query(queryID);
			}
		});
		receive();
		 this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DiashengheSonActivity.this.finish();
				}
			});
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
					Intent intent=new Intent(DiashengheSonActivity.this,CaptureActivity.class);
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

				edtQueryID.setText(data.getStringExtra("codestr"));
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
