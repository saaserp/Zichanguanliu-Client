package com.qufei.mpchartexample.activitys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Color;
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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.qufei.androidapp.R;
import com.qufei.factory.StatisticalJsonResultFactory;
import com.qufei.model.StatisticalModel;
import com.qufei.mpchartexample.activitys.notimportant.DemoBase;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class PieChartActivity extends DemoBase implements OnSeekBarChangeListener, OnChartValueSelectedListener {
	private ProgressDialog pd;
	private PieChart mChart;

	// private SeekBar mSeekBarX, mSeekBarY;
	// private TextView tvX, tvY;

	private double sum(double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;

	}

	private Typeface tf;
	Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				setData(mParties.length, (float) sum(mValues));
			}
			else {
				Toast.makeText(PieChartActivity.this, "请连接互联网", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_piechart);
		// this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// PieChartActivity.this.finish();
		// }
		// });
		super.mParties = new String[] { "房地", "设备", "书本", "畜生", "农作" };
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在处理");

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

					for (int i = 0; i < mParties.length; i++) {
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
		// tvX = (TextView) findViewById(R.id.tvXMax);
		// tvY = (TextView) findViewById(R.id.tvYMax);
		//
		// mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
		// mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
		//
		// mSeekBarY.setProgress(10);
		//
		// mSeekBarX.setOnSeekBarChangeListener(this);
		// mSeekBarY.setOnSeekBarChangeListener(this);

		mChart = (PieChart) findViewById(R.id.chart1);
		mChart.setUsePercentValues(true);
		mChart.setDescription("");

		mChart.setDragDecelerationFrictionCoef(0.95f);

		tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

		mChart.setDrawHoleEnabled(true);
		mChart.setHoleColorTransparent(true);

		mChart.setTransparentCircleColor(Color.WHITE);

		mChart.setHoleRadius(58f);
		mChart.setTransparentCircleRadius(61f);

		mChart.setDrawCenterText(true);

		mChart.setRotationAngle(0);
		// enable rotation of the chart by touch
		mChart.setRotationEnabled(true);

		// mChart.setUnit(" 閳э拷");
		// mChart.setDrawUnitsInChart(true);

		// add a selection listener
		mChart.setOnChartValueSelectedListener(this);

		mChart.setCenterText("曲飞科技");

		// setData(mParties.length, mValues.length);

		mChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
		// mChart.spin(2000, 0, 360);

		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.RIGHT_OF_CHART);
		l.setXEntrySpace(7f);
		l.setYEntrySpace(5f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pie, menu);
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
		case R.id.actionToggleHole: {
			if (mChart.isDrawHoleEnabled())
				mChart.setDrawHoleEnabled(false);
			else
				mChart.setDrawHoleEnabled(true);
			mChart.invalidate();
			break;
		}
		case R.id.actionDrawCenter: {
			if (mChart.isDrawCenterTextEnabled())
				mChart.setDrawCenterText(false);
			else
				mChart.setDrawCenterText(true);
			mChart.invalidate();
			break;
		}
		case R.id.actionToggleXVals: {

			mChart.setDrawSliceText(!mChart.isDrawSliceTextEnabled());
			mChart.invalidate();
			break;
		}
		case R.id.actionSave: {
			if (createSDCardDir()) {
				mChart.saveToPath("曲飞科技" + System.currentTimeMillis(), "/qufei/");
				Toast.makeText(PieChartActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(PieChartActivity.this, "创建文件夹失败", Toast.LENGTH_SHORT).show();
			// mChart.saveToGallery("title"+System.currentTimeMillis());

			break;
		}
		case R.id.actionTogglePercent:
			mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
			mChart.invalidate();
			break;
		case R.id.animateX: {
			mChart.animateX(1800);
			break;
		}
		case R.id.animateY: {
			mChart.animateY(1800);
			break;
		}
		case R.id.animateXY: {
			mChart.animateXY(1800, 1800);
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
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		// tvX.setText("" + (mSeekBarX.getProgress() + 1));
		// tvY.setText("" + (mSeekBarY.getProgress()));

		// setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
	}

	private void setData(int count, float range) {

		float mult = range;

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		// IMPORTANT: In a PieChart, no values (Entry) should have the same
		// xIndex (even if from different DataSets), since no values can be
		// drawn above each other.
		String resultss;
		for (int i = 0; i < count; i++) {
			resultss = String.format("%.0f", mValues[i % mParties.length] / 10000.0f);

			yVals1.add(new Entry(Float.parseFloat(resultss), i));
		}

		ArrayList<String> xVals = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			resultss = String.format("%.0f", mValues[i % mParties.length] / 10000.0f);
			xVals.add(mParties[i % mParties.length] + resultss + "万元");
		}
		PieDataSet dataSet = new PieDataSet(yVals1, "图例");
		dataSet.setSliceSpace(3f);
		dataSet.setSelectionShift(5f);

		// add a lot of colors

		ArrayList<Integer> colors = new ArrayList<Integer>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);

		PieData data = new PieData(xVals, dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.WHITE);
		data.setValueTypeface(tf);
		mChart.setData(data);

		// undo all highlights
		mChart.highlightValues(null);

		mChart.invalidate();
		mChart.animateXY(1800, 1800);
	}

	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

		if (e == null)
			return;
		Log.i("VAL SELECTED", "Value: " + e.getVal() + ", xIndex: " + e.getXIndex() + ", DataSet index: " + dataSetIndex);
	}

	@Override
	public void onNothingSelected() {
		Log.i("PieChart", "nothing selected");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	// private void removeLastEntry() {
	//
	// PieData data = mChart.getDataOriginal();
	//
	// if (data != null) {
	//
	// PieDataSet set = data.getDataSet();
	//
	// if (set != null) {
	//
	// Entry e = set.getEntryForXIndex(set.getEntryCount() - 1);
	//
	// data.removeEntry(e, 0);
	// // or remove by index
	// // mData.removeEntry(xIndex, dataSetIndex);
	//
	// mChart.notifyDataSetChanged();
	// mChart.invalidate();
	// }
	// }
	// }
	//
	// private void addEntry() {
	//
	// PieData data = mChart.getDataOriginal();
	//
	// if (data != null) {
	//
	// PieDataSet set = data.getDataSet();
	// // set.addEntry(...);
	//
	// data.addEntry(new Entry((float) (Math.random() * 25) + 20f,
	// set.getEntryCount()), 0);
	//
	// // let the chart know it's data has changed
	// mChart.notifyDataSetChanged();
	//
	// // redraw the chart
	// mChart.invalidate();
	// }
	// }
}
