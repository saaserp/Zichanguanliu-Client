package com.qufei.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.m;

import com.qufei.androidapp.R;
import com.qufei.main.QufeiAppMain;
import com.qufei.tools.AppConfig;

public class UserInfoActivity extends Activity {

	public static UserInfoActivity m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_user);
		m=this;
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment(this))
			.commit();
		}

	}
	public  Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			//将记住密码取消
			SharedPreferences mSettings;
			mSettings =getSharedPreferences("user_info", Context.MODE_PRIVATE);

			Editor mEditor = mSettings.edit();
		 
			mEditor.putBoolean("isSavePassword", false);
		 
			mEditor.commit();
		 
			  msg=QufeiAppMain.handExit.obtainMessage();
				msg.sendToTarget();
			UserInfoActivity.this.finish();
			
		}
	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		Context context;
		public PlaceholderFragment(Context context) {
			this.context=context;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			TextView tvUserID;
			TextView tvUserName;
			TextView tvUserRole;
			tvUserID=(TextView) rootView.findViewById(R.id.user_id);
			tvUserName=(TextView)rootView.findViewById(R.id.user_name);
			tvUserRole=(TextView)rootView.findViewById(R.id.user_role);
			tvUserID.setText(AppConfig.LoginUser.getUserid());
			tvUserName.setText(AppConfig.LoginUser.getUsername());
			tvUserRole.setText(AppConfig.LoginUser.getRolename());
			
			rootView.findViewById(R.id.exit).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					if(AppConfig.LoginUser.getRightbit().equals(""))
						Toast.makeText(rootView.getContext(), "您还没有登录", Toast.LENGTH_SHORT).show();
					else{
						
						AlertDialog.Builder builder = new Builder(context);
						builder.setMessage("确定要退出吗?退出后密码不记录");
						builder.setTitle("提示");
						builder.setIcon(android.R.drawable.ic_dialog_alert);
						builder.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {
							
							
							public void onClick(DialogInterface dialog, int which) {
								
								dialog.dismiss();
								AppConfig.LoginUser.setRightbit("");
								Message msg=((UserInfoActivity)context).hand.obtainMessage();
								msg.sendToTarget();
								JPushInterface.stopPush((Context)m);
								 
								Toast.makeText(rootView.getContext(), "已安全退出", Toast.LENGTH_SHORT).show();
							
							}
						});

						builder.setNegativeButton("取消",
								new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});

						builder.create().show();
					
						
					}

				}
			});
			return rootView;
		}
	}

}
