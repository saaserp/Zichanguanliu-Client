package com.qufei.chart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.qufei.androidapp.R;

public class ChartActivity extends Activity {
ImageView image1;
ImageView image2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		image1=(ImageView)findViewById(R.id.img_1);
		image2=(ImageView)findViewById(R.id.img_2);
	
		image1.setOnClickListener(new OnClickListener() {
			 Intent intent;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				intent =new Intent(com.qufei.chart.ChartActivity.this,com.qufei.mpchartexample.activitys.PieChartActivity.class);
				startActivity(intent);
			}
		});
		image2.setOnClickListener(new OnClickListener() {
			 Intent intent;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent =new Intent(com.qufei.chart.ChartActivity.this,com.qufei.mpchartexample.activitys.BarChartActivity.class);
				startActivity(intent);
			}
		});
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ChartActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart, menu);
		return true;
	}

}
