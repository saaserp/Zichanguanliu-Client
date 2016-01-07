package com.qufei.setting;

import com.qufei.androidapp.CircleActivity;
import com.qufei.androidapp.R;
import com.qufei.jpush.ExampleUtil;
import com.qufei.main.QufeiAppMain;
import com.qufei.tools.AppConfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Message;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NetWorkSetting extends PreferenceActivity implements OnPreferenceChangeListener{
	private EditTextPreference address;//ip
	private EditTextPreference httpPort;
	private EditTextPreference socketProts;
	
	String addressStr;
	String httpPortStr;
	String socketProtStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.layout_setting);
		addPreferencesFromResource(R.xml.network_setting);

		address = (EditTextPreference) findPreference("prefer_address");
		httpPort=(EditTextPreference) findPreference("http_port");
		socketProts=(EditTextPreference) findPreference("socket_port");

		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NetWorkSetting.this.finish();
			}
		});
		TextView tv_title=(TextView)this.findViewById(R.id.title_text);
		tv_title.setText("网络设置");
		SharedPreferences sp = getSharedPreferences("network_setting", Context.MODE_PRIVATE);
		addressStr = sp.getString("prefer_address", getResources().getString(R.string.ip));
		httpPortStr=sp.getString("http_port", getResources().getString(R.string.httpport));
		socketProtStr=sp.getString("socket_port", getResources().getString(R.string.socketport));
		 
		address.setOnPreferenceChangeListener(this);		
		address.setSummary(addressStr);
		address.setText(addressStr);
		
		httpPort.setOnPreferenceChangeListener(this);		
		httpPort.setSummary(httpPortStr);
		httpPort.setText(httpPortStr);
		
		socketProts.setOnPreferenceChangeListener(this);		
		socketProts.setSummary(socketProtStr);
		socketProts.setText(socketProtStr);
		 
		

	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		SharedPreferences mSettings;
		mSettings =getSharedPreferences("network_setting", Context.MODE_PRIVATE);
		Editor mEditor = mSettings.edit();
		
		if(preference == address){
			preference.setSummary(newValue.toString());
			//address.setText(newValue.toString());
			mEditor.putString("prefer_address", newValue.toString());
		}
		if(preference == httpPort){
			preference.setSummary(newValue.toString());
			mEditor.putString("http_port", newValue.toString());
		}
		
		if(preference == socketProts){
			preference.setSummary(newValue.toString());
			mEditor.putString("socket_port", newValue.toString());
		}
		 
		if(mEditor.commit()){
			Message msg=QufeiAppMain.HandReloadConfig.obtainMessage();
			msg.sendToTarget();
			Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
		}
		
				
		return true;

	}
}
