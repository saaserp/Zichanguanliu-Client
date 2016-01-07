package com.qufei.confirm;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.factory.TrueOrFalseJsonResultFactory;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class Confirm extends Activity {

	 TextView time;
	 EditText edt;
	 String yanshoudanID;
	 ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm);
		Calendar c = Calendar.getInstance();  
		int year = c.get(Calendar.YEAR);  
        int month = c.get(Calendar.MONTH);  
        int day = c.get(Calendar.DAY_OF_MONTH);  
        pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("正在操作");
        pd.setCancelable(false);
        yanshoudanID=getIntent().getStringExtra("yanshoudanID");
        
        time=(TextView) this.findViewById(R.id.time);
        edt=(EditText)this.findViewById(R.id.editText1);
        String timeStr=year+"-"+(month+1)+"-"+day;
        
        time.setText(timeStr);
        this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Confirm.this.finish();
			}
		});
        this.findViewById(R.id.rollback).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        this.findViewById(R.id.rollback).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 undo();
			}
		});
	}
  public   Handler confirmHandler=new Handler(){
	public void handleMessage(android.os.Message msg) {
		String timeStr= (String)msg.obj;
		 time.setText(timeStr);
	};
};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.confirm, menu);
		return true;
	}
	public void showDatePickerDialog(View view){  
	    DatePickerFragment datePicker = new DatePickerFragment(this);  
	    datePicker.show(getFragmentManager(), "datePicker");  
	}  
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				Toast.makeText(Confirm.this, "操作成功", Toast.LENGTH_SHORT).show();
				
			}else{
				Toast.makeText(Confirm.this, "操作失败,请检查网络连接", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};
	 public void undo(){
		 pd.show();
		 
			new Thread(new Runnable() {
				Message msg=hand.obtainMessage();
				@Override
				public void run() {
					// TODO Auto-generated method stub
					JSONArray jsa=new JSONArray();
					JSONObject jo=new JSONObject();
					 
					
					try {
						jo.put("yanshoudanID", yanshoudanID);
					
						jo.put("userID", AppConfig.LoginUser.getUserid());
						jo.put("option", "undo");
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
					
					 jsa.put(jo);
					
				TrueOrFalseJsonResultFactory fac=	(TrueOrFalseJsonResultFactory) new TrueOrFalseJsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("Confirm", jsa));
			 
				JsonResult re= fac.createResult() ;
				 if(re==null)
				 {
					 msg.what=0;
					 msg.sendToTarget();
					 return ;
					 
				 }
				TrueOrFalseModel mod= (TrueOrFalseModel) re.getModels().get(0);
				
					if(mod.getIsOk().equals("true"))
					{
						msg.what=1;
					}else
					{
						msg.what=0;
					}
					msg.sendToTarget();
				}
			}){}.start();
		 
	 }
	public void updateInfo(View view){
		
		final String caiwupingdanID=edt.getText().toString();
		final String times=time.getText().toString();
		pd.show();
		new Thread(new Runnable() {
			Message msg=hand.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSONArray jsa=new JSONArray();
				JSONObject jo=new JSONObject();
				 
				
				try {
					jo.put("yanshoudanID", yanshoudanID);
					jo.put("caiwupingdanID", caiwupingdanID);
					jo.put("userID", AppConfig.LoginUser.getUserid());
					jo.put("userName", AppConfig.LoginUser.getUsername());
					jo.put("time", times);
					jo.put("option", "do");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
				
				 jsa.put(jo);
				
				 TrueOrFalseJsonResultFactory fac=(TrueOrFalseJsonResultFactory) new TrueOrFalseJsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("Confirm", jsa));
				 
				 JsonResult re= fac.createResult() ;
				 if(re==null)
				 {
					 msg.what=0;
					 msg.sendToTarget();
					 return ;
					 
				 }
				TrueOrFalseModel mod= (TrueOrFalseModel) re.getModels().get(0);
				if(mod.getIsOk().equals("true"))
				{
					msg.what=1;
				}else
				{
					msg.what=0;
				}
				msg.sendToTarget();
			}
	 
		}){}.start();
	}

}
