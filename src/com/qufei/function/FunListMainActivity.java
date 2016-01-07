package com.qufei.function;

import cn.jpush.android.data.r;

import com.qufei.androidapp.R;
import com.qufei.login.LoginActivity;
import com.qufei.model.UserModel;
import com.qufei.query.ProductInfoActivity;
import com.qufei.setting.AppSetting;
import com.qufei.tools.AppConfig;
import com.qufei.verify.DaishengheActivity;
import com.zbar.lib.CaptureActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FunListMainActivity extends Activity {
	final static int QUERYACTIVITYCODE=2;
	static LinearLayout v0;
	static LinearLayout v1;
	static LinearLayout v2;
	static LinearLayout v3;
	static LinearLayout v4;
	static LinearLayout v5;
	static LinearLayout v6;
	static LinearLayout v7;
	static LinearLayout v8;
	static LinearLayout p0;
	static LinearLayout p1;
	static LinearLayout p2;
	static LinearLayout p3;
	static LinearLayout p4;
	static LinearLayout p5;
	static LinearLayout p6;
	static LinearLayout p7;
	static LinearLayout p8;
	static FunListMainActivity m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.funlist_activity_main);

		AppConfig.LoginUser = new UserModel("", "","", "", "", "", "");
		m=this;





		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment())
			.commit();
		}
	}






	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.funlist_fragment_main, container, false);
			v0=(LinearLayout) rootView.findViewById(R.id.v_0);
			v1=(LinearLayout) rootView.findViewById(R.id.v_1);
			v2=(LinearLayout) rootView.findViewById(R.id.v_2);
			v3=(LinearLayout) rootView.findViewById(R.id.v_3);
			v4=(LinearLayout) rootView.findViewById(R.id.v_4);
			v5=(LinearLayout) rootView.findViewById(R.id.v_5);
			v6=(LinearLayout) rootView.findViewById(R.id.v_6);
			v7=(LinearLayout) rootView.findViewById(R.id.v_7);  
			v8=(LinearLayout) rootView.findViewById(R.id.v_8);  
			p0=(LinearLayout) rootView.findViewById(R.id.p_0);
			p1=(LinearLayout) rootView.findViewById(R.id.p_1);
			p2=(LinearLayout) rootView.findViewById(R.id.p_2);
			p3=(LinearLayout) rootView.findViewById(R.id.p_3);
			p4=(LinearLayout) rootView.findViewById(R.id.p_4);
			p5=(LinearLayout) rootView.findViewById(R.id.p_5);
			p6=(LinearLayout) rootView.findViewById(R.id.p_6);
			p7=(LinearLayout) rootView.findViewById(R.id.p_7);  
			p8=(LinearLayout) rootView.findViewById(R.id.p_8); 
			v0.setOnClickListener(this);
			v1.setOnClickListener(this);
			v2.setOnClickListener(this);
			v3.setOnClickListener(this);
			v4.setOnClickListener(this);
			v5.setOnClickListener(this);
			v6.setOnClickListener(this);
			v7.setOnClickListener(this); 
			v8.setOnClickListener(this);
			
			hitFun();
			return rootView;
		}
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			hitFun();
		}
		public void hitFun()
		{
			String right=AppConfig.app.LoginUser.getRightbit();			 
			if(!right.contains("0")){
			 
				p0.setVisibility(View.INVISIBLE);
				p0.setVisibility(View.GONE);
				
			} else{
				 
				p0.setVisibility(View.VISIBLE);
				
			}
			if(!right.contains("1")){
				p1.setVisibility(View.INVISIBLE);
				p1.setVisibility(View.GONE);
			} 
			else{
				p1.setVisibility(View.VISIBLE);
			}
			if(!right.contains("2")){
				p2.setVisibility(View.INVISIBLE);
				p2.setVisibility(View.GONE);
			} 
			else{
				p2.setVisibility(View.VISIBLE);
			}
			if(!right.contains("3")){
				p3.setVisibility(View.INVISIBLE);
				p3.setVisibility(View.GONE);
			}  
			else{
				p3.setVisibility(View.VISIBLE);
			}
			if(!right.contains("4")){
				p4.setVisibility(View.INVISIBLE);
				p4.setVisibility(View.GONE);
			} 
			else{
				p4.setVisibility(View.VISIBLE);
			}
			if(!right.contains("5")){
				p5.setVisibility(View.INVISIBLE);
				p5.setVisibility(View.GONE);
			}  
			else{
				p5.setVisibility(View.VISIBLE);
			}
			if(!right.contains("6")){
				p6.setVisibility(View.INVISIBLE);
				p6.setVisibility(View.GONE);
				
			} 
			else{
				p6.setVisibility(View.VISIBLE);
			}
			if(!right.contains("7")){
				p7.setVisibility(View.INVISIBLE);
				p7.setVisibility(View.GONE);
			} 
			else{
				p7.setVisibility(View.VISIBLE);
			}
			if (!right.contains("8")){
				p8.setVisibility(View.INVISIBLE);
				p8.setVisibility(View.GONE);
			}  else
			{
				p8.setVisibility(View.VISIBLE);
			}
}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent ;
			int pos=getPos(arg0);

			switch (pos) {
			case 0:

				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText(m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}
				intent = new Intent(m, com.qufei.query.QueryActivity.class);
				startActivityForResult(intent, 0);
				break;
			case 1://资产录入
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}		 
				intent =new Intent( m,com.qufei.addproduct.SelectTypeActivity.class);
				startActivity(intent);

				break;
			case 2://财务对账
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}

				intent =new Intent( m,com.qufei.confirm.YanshoudanListActivity.class);
				startActivity(intent);
				break;
			case 3://单据审核
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();

					return;
				}
				intent=new Intent( m,com.qufei.verify.DaishengheActivity.class);
				intent.putExtra("mod", "1");//可以修改
				startActivity(intent);


				break;
			case 4://用户信息查看
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText(  m, "没有授权", Toast.LENGTH_SHORT).show();


					return;
				}
				intent=new Intent( m,com.qufei.user.UserInfoActivity.class);
				startActivity(intent);

				break;

			case 5:
				//图形分析
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}
				intent = new Intent( m, com.qufei.chart.ChartActivity.class);
				startActivity(intent);
				break;
			case 6:
				//系统设置
				intent = new Intent( m, AppSetting.class);
				startActivity(intent);
				break;
			case 7:
				//扫码查单
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}
				intent=new Intent(m,CaptureActivity.class);
				startActivityForResult(intent, QUERYACTIVITYCODE);
				break;
			case 8:
				//单据查询
				if (!AppConfig.LoginUser.getRightbit().contains(pos + "")) {
					Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
					return;
				}
				intent=new Intent(m,DaishengheActivity.class);
				intent.putExtra("mod", "0");
				startActivity(intent);
				 
				break;
			default:
				Toast.makeText( m, "没有授权", Toast.LENGTH_SHORT).show();
				return;

			}
		}
	}



	public static int getPos(View v){
		switch(v.getId()){
		case R.id.v_0:
			return 0;
		case R.id.v_1:
			return 1;
		case R.id.v_2:
			return 2;
		case R.id.v_3:
			return 3;
		case R.id.v_4:
			return 4;
		case R.id.v_5:
			return 5;
		case R.id.v_6:
			return 6;
		case R.id.v_7:
			return 7;
		case R.id.v_8:
			return 8;
		default:
			return 0;
		}

	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case QUERYACTIVITYCODE:{
			if(resultCode==Activity.RESULT_OK){
				String codestr=data.getStringExtra("codestr");
				Intent i=new Intent(this,ProductInfoActivity.class);
				i.putExtra("keyWord", codestr);
				i.putExtra("type","");
				startActivity(i);
			}
			else
				Toast.makeText(this, "未扫描到数据", Toast.LENGTH_LONG).show();;
				;
		}
		break;
		default:
			break;
		}
	}

}
