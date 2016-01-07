package com.qufei.addproduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.qufei.adapter.AllProductAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.TypeJsonResultFactory;
import com.qufei.model.TypeModel;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class ProductTypeActivity extends Activity {
	ProgressDialog pd;

	ListView lv;
	List<Map<String,String>>data;
	AllProductAdapter adp1;
	int pos;
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				adp1=new AllProductAdapter(ProductTypeActivity.this,data);

				lv.setAdapter(adp1);
			}else
			{
				Toast.makeText(ProductTypeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};

	private String keyName;

	private String type;

	private String keyValueOld;

	private String optional;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_type);
		data=new ArrayList<Map<String,String>>();
		lv=(ListView) this.findViewById(R.id.lvAllType);
		pd=new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在获取数据...");
		pd.show();
		keyName=getIntent().getStringExtra("name");
		type=getIntent().getStringExtra("type");
		pos=getIntent().getIntExtra("pos", -1);
		keyValueOld=getIntent().getStringExtra("default");
		optional=getIntent().getStringExtra("optional");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=hand.obtainMessage();
				try{
					data=doThread();
					msg.what=1;
				}catch(Exception e){
					msg.what=0;
				}
				msg.sendToTarget();

			}
		}){}.start();
		this.findViewById(R.id.title_left
				).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ProductTypeActivity.this.finish();
					}
				});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Uri dataUrl=Uri.parse("");

				Intent intent=new Intent(null,dataUrl);
				 
				 
				intent.putExtra("name", keyName);
				intent.putExtra("default", data.get(arg2).get("typeName"));
				intent.putExtra("pos", pos);
				intent.putExtra("type", type);
				intent.putExtra("optional", optional);
				
				
				setResult(RESULT_OK,intent);
				ProductTypeActivity.this.finish();
			}
		});
		
	}
	List<Map<String,String>> doThread(){

		JsonResult js=new TypeJsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("GetAllType","_")).createResult();
		List<Model> list=js.getModels();
		List<Map<String,String>>maplist=new ArrayList<Map<String,String>>();
		for(Model md:list){
			Map<String,String>map=new HashMap<String, String>();
			map.put("typeCode",((TypeModel)md).getPrefix());
			map.put("typeName",((TypeModel)md).getName());
			map.put("typeNo", ((TypeModel)md).getTypeNo());
			
			Log.i("ProductTypeActivity","ProductTypeActivity->typeNo:"+((TypeModel)md).getTypeNo());
			maplist.add(map);
		}
		return maplist;


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_type, menu);
		return true;
	}

}
