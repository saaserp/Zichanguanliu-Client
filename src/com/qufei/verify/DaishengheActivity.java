package com.qufei.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.qufei.adapter.DaishengheAdapter;
import com.qufei.androidapp.R;
import com.qufei.mapper.DaishengheVerifyBigTypeRowMapper;
import com.qufei.model.DaishengheVerifyBigTypeModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class DaishengheActivity extends Activity {
ListView lv;
List<Map<String,String>> data;
JsonResultFactory jsfactory;
JsonResult json = null;
ProgressDialog pd;
String mod="1";
public List<Map<String,String>> getData()
{
	data=new ArrayList<Map<String,String>>();
	JSONArray jsa=new JSONArray();
	JSONObject j=new JSONObject();
	try {
		j.put("userID", AppConfig.LoginUser.getUserid());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	jsa.put(j);
	jsfactory=new JsonResultFactory() {
		
		@Override
		public JsonResult createResult() {
			// TODO Auto-generated method stub
			JsonResult json=new JsonResult();
			String str=super.getJsonStr();
			if(str=="")
				return null;
			json.setJsonStr(str);
			json.parse(new DaishengheVerifyBigTypeRowMapper());
			return json;
		}
	};
	jsfactory.setJsonStr(QufeiSocketClient.getInstance().send("GetDaishengheBig", jsa));
	
	json = jsfactory.createResult();
	
	if(json==null){
		return null;
	}
	
	List<Model> modelList=json.getModels();
	
	for(Model m:modelList){
		Map<String,String>map=new HashMap<String, String>();
		map.put("typeName", ((DaishengheVerifyBigTypeModel)m).getTypeName());
		map.put("number",((DaishengheVerifyBigTypeModel)m).getNumber());
		data.add(map);
	}
	
	return data;
	
}
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				Map<String,String>pram=new HashMap<String, String>();
				pram.put("mod",mod);
				DaishengheAdapter adapter=new DaishengheAdapter(DaishengheActivity.this, data,pram);
     			lv.setAdapter(adapter);
			}else{
				 Toast.makeText(DaishengheActivity.this, "服务器正忙，请稍后再试!", Toast.LENGTH_SHORT).show();
			}
			if(pd.isShowing())
			pd.dismiss();
		};
	};
	public void dell()
	{
		pd.show();
		new Thread(new Runnable() {
			Message msg=hand.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				data=getData();
				if(data==null){
					msg.what=0;
				}else{
					msg.what=1;
				}
				msg.sendToTarget();
			}
		}){}.start();		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daishenghe);
		lv=(ListView)this.findViewById(R.id.lv_daishenghe);
		pd=new ProgressDialog(this);
		pd.setMessage("正在获取数据");
		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mod=getIntent().getStringExtra("mod");//查看还是修改
		dell();
		
	 this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			DaishengheActivity.this.finish();
		}
	});
	 
	 this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			dell();
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	 
		return true;
	}

}
