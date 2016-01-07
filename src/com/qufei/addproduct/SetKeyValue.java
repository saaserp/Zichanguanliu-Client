package com.qufei.addproduct;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.qufei.androidapp.R;

public class SetKeyValue extends Activity {

	String oldKey;
	String oldValue;
	String newKey;
	String newValue;
	int pos;
	EditText edt_key;
	EditText edt_value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_key_value);
		oldKey=getIntent().getStringExtra("key");
		oldValue=getIntent().getStringExtra("value");
		pos=getIntent().getIntExtra("pos", -1);
		edt_key=(EditText) this.findViewById(R.id.key);
		edt_value=(EditText) this.findViewById(R.id.value);
		edt_key.setText(oldKey);
		edt_value.setText(oldValue);
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			SetKeyValue.this.finish();
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				save();
			}
		});
		
		
		
	}
	void save(){
		Uri data=Uri.parse("");
		Intent intent=new Intent(null,data);
		newKey=edt_key.getText().toString();
		newValue=edt_value.getText().toString();
		
		intent.putExtra("key", newKey);
		intent.putExtra("value", newValue);
		intent.putExtra("pos", pos);
		setResult(RESULT_OK,intent);
		this.finish();
	}
	void unSave(){
		Uri data=Uri.parse("");
		Intent intent=new Intent(null,data);
		setResult(RESULT_CANCELED,intent);
		this.finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_key_value, menu);
		return true;
	}

}
