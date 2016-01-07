package com.qufei.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.util.h;

import com.qufei.androidapp.CircleActivity;
import com.qufei.androidapp.R;
import com.qufei.androidapp.CircleActivity.MessageReceiver;
import com.qufei.function.FunListMainActivity;
import com.qufei.jpush.ExampleUtil;
import com.qufei.query.ProductInfoActivity;
import com.qufei.setting.AppSetting;
import com.qufei.tools.AppConfig;
import com.qufei.user.UserInfoActivity;
import com.zbar.lib.CaptureActivity;
@SuppressWarnings("deprecation")
public class QufeiAppMain extends TabActivity implements OnClickListener  {
	FrameLayout fmpan; 
	TabHost tabHost;
	public final int QUERYACTIVITYCODE=3;
	//FrameLayout fm;// ��ҳ
	LayoutInflater inflater;
	LinearLayout layout_funlist; //�����б�
	LinearLayout layout_query; //ɨ�����
	LinearLayout layout_my; //�ҵ��˻�
	LinearLayout layout_setting; //ϵͳ����
	public static final String TAB_FUNLIST="�����б�";
	public static final String TAB_QYERY="ɨ��鵥";
	public static final String TAB_MY="�ҵ��˻�";
	public static final String TAB_SETTING="ϵͳ����";
	public static final String TAB_HOME="��ҳ";
	public TextView tv0;
	public TextView tv1;
	public TextView tv2;
	public TextView tv3;

