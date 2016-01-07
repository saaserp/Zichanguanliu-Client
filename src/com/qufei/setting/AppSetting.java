package com.qufei.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

import com.qufei.androidapp.R;
import com.qufei.jpush.SettingActivity;

public class AppSetting extends PreferenceActivity implements OnPreferenceChangeListener {
	private Preference push_setting;
	private Preference net_setting;
	CheckBoxPreference	enable_push;
	public String mod="1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_setting);
		addPreferencesFromResource(R.xml.setting);
	 	SharedPreferences sp  =getSharedPreferences("setting", Context.MODE_PRIVATE);
	 	mod=getIntent().getStringExtra("mod");
		enable_push = (CheckBoxPreference) findPreference("enable_push");
		enable_push.setOnPreferenceChangeListener(this);
		enable_push.setChecked(sp.getBoolean("enable_push",true));
		push_setting = (Preference) findPreference("push_setting");
		
		net_setting= (Preference) findPreference("connect_setting");
		if(mod!=null&&mod.equals("0"))
		  this.findViewById(R.id.title_left).setVisibility(View.INVISIBLE);
		else
			this.findViewById(R.id.title_left).setVisibility(View.VISIBLE);
		push_setting.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppSetting.this, SettingActivity.class);
				startActivity(intent);
				return false;
			}
		});
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AppSetting.this.finish();
			}
		});
		net_setting.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(AppSetting.this,NetWorkSetting.class);
				startActivity(intent);
				return false;
			}
		});
		
		TextView tv_title=(TextView)this.findViewById(R.id.title_text);
		tv_title.setText("…Ë÷√");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_setting, menu);
		return true;
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		
		SharedPreferences sp  =getSharedPreferences("setting", Context.MODE_PRIVATE);
		if(preference == enable_push){
			preference.setSummary(newValue.toString());
		
			if(newValue.toString().equals("false")){
				sp.edit().putBoolean("enable_push", false).commit();
			JPushInterface.stopPush(getApplicationContext());
		 
			}
			else{
				sp.edit().putBoolean("enable_push", true).commit();
				JPushInterface.resumePush(getApplicationContext());
			}
			 
		} 
				
		 
		 
		return true;
	}

}
