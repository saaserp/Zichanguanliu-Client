package com.qufei.main;

import com.qufei.androidapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		 
		return true;
	}

}