	ImageView image0;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	static QufeiAppMain m;
	ColorStateList  color1;
	ColorStateList  color2;


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
				Toast.makeText(QufeiAppMain.this, "�յ���Ϣ:"+showMsg.toString(), Toast.LENGTH_SHORT).show();

			}
		}
	}
	private MessageReceiver mMessageReceiver;
	private void registerMessageReceiver() {
		// TODO Auto-generated method stub

		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);

	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {  

	} 
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		int version=AppConfig.getAndroidSDKVersion();
		if(version<15){
			new AlertDialog.Builder(this).setTitle("ϵͳ��ʾ")//���öԻ������  
			  
		     .setMessage("��ǰ��׿�汾̫��,���ڰ�׿4.3���ϵİ汾�����У�")//������ʾ������  
		  
		     .setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {//���ȷ����ť  
		  
		          
		  
		         @Override  
		  
		         public void onClick(DialogInterface dialog, int which) {//ȷ����ť����Ӧ�¼�  
		  
		             // TODO Auto-generated method stub  
		  
		             finish();  
		  
		         }  
		  
		     }) 
		      .show();//�ڰ�����Ӧ�¼�����ʾ�˶Ի���  
		 
		}
		//����һ����������������jpush����
		registerMessageReceiver(); 
		//  
		setContentView(R.layout.main_tab);
		color1=  this.getResources().getColorStateList(com.qufei.androidapp.R.color.black);
		color2=  this.getResources().getColorStateList(com.qufei.androidapp.R.color.blue);
		
		loadConfig(0);
		
		m=this;

	}
	public static Handler  HandReloadConfig=new Handler(){
		public void handleMessage(Message msg) {
			m.loadConfig(1);
		};
	};
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				initView();
			}else{
				Toast.makeText(QufeiAppMain.this, "��ȡ�����ļ�����", Toast.LENGTH_SHORT).show();
			}
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
	private void initView() {

	



		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		fmpan = (FrameLayout)findViewById(R.id.tab1);//
		//fm = (FrameLayout)findViewById(R.id.home);//

		tabHost=this.getTabHost();
		layout_funlist=(LinearLayout)findViewById(R.id.funlist);
		layout_query=(LinearLayout)findViewById(R.id.query);
		layout_my=(LinearLayout)findViewById(R.id.my);
		layout_setting=(LinearLayout)findViewById(R.id.setting);


		tv0=(TextView)findViewById(R.id.tv0);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		tv3=(TextView)findViewById(R.id.tv3);
		image0=(ImageView)findViewById(R.id.img0);
		image1=(ImageView)findViewById(R.id.img1);
		image2=(ImageView)findViewById(R.id.img2);
		image3=(ImageView)findViewById(R.id.img3);

		TabSpec tabspac_commuity=tabHost.newTabSpec(TAB_FUNLIST).setIndicator(TAB_FUNLIST);

		TabSpec tabspac_message=tabHost.newTabSpec(TAB_QYERY).setIndicator(TAB_QYERY);
		TabSpec tabspac_my=tabHost.newTabSpec(TAB_MY).setIndicator(TAB_MY);
		TabSpec tabspac_explore=tabHost.newTabSpec(TAB_SETTING).setIndicator(TAB_SETTING);
		TabSpec tabHome=tabHost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME);
		tabspac_commuity.setContent(new Intent(QufeiAppMain.this,FunListMainActivity.class));
		tabspac_message.setContent(new Intent(QufeiAppMain.this,MessageActivity.class));
		tabspac_my.setContent(new Intent(QufeiAppMain.this,UserInfoActivity.class));
		Intent i=new  Intent(QufeiAppMain.this,AppSetting.class);
		i.putExtra("mod", "0");
		tabspac_explore.setContent(i);
		tabHome.setContent(new Intent(QufeiAppMain.this,CircleActivity.class));
		tabHost.addTab(tabspac_commuity);
		tabHost.addTab(tabspac_message);
		tabHost.addTab(tabspac_my);
		tabHost.addTab(tabspac_explore);
		tabHost.addTab(tabHome);

		layout_funlist.setOnClickListener(this);
		layout_query.setOnClickListener(this);
		layout_my.setOnClickListener(this);
		layout_setting.setOnClickListener(this);
		tabHost.setCurrentTabByTag(TAB_FUNLIST);

		//		fm.setOnClickListener(new OnClickListener() {
		//			public void onClick(View v) {
		//				tv0.setTextColor(color1);
		//				tv1.setTextColor(color1);
		//				tv2.setTextColor(color1);
		//				tv3.setTextColor(color1);
		//				image0.setImageResource(R.drawable.ta0);
		//				image1.setImageResource(R.drawable.tb0);
		//				image2.setImageResource(R.drawable.tc0);
		//				image3.setImageResource(R.drawable.td0);
		//				tabHost.setCurrentTabByTag(TAB_HOME);
		//
		//			}
		//		});

		//û�е�¼��������¼
		if ( AppConfig.LoginUser==null||AppConfig.LoginUser.getRightbit() == "") {
			//Toast.makeText(this, "����û�е�¼��", Toast.LENGTH_SHORT).show();
			Intent intent= new Intent( this, com.qufei.login.LoginActivity.class);
			startActivity(intent);
			return;
		}
		
	}
	
	


	public void onClick(View v) {
		// TODO Auto-generated method stub

		tv0.setTextColor(color1);
		tv1.setTextColor(color1);
		tv2.setTextColor(color1);
		tv3.setTextColor(color1);
		image0.setImageResource(R.drawable.ta0);
		image1.setImageResource(R.drawable.tb0);
		image2.setImageResource(R.drawable.tc0);
		image3.setImageResource(R.drawable.td0);

		Intent intent;
		if( R.id.setting!=v.getId())
			if ( AppConfig.LoginUser==null||AppConfig.LoginUser.getRightbit() == "") {
				Toast.makeText(this, "����û�е�¼��", Toast.LENGTH_SHORT).show();
				intent= new Intent( this, com.qufei.login.LoginActivity.class);
				startActivity(intent);
				return;
			}

		switch (v.getId()) {
		case R.id.funlist:

			tv0.setTextColor(color2);
			tabHost.setCurrentTabByTag(TAB_FUNLIST);
			image0.setImageResource(R.drawable.ta1);
			break;

		case R.id.query:
			tv1.setTextColor(color2);
			image1.setImageResource(R.drawable.tb1);
			if (!AppConfig.LoginUser.getRightbit().contains(7 + "")) {
				Toast.makeText(  this, "û����Ȩ", Toast.LENGTH_SHORT).show();


				return;
			}
			//tabHost.setCurrentTabByTag(TAB_MESSAGE);
			intent=new Intent(this,CaptureActivity.class);

			startActivityForResult(intent, QUERYACTIVITYCODE);
			
			break;
		case R.id.my:
			tv2.setTextColor(color2);
			image2.setImageResource(R.drawable.tc1);
			tabHost.setCurrentTabByTag(TAB_MY);
			break;
		case R.id.setting:
			tv3.setTextColor(color2);
			image3.setImageResource(R.drawable.td1);
					
			tabHost.setCurrentTabByTag(TAB_SETTING);
			break;
		default :
			break;

		}

	}  
	public static Handler handExit=new Handler(){
		public void handleMessage(Message msg) {
			  //��ѯ��,ֱ���˳�
				dialog_Exit(m,0);
			 
			
		};
	};
	@Override
	public boolean dispatchKeyEvent(KeyEvent event){
		// TODO Auto-generated method stub
		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK&&event.getAction() == KeyEvent.ACTION_DOWN)
			dialog_Exit(this,1);//ѯ���˳�
		return false;

		//end onKeyDown
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case QUERYACTIVITYCODE:{
			if(resultCode==Activity.RESULT_OK){
				String codestr=data.getStringExtra("codestr");
				Intent i=new Intent(this,ProductInfoActivity.class);
				i.putExtra("fromPage", "main");
				i.putExtra("keyWord", codestr);
				i.putExtra("type","");
				startActivity(i);
			}
			else
				Toast.makeText(this, "δɨ�赽����", Toast.LENGTH_LONG).show();
			tv1.setTextColor(color1);
			image1.setImageResource(R.drawable.tb0);
		}
		break;
		default:
			break;
		}
	}


	public static void dialog_Exit(Context context,int mod) {
		if(mod==0)
		{//ֱ���˳�
			m.finish();
			return ;
			
		} 
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("ȷ��Ҫ�˳���?");
		builder.setTitle("��ʾ");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				android.os.Process.killProcess(android.os.Process
						.myPid());
			}
		});

		builder.setNegativeButton("ȡ��",
				new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}
	private static final int SETTINGS_ID = 0;  
	private static final int EXIT_ID = 1;  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		menu.add(0, SETTINGS_ID, 0, "ϵͳ����");  
		menu.add(0, EXIT_ID, 0, "�˳��˺�");  
		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() == SETTINGS_ID) {  
			Intent intent = new Intent(this, AppSetting.class);  
			//���requestCode >= 0 �򷵻ؽ��ʱ��ص� onActivityResult()����  
			startActivityForResult(intent, 1); 

		} else if(item.getItemId()==EXIT_ID){  
			if(AppConfig.LoginUser.getRightbit().equals(""))
				Toast.makeText(this, "����û�е�¼", Toast.LENGTH_SHORT).show();
			else{
				AppConfig.LoginUser.setRightbit("");
				Toast.makeText(this, "�Ѱ�ȫ�˳�", Toast.LENGTH_SHORT).show();
			}

		}  
		return true;
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}

}