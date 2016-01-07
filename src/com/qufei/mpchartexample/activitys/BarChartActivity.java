package com.qufei.mpchartexample.activitys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.qufei.androidapp.R;
import com.qufei.factory.StatisticalJsonResultFactory;
import com.qufei.model.StatisticalModel;
import com.qufei.mpchartexample.activitys.custom.MyValueFormatter;
import com.qufei.mpchartexample.activitys.notimportant.DemoBase;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class BarChartActivity extends DemoBase implements OnSeekBarChangeListener, OnChartValueSelectedListener {
	private ProgressDialog pd;
	protected BarChart mChart;

	private Typeface mTf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_barchart);
		// this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// BarChartActivity.this.finish();
		// }
		// });

		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在加载数据");

		mChart = (BarChart) findViewById(R.id.chart1);
		mChart.setOnChartValueSelectedListener(this);

		mChart.setDrawBarShadow(false);
		mChart.setDrawValueAboveBar(true);

		mChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);

		// draw shadows for each bar that show the maximum value
		// mChart.setDrawBarShadow(true);

		// mChart.setDrawXLabels(false);

		mChart.setDrawGridBackground(false);
		// mChart.setDrawYLabels(false);

		mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setTypeface(mTf);
		xAxis.setDrawGridLines(false);
		xAxis.setSpaceBetweenLabels(2);

		ValueFormatter custom = new MyValueFormatter();

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setTypeface(mTf);
		leftAxis.setLabelCount(8);
		leftAxis.setValueFormatter(custom);
		leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setSpaceTop(15f);

		YAxis rightAxis = mChart.getAxisRight();
		rightAxis.setDrawGridLines(false);
		rightAxis.setTypeface(mTf);
		rightAxis.setLabelCount(8);
		rightAxis.setValueFormatter(custom);
		rightAxis.setSpaceTop(15f);

		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		l.setForm(LegendForm.SQUARE);
		l.setFormSize(9f);
		l.setTextSize(11f);
		l.setXEntrySpace(4f);

		loadData();
		// setData(12, 50);

	}

	double max(double[] s) {
		double max = 0;
		for (double n : s) {
			if (max < n) {
				max = n;
			}

		}
		return max;
	}

	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				setData(mParties, mValues);
				mChart.animateXY(3000, 3000);
			}
			else {
				Toast.makeText(BarChartActivity.this, "请连接互联网", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};

	private void loadData() {
		pd.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = hand.obtainMessage();
				QufeiSocketClient qs = QufeiSocketClient.getInstance();
				JSONArray jsArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("userID", AppConfig.LoginUser.getUserid());
				}
				catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jsArray.put(jsonObject);
				try {
					String jsStr = qs.send("Statistical", jsArray);

					Log.i("jsStrinfo", jsStr);
					JsonResult r = new StatisticalJsonResultFactory().setJsonStr(jsStr).createResult();
					List<Model> mds = r.getModels();
					mParties = new String[mds.size()];
					mValues = new double[mds.size()];

					for (int i = 0; i < mds.size(); i++) {
						mParties[i] = ((StatisticalModel) mds.get(i)).getpName();
						mValues[i] = Double.parseDouble(((StatisticalModel) mds.get(i)).getpSum());

					}
					msg.what = 1;
				}
				catch (Exception e) {
					msg.what = 0;
				}
				msg.sendToTarget();
			}
		}) {
		}.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.actionToggleValues: {
			for (DataSet<?> set : mChart.getData().getDataSets())
				set.setDrawValues(!set.isDrawValuesEnabled());

			mChart.invalidate();
			break;
		}
		case R.id.actionToggleHighlight: {
			if (mChart.isHighlightEnabled())
				mChart.setHighlightEnabled(false);
			else
				mChart.setHighlightEnabled(true);
			mChart.invalidate();
			break;
		}
		case R.id.actionTogglePinch: {
			if (mChart.isPinchZoomEnabled())
				mChart.setPinchZoom(false);
			else
				mChart.setPinchZoom(true);

			mChart.invalidate();
			break;
		}
		// case R.id.actionToggleHighlightArrow: {
		// if (mChart.isDrawHighlightArrowEnabled())
		// mChart.setDrawHighlightArrow(false);
		// else
		// mChart.setDrawHighlightArrow(true);
		// mChart.invalidate();
		// break;
		// }
		// case R.id.actionToggleStartzero: {
		// mChart.getAxisLeft().setStartAtZero(!mChart.getAxisLeft().isStartAtZeroEnabled());
		// mChart.getAxisRight().setStartAtZero(!mChart.getAxisRight().isStartAtZeroEnabled());
		// mChart.notifyDataSetChanged();
		// mChart.invalidate();
		// break;
		// }
		// case R.id.animateX: {
		// mChart.animateX(3000);
		// break;
		// }
		// case R.id.animateY: {
		// mChart.animateY(3000);
		// break;
		// }
		// case R.id.animateXY: {
		//
		// mChart.animateXY(3000, 3000);
		// break;
		// }
		// case R.id.actionToggleFilter: {
		//
		// Approximator a = new Approximator(ApproximatorType.DOUGLAS_PEUCKER, 25);
		//
		// if (!mChart.isFilteringEnabled()) {
		// mChart.enableFiltering(a);
		// } else {
		// mChart.disableFiltering();
		// }
		// mChart.invalidate();
		// break;
		// }
		case R.id.actionSave: {
			if (createSDCardDir()) {
				mChart.saveToPath("曲飞科技" + System.currentTimeMillis(), "/qufei/");
				Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(this, "创建文件夹失败", Toast.LENGTH_SHORT).show();
			// mChart.saveToGallery("title"+System.currentTimeMillis());

			break;
		}
		}
		return true;
	}

	public boolean createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + "/qufei";
			File path1 = new File(path);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();

			}
		}
		else {

			return false;

		}
		return true;

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	private void setData(String mParties[], double mValues[]) {
		String resultss;
		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < mParties.length; i++) {

			xVals.add(mParties[i % mParties.length]);
		}

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

		for (int i = 0; i < mValues.length; i++) {

			resultss = String.format("%.2f", mValues[i] / 10000.0f);
			yVals1.add(new BarEntry(Float.parseFloat(resultss), i));
		}

		BarDataSet set1 = new BarDataSet(yVals1, "资产");
		set1.setBarSpacePercent(35f);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);
		// data.setValueFormatter(new MyValueFormatter());
		data.setValueTextSize(10f);
		data.setValueTypeface(mTf);

		mChart.setData(data);
	}

	@SuppressLint("NewApi")
	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

		if (e == null)
			return;

		RectF bounds = mChart.getBarBounds((BarEntry) e);
		PointF position = mChart.getPosition(e, AxisDependency.LEFT);

		Log.i("bounds", bounds.toString());
		Log.i("position", position.toString());
	}

	public void onNothingSelected() {
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub

	};
}
