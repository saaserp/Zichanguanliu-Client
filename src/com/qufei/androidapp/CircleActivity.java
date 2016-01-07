package com.qufei.androidapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.util.h;

import com.qufei.jpush.ExampleUtil;
import com.qufei.model.UserModel;
import com.qufei.setting.AppSetting;
import com.qufei.tools.AppConfig;
import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

/**
 *
 */
public class CircleActivity extends  InstrumentedActivity{

	public static CircleActivity m;
	private CircleMenuLayout mCircleMenuLayout;
	public final int LOGINACTIVITYCODE = 0;
	public final int QUERYACTIVITYCODE = 1;
	public final int ADDPRODUCTACTIVITY = 2;
	private String[] mItemTexts = new String[] { "资产查询", "资产录入", "财务对账", "单据审核", "我的账户", "图形分析" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal, R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal, R.drawable.home_mbank_6_normal };




	//jpush

	public static boolean isForeground = false;
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";


	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!ExampleUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				Toast.makeText(CircleActivity.this, "收到信息:"+showMsg.toString(), Toast.LENGTH_SHORT).show();

			}
		}
	}


	//for receive customer msg from jpush server
	private MessageReceiver mMessageReceiver;
	private void registerMessageReceiver() {
		// TODO Auto-generated method stub

		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);

	}
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				init();
			}else{
				Toast.makeText(CircleActivity.this, "读取配置文件故障", Toast.LENGTH_SHORT).show();
			}
		};
	};
	public static Handler  HandReloadConfig=new Handler(){
		public void handleMessage(Message msg) {
			m.loadConfig(1);
		};
	};
	public  void loadConfig(final int mod){
		new Thread(new Runnable() {
			Message msg=hand.obtainMessage();

			@Override
			public void run() {
				// TODO Auto-generated method stub
				AppConfig app= AppConfig.getInstance();
				try{
					SharedPreferences sp = getSharedPreferences("network_setting", Context.MODE_PRIVATE);
					String addressStr = sp.getString("prefer_address", getResources().getString(R.string.ip));
					String httpPortStr=sp.getString("http_port", getResources().getString(R.string.httpport));
					String socketProt=sp.getString("socket_port", getResources().getString(R.string.socketport));

					app.setIp(addressStr);
					app.setHttpPort(httpPortStr);
					app.setSocketPort(socketProt);

					AppConfig.app=app;
					msg.what=1;
				}catch
				(Exception e){
					msg.what=0;
				}
				if(mod==0)//
					msg.sendToTarget();

			}
		}){

		}.start();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//定义一个接收器，用来做jpush推送
		registerMessageReceiver(); 
		//  
		setContentView(R.layout.activity_main02);
		


		m=this;
		mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		loadConfig(0);

	}
	public void init(){
		// setContentView(R.layout.activity_main);
		AppConfig.LoginUser = new UserModel("", "","", "", "", "", "");
		

		mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void itemClick(View view, int pos) {
				Log.i("pos__", pos + "");
				if (AppConfig.LoginUser.getRightbit() == "") {
					Toast.makeText(CircleActivity.this, "您还没有登录！", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(com.qufei.androidapp.CircleActivity.this, com.qufei.login.LoginActivity.class);
					startActivityForResult(intent, LOGINACTIVITYCODE);
					return;
				}
				Intent intent = null;
				switch (pos) {
				case 0:

					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
						return;
					}
					intent = new Intent(com.qufei.androidapp.CircleActivity.this, com.qufei.query.QueryActivity.class);
					startActivityForResult(intent, QUERYACTIVITYCODE);
					break;
				case 1:
					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
						return;
					}


					//					intent = new Intent(com.qufei.androidapp.CircleActivity.this, com.qufei.addproduct.AddProductActivity.class);
					//					startActivityForResult(intent, ADDPRODUCTACTIVITY);

					intent =new Intent(CircleActivity.this,com.qufei.addproduct.SelectTypeActivity.class);
					startActivity(intent);

					break;
				case 2://财务对账
					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
						return;
					}
//					intent =new Intent(CircleActivity.this,com.qufei.confirm.ConfirmQueryActivity.class);
					intent =new Intent(CircleActivity.this,com.qufei.confirm.YanshoudanListActivity.class);
					startActivity(intent);
					break;
				case 3://单据审核
					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();

						return;
					}
					intent=new Intent(CircleActivity.this,com.qufei.verify.DaishengheActivity.class);
					startActivity(intent);


					break;
				case 4://用户信息查看
					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
						

						return;
					}
						intent=new Intent(CircleActivity.this,com.qufei.user.UserInfoActivity.class);
						startActivity(intent);
					 
					break;

				case 5:
					//报表
					if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
						Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
						return;
					}
					intent = new Intent(com.qufei.androidapp.CircleActivity.this, com.qufei.chart.ChartActivity.class);
					startActivity(intent);
					break;
				default:
					Toast.makeText(CircleActivity.this, "没有授权", Toast.LENGTH_SHORT).show();
					return;

				}

			}

			@Override
			public void itemCenterClick(View view) {
				// 这里是登陆界面
				if (com.qufei.tools.NetworkDetector.isNetworkAvailable(com.qufei.androidapp.CircleActivity.this)) {

				}
				else {
					Toast.makeText(com.qufei.androidapp.CircleActivity.this, "当前网络不不可用", Toast.LENGTH_LONG).show();
					com.qufei.tools.NetworkDetector.setNetworkMethod(com.qufei.androidapp.CircleActivity.this);
					return;
				}

				Intent intent = new Intent(com.qufei.androidapp.CircleActivity.this, com.qufei.login.LoginActivity.class);
				startActivityForResult(intent, LOGINACTIVITYCODE);

			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("result", requestCode + "|" + resultCode);
		switch (requestCode) {
		case LOGINACTIVITYCODE: {
			if (resultCode == Activity.RESULT_OK)
				Toast.makeText(CircleActivity.this, "用户" + data.getStringExtra("username") + "登录成功", Toast.LENGTH_LONG).show();
		}
		break;
		default:
			break;
		}
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() == SETTINGS_ID) {  
			Intent intent = new Intent(this, AppSetting.class);  
			//如果requestCode >= 0 则返回结果时会回调 onActivityResult()方法  
			startActivityForResult(intent, 1); 

		} else if(item.getItemId()==EXIT_ID){  
			if(AppConfig.LoginUser.getRightbit().equals(""))
				Toast.makeText(this, "您还没有登录", Toast.LENGTH_SHORT).show();
			else{
				AppConfig.LoginUser.setRightbit("");
				Toast.makeText(this, "已安全退出", Toast.LENGTH_SHORT).show();
			}

		}  
		return true;
	}

	private static final int SETTINGS_ID = 0;  
	private static final int EXIT_ID = 1;  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		menu.add(0, SETTINGS_ID, 0, "系统设置");  
		menu.add(0, EXIT_ID, 0, "退出账号");  
		return true;
	}

}
