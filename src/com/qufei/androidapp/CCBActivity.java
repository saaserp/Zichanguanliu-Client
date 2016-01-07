package com.qufei.androidapp;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qufei.tools.AppConfig;
import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;
/**
 * <pre>
 * @author lwc 
 * 
 * </pre>
 */
public class CCBActivity extends Activity
{
	public final int LOGINACTIVITYCODE=0;
	public final int QUERYACTIVITYCODE=1;
	public final int ADDPRODUCTACTIVITY=2;
	private CircleMenuLayout mCircleMenuLayout;

	private String[] mItemTexts = new String[] { "资产查询", "资产录入", "财务对账",
			"单据审核", "我的账户", "图形分析" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//自已切换布局文件看效果
//		setContentView(R.layout.activity_main02);
		setContentView(R.layout.activity_main02);

		mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		
		

		mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			
			@Override
			public void itemClick(View view, int pos)
			{
				Intent intent = null;
				switch(pos){
				case 0:
					if(!AppConfig.LoginUser.getRightbit().contains(pos+"")){
						Toast.makeText(CCBActivity.this, "没有授权", Toast.LENGTH_LONG).show();
					return ;
					}
					intent=new Intent(com.qufei.androidapp.CCBActivity.this,com.qufei.query.QueryActivity.class);
					startActivityForResult(intent, QUERYACTIVITYCODE);
					break;
				case 1:
					if(!AppConfig.LoginUser.getRightbit().contains(pos+"")){
						Toast.makeText(CCBActivity.this, "没有授权", Toast.LENGTH_LONG).show();
					return ;
					}
					intent=new Intent(com.qufei.androidapp.CCBActivity.this,com.qufei.addproduct.AddProductActivity.class);
					startActivityForResult(intent, ADDPRODUCTACTIVITY);
					break;
				case 5:
					if(!AppConfig.LoginUser.getRightbit().contains(pos+"")){
						Toast.makeText(CCBActivity.this, "没有授权", Toast.LENGTH_LONG).show();
					return ;
					}
					//intent =new Intent(com.qufei.androidapp.CircleActivity.this,com.qufei.mpchartexample.activitys.PieChartActivity.class);
					//intent =new Intent(com.qufei.androidapp.CircleActivity.this,com.qufei.mpchartexample.activitys.BarChartActivity.class);
					intent =new Intent(com.qufei.androidapp.CCBActivity.this,com.qufei.chart.ChartActivity.class);
					startActivity(intent);
				default:
					
						Toast.makeText(CCBActivity.this, "没有授权", Toast.LENGTH_LONG).show();
					
					
					return ;
					
				}
				

			}
			
			@Override
			public void itemCenterClick(View view)
			{
				//这里是登陆界面
				if(com.qufei.tools.NetworkDetector.isNetworkAvailable(com.qufei.androidapp.CCBActivity.this))
				{
					
				}
				else
				{
					Toast.makeText(com.qufei.androidapp.CCBActivity.this, "当前网络不不可用", Toast.LENGTH_LONG).show();
					com.qufei.tools.NetworkDetector.setNetworkMethod(com.qufei.androidapp.CCBActivity.this);
						return ;
				}

				Intent intent=new Intent(com.qufei.androidapp.CCBActivity.this,com.qufei.login.LoginActivity.class);
				startActivityForResult(intent, LOGINACTIVITYCODE);
				
				
			}
		});
		
	}

}
