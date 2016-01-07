package com.qufei.query;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.qufei.androidapp.R;
import com.zbar.lib.CaptureActivity;

public class QueryActivity extends Activity {
	public final int QUERYACTIVITYCODE=3;
	ImageView imageView;
	EditText tv;
	TextView submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_query);

		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				QueryActivity.this.finish();
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(QueryActivity.this,CaptureActivity.class);
				startActivityForResult(intent, QUERYACTIVITYCODE);
			}
		});

		imageView=(ImageView)this.findViewById(R.id.imageView1);
		submit=(TextView)this.findViewById(R.id.submit);
		tv=(EditText)this.findViewById(R.id.edt_keywords);





		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(QueryActivity.this,CaptureActivity.class);
				startActivityForResult(intent, QUERYACTIVITYCODE);

			}
		});

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(com.qufei.query.QueryActivity.this,com.qufei.query.BigClassActivity.class);

				intent.putExtra("keyWord",tv.getText().toString() );

				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.query, menu);

		return true;


	}
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);

		switch(requestCode){
		case QUERYACTIVITYCODE:{
			if(resultCode==Activity.RESULT_OK){

				tv.setText(data.getStringExtra("codestr"));
			}
			else
				//Toast.makeText(CircleActivity.this, "Î´µÇÂ¼", Toast.LENGTH_LONG).show();;
				;
		}
		break;
		default:
			break;
		}
	}

}
