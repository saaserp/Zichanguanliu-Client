package com.qufei.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.factory.BigClassResultFactory;
import com.qufei.model.BigClassModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class BigClassActivity extends Activity {
	ProgressDialog pd;
	ListView lv;
	List<Model> list;
	List<BigClassModel> bigClassList;
	List<Map<String, String>> data;
	String[] from = { "className", "ct", "je" };
	int[] to = { R.id.Classname, R.id.Count, R.id.je };
	JsonResult json = null;
	JsonResultFactory jsfactory = null;
	String keyWord = null;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.arg1 == 1) {
				SimpleAdapter adapter = new SimpleAdapter(BigClassActivity.this, data, R.layout.layout_big_class_item, from, to);
				lv.setAdapter(adapter);

			}
			if (msg.arg1 == AppConfig.SERVERERROR) {
				Toast.makeText(BigClassActivity.this, AppConfig.SERVERERRORSTR, Toast.LENGTH_LONG).show();
				BigClassActivity.this.finish();
			}
			if (msg.arg1 == AppConfig.NODATAREQUEST) {
				Toast.makeText(BigClassActivity.this, AppConfig.NODATAREQUESTSTR, Toast.LENGTH_LONG).show();
				BigClassActivity.this.finish();
			}

			pd.dismiss();
		}

	};

	public void Serach() {
		pd.show();

		// String url=AppConfig.hostName+"/GetBigClass?keyword="+keyWord;

		// jsfactory.setUrl(url);老板本采用sevlet

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				try {
					JSONArray sjas = new JSONArray();
					JSONObject sj = new JSONObject();
					sj.put("keyWord", keyWord);
					sj.put("userID", AppConfig.LoginUser.getUserid());
					sjas.put(sj);
					jsfactory.setJsonStr(QufeiSocketClient.getInstance().send("GetBigClass", sjas));

					json = jsfactory.createResult();
					if (json.getModels().size() == 0) {
						msg.arg1 = -1;
						msg.sendToTarget();
						return;
					}
				}
				catch (Exception e) {
					msg.arg1 = 0;
					msg.sendToTarget();
					return;
				}
				bigClassList = new ArrayList<BigClassModel>();

				list = json.getModels();
				if (list.size() == 0) {
					msg.arg1 = 0;
					msg.sendToTarget();
					return;
				}
				for (Model md : list) {
					bigClassList.add((BigClassModel) md);
				}

				for (BigClassModel bmd : bigClassList) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("className", bmd.getClassName());
					map.put("ct", bmd.getCt());
					map.put("type", bmd.getType());
					map.put("je", "价值" + bmd.getJe() + "元");
					data.add(map);

				}
				msg.arg1 = 1;
				msg.sendToTarget();
			}
		});
		th.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_big_class);
		keyWord = getIntent().getStringExtra("keyWord");

		pd = new ProgressDialog(this);
		pd.setMessage("正在查询...");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		data = new ArrayList<Map<String, String>>();
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BigClassActivity.this.finish();
			}
		});

		lv = (ListView) this.findViewById(R.id.lstBigClass);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Map<String, String> mp = data.get(arg2);
				// Toast.makeText(BigClassActivity.this, mp.get("type"),Toast.LENGTH_LONG).show();
				Intent intent = new Intent(BigClassActivity.this, SmallClassActivity.class);
				intent.putExtra("keyWord", keyWord);
				intent.putExtra("type", mp.get("type"));
				startActivity(intent);

			}
		});
		jsfactory = new BigClassResultFactory();

		Serach();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.big_class, menu);
		return true;
	}

}
