package com.qufei.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qufei.adapter.VerifyDataAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.TrueOrFalseJsonResultFactory;
import com.qufei.factory.XtResultFactory;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.QufeiSocketClient;

public class VerifyActivity extends Activity {
	ListView lv;
	ProgressDialog pd;
	String tableName;
	String key;
	String value;
	boolean buttonEnable=true;
	String thyy="";
	String mod;//0��ʾ��ѯ,1��ʾ�޸�
	List<Map<String,String>>data;
	Handler handTuihuiyy=new Handler(){
		public void handleMessage(Message msg) {
			thyy=(String)msg.obj;
		};
	};
	Handler hand =new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				VerifyDataAdapter adapter=new VerifyDataAdapter(VerifyActivity.this, data);
				lv.setAdapter(adapter);
			}else{

				Toast.makeText(VerifyActivity.this, "��������æ", Toast.LENGTH_SHORT).show();
			}	
			if(buttonEnable==false)
			{

				
				VerifyActivity.this.findViewById(R.id.tuihui).setVisibility(View.GONE);
				
				VerifyActivity.this.findViewById(R.id.tongyi).setVisibility(View.GONE);
			}
			pd.dismiss();
		};
	};

	public void dellData(){
		data=new ArrayList<Map<String,String>>();
		pd.show();
		new Thread(new Runnable() {
			Message msg=hand.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSONArray jsa=new JSONArray();
				JSONObject j=new JSONObject();

				try {
					j.put("userID", AppConfig.LoginUser.getUserid());
					j.put("tableName",tableName);
					j.put("key", key);
					j.put("value", value);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsa.put(j);
				XtResultFactory xtFac=new XtResultFactory();
				xtFac.setJsonStr(QufeiSocketClient.getInstance().send("GetDaishenghe", jsa));
				xtFac.createResult();
				

				 
				List<LinkedHashMap<String,String>>mapList=xtFac.getMapList();
				if(mapList==null)
				{
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				Map<String,String>mp=mapList.get(0);
				Set<String>keySet=mp.keySet();
				for(String key:keySet){
					Map<String,String>map=new HashMap<String, String>();
					map.put("key", key);
					if(key.contains("���״̬"))
					{
						if(mp.get(key).equals("�����"))
						{
							buttonEnable=false;
						}
					}
					if(mod.equals("0")){
						//������ѯ
						buttonEnable=false;
					}

					map.put("value", mp.get(key));
					data.add(map);
				}
				if(data!=null){
					msg.what=1;
				}else{
					msg.what=0;
				}
				msg.sendToTarget();

			}
		}){}.start();


	}
	public void verify( Message msg){
		JSONArray jsa=new JSONArray();
		JSONObject j=new JSONObject();

		try {
			j.put("userID", AppConfig.LoginUser.getUserid());
			j.put("tableName",tableName);
			j.put("key", key);
			j.put("value", value);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsa.put(j);
		JsonResultFactory fac=new TrueOrFalseJsonResultFactory();
		fac.setJsonStr(QufeiSocketClient.getInstance().send("Verify", jsa));
		JsonResult verifyResult= fac.createResult();
		if(verifyResult==null)
		{
			msg.what=0;
			msg.sendToTarget();
			return ;
		}
		TrueOrFalseModel model=	(TrueOrFalseModel) verifyResult.getModels().get(0);

		if(model==null)
		{
			msg.what=0;
		}
		else if(model.getIsOk().equals("true"))
		{
			msg.what=1;
		}else {
			msg.what=2;
			msg.obj=model.getIsOk();
		}
		msg.sendToTarget();

	}
	
	Handler handVerify=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==0){
				Toast.makeText(VerifyActivity.this, "��˲�����", Toast.LENGTH_SHORT).show();
			}else if(msg.what==1){
				Toast.makeText(VerifyActivity.this, "��˳ɹ�", Toast.LENGTH_SHORT).show();
			}else if(msg.what==2){
				Toast.makeText(VerifyActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}

		};
	};
	Dialog dialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify);
		pd=new ProgressDialog(this);
		pd.setMessage("���ڻ�ȡ��������");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCancelable(false);
		tableName=getIntent().getStringExtra("tableName");
		key=getIntent().getStringExtra("key");
		value=getIntent().getStringExtra("value");
		mod=getIntent().getStringExtra("mod");


		lv=(ListView)this.findViewById(R.id.lv_verify);		


		dellData();

		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				VerifyActivity.this.finish();
			}
		});
		//ע��Ի���
		final AlertDialog.Builder builderISOK=new Builder(this);
		builderISOK.setMessage("ȷʵҪͨ����?");
		builderISOK.setTitle("��ʾ");
		builderISOK.setPositiveButton("ȷ��", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub
				pd.show();
				new Thread(new Runnable() {
					Message msg=handVerify.obtainMessage();
					@Override
					public void run() {
						// TODO Auto-generated method stub
						verify(msg);
					}
				}){}.start();

				dialog.dismiss();

				VerifyActivity.this.finish();

			}
		});
		builderISOK.setNegativeButton("ȡ��", new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				// TODO Auto-generated method stub



				Toast.makeText(VerifyActivity.this, "ȡ��", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}

		});
		
		this.findViewById(R.id.tuihui).setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				

				dialog=	new AlertDialog.Builder(VerifyActivity.this)
				.setTitle("�������˻ر�ע")

				.setIcon(android.R.drawable.ic_dialog_info)

				.setView(getLayoutInflater().inflate(R.layout.layout_tuihui, null))			 

				.setPositiveButton("ȷ��", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						//Toast.makeText(VerifyActivity.this, "ȷ��", Toast.LENGTH_SHORT).show();
						Message msg=handTuihuiyy.obtainMessage();
						EditText edt=(EditText) dialog.findViewById(R.id.edttuihui);
						msg.obj=edt.getText().toString();
						msg.sendToTarget();
						//����д�˻��¼�
						tuihui();
						

					}
				})
				.setNegativeButton("ȡ��", new AlertDialog.OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						//Toast.makeText(VerifyActivity.this, "ȡ��", Toast.LENGTH_SHORT).show();
					}

				}).show();

				dialog.getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);


			}
		});

		this.findViewById(R.id.tongyi).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				builderISOK.show();
			}
		});

	}
	Handler handTuihui=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{
				Toast.makeText(VerifyActivity.this, "�������˻�", Toast.LENGTH_SHORT).show();
				VerifyActivity.this.finish();
				
			}else
			{
				Toast.makeText(VerifyActivity.this, "��������ʧ��", Toast.LENGTH_SHORT).show();
			}
		};
	};
	public void tuihui(){
		pd.show();
		new Thread(new Runnable() {
			Message msg=handTuihui.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSONArray jsarr=new JSONArray();
				JSONObject j=new JSONObject();
				try {
					j.put("userID", AppConfig.LoginUser.getUserid());
					j.put("thyy", thyy);
					j.put("tableName",tableName);
					j.put("key", key);
					j.put("value", value);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jsarr.put(j);
				JsonResultFactory fac=new TrueOrFalseJsonResultFactory();
				fac.setJsonStr(QufeiSocketClient.getInstance().send("BackVerify", jsarr));
				JsonResult verifyResult= fac.createResult();
				if(verifyResult==null)
				{
					msg.what=0;
					msg.sendToTarget();
					return ;
				}
				TrueOrFalseModel model=	(TrueOrFalseModel) verifyResult.getModels().get(0);

				if(model==null)
				{
					msg.what=0;
				}
				else if(model.getIsOk().equals("true"))
				{
					msg.what=1;
				}else {
					msg.what=2;
					msg.obj=model.getIsOk();
				}
				msg.sendToTarget();
			}
		}){}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}

}
