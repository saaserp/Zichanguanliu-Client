package com.qufei.addproduct;

import com.qufei.androidapp.R;
import com.qufei.androidapp.R.layout;
import com.qufei.androidapp.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetValueActivity extends Activity {
	String type="";
	String keyName;
	String keyValue="";
	String keyValueOld="";
	String tName="";
	String isNull="";
	private EditText edtKeyValue;
	int pos=0;
	private String optional;
	private String isJine="false";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_value);
		edtKeyValue=(EditText) this.findViewById(R.id.edtKeyValue);
		edtKeyValue.performClick();
		keyName=getIntent().getStringExtra("name");
		type=getIntent().getStringExtra("type");
		pos=getIntent().getIntExtra("pos", -1);
		keyValueOld=getIntent().getStringExtra("default");
		optional=getIntent().getStringExtra("optional");
		tName=getIntent().getStringExtra("tName");
		isNull=getIntent().getStringExtra("isNull");
		isJine=getIntent().getStringExtra("isJine");
		int inputType = 0 ;
		if(type.equals("int")){

			inputType= InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL;

		}else if(type.equals("String"))
		{
			inputType=InputType.TYPE_CLASS_TEXT;

		}else if(type.equals("Date"))
		{
			inputType=InputType.TYPE_CLASS_DATETIME;
		}
		else if(type.equals("double"))
		{
			inputType= InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL;
			 
		}

		this.edtKeyValue.setInputType(inputType);


		edtKeyValue.setText(keyValueOld);

		if(pos<0){
			this.finish();
			return ;
		}
		((TextView)this.findViewById(R.id.title_text)).setText(keyName+"设置");
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				unSave();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_value, menu);
		return true;
	}
	private void unSave(){
		keyValue=keyValueOld;
		Uri data=Uri.parse(keyValue.toString());

		Intent intent=new Intent(null,data);
		intent.putExtra("keyValue", keyValue);
		intent.putExtra("pos", pos);
		setResult(RESULT_CANCELED,intent);
		finish();
	}

	private void save(){

		keyValue=edtKeyValue.getText().toString();
		if(type.equals("double")){
			if(!  keyValue.matches("\\d+\\.?\\d*")) {
				Toast.makeText(this, "请输入正确的值", Toast.LENGTH_SHORT).show();
				return ;
			}
		}


		Uri data=Uri.parse(keyValue.toString());

		Intent intent=new Intent(null,data);
		intent.putExtra("name", keyName);
		intent.putExtra("default", keyValue);
		intent.putExtra("pos", pos);
		intent.putExtra("type", type);
		intent.putExtra("optional", optional);
		intent.putExtra("tName", tName);
		intent.putExtra("isNull", isNull);
		intent.putExtra("isJine", isJine);
		setResult(RESULT_OK,intent);
		finish();
	}
}
