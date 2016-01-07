package com.qufei.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

 
import com.qufei.androidapp.R;
import com.qufei.factory.SmallClassResultFactory;
import com.qufei.login.LoginActivity;
import com.qufei.model.SmallClassModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;
import com.qufei.adapter.RefreshListView;
public class SmallClassActivity extends Activity 
implements RefreshListView.IOnRefreshListener,

RefreshListView.IOnLoadMoreListener{
	List<Map<String,String>>data;
	String [] from={"name","id"};
	ProgressDialog pd;
	int []to={R.id.SmallClassName,R.id.SmallClassId};
	String type;
	String keyWord;
	private RefreshListView mListView;
	SimpleAdapter adapter;
	private int ONETIMECELEN=100;//一次加载300条
	private int SHIJISIZE=0;//本次实际加载的条数
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	public void init(){
		keyWord=getIntent().getStringExtra("keyWord");
		type=getIntent().getStringExtra("type");
		data=new ArrayList<Map<String,String>>();
		mListView=(RefreshListView) this.findViewById(R.id.lstSmallClass);
		 
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SmallClassActivity.this.finish();	
			}
		});
		this.findViewById(R.id.title_right).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				SmallClassActivity.this.finish();


			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				try{
				 keyWord=data.get(arg2-1).get("id");
				}catch(Exception e){
					return ;
				}
				Intent intent=new Intent(SmallClassActivity.this,ProductInfoActivity.class);
				intent.putExtra("keyWord", keyWord);
				intent.putExtra("type", type);
				intent.putExtra("fromPage", "query");
				startActivity(intent);
			}
		});
		pd=new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.arg1==1){
				adapter=new SimpleAdapter(SmallClassActivity.this,data,R.layout.layout_small_item,from,to);       
				mListView.setAdapter(adapter); 
			}
			if(msg.arg1==AppConfig.SERVERERROR){
				Toast.makeText(SmallClassActivity.this, AppConfig.SERVERERRORSTR, Toast.LENGTH_LONG).show();
				SmallClassActivity.this.finish();

			}
			if(msg.arg1==AppConfig.NODATAREQUEST){
				Toast.makeText(SmallClassActivity.this, AppConfig.NODATAREQUESTSTR, Toast.LENGTH_LONG).show();
				SmallClassActivity.this.finish();
			}
			pd.dismiss();
		}

	};
	private void Serach(int mm,int nn){
		pd.show();
		final int m=mm;
		final int n=nn;
		Thread thr=new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg=handler.obtainMessage();
				// TODO Auto-generated method stub
				JsonResultFactory factory=new SmallClassResultFactory();

				JSONArray mjsa=new JSONArray();
				JSONObject mjson=new JSONObject();
				try {
					mjson.put("type", type);
					mjson.put("keyWord",keyWord);
					mjson.put("userID",LoginActivity.getmUserID());
					mjson.put("m",m+"");
					mjson.put("n",n+"");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mjsa.put(mjson);
				//factory.setJsonStr(QufeiSocketClient.getInstance().send("GetSmallClass",mjsa));
				factory.setJsonStr(QufeiSocketClient.getInstance().send("GetSmallClassFromMToN",mjsa));
				JsonResult json;
				try{
					json=factory.createResult();
				}catch(Exception e){
					msg.arg1=AppConfig.SERVERERROR;
					msg.sendToTarget();
					return ;

				}
				if(json==null){
					msg.arg1=AppConfig.SERVERERROR;
					msg.sendToTarget();
					return ;
				}
				List<Model> list=json.getModels();
				if(list==null){
					msg.arg1=AppConfig.NODATAREQUEST;
					msg.sendToTarget();
					return ;

				}
				List<SmallClassModel> models=new ArrayList<SmallClassModel>();
				for(Model m:list){
					models.add((SmallClassModel)m);
				}
				SHIJISIZE=models.size();
				for(SmallClassModel sm:models){
					Map<String,String>map=new HashMap<String, String>();
					map.put("name", sm.getName());
					map.put("id",sm.getId());
					data.add(map);
				}
				msg.arg1=1;
				msg.sendToTarget();
			}
		});
		thr.start();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_small_class);
		init();

		Serach(0,ONETIMECELEN);//首次加载300条


	}
	public void loadMore()
	{
		JsonResultFactory factory=new SmallClassResultFactory();

		JSONArray mjsa=new JSONArray();
		JSONObject mjson=new JSONObject();
		try {
			mjson.put("type", type);
			mjson.put("keyWord",keyWord);
			mjson.put("userID",LoginActivity.getmUserID());
			mjson.put("m",data.size()+1);
			mjson.put("n",data.size()+ONETIMECELEN);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mjsa.put(mjson);
		//factory.setJsonStr(QufeiSocketClient.getInstance().send("GetSmallClass",mjsa));
		factory.setJsonStr(QufeiSocketClient.getInstance().send("GetSmallClassFromMToN",mjsa));
		JsonResult json;
		try{
			json=factory.createResult();
		}catch(Exception e){
			 
			return ;

		}
		List<Model> list=json.getModels();
		if(list==null){
			 
			return ;

		}
		List<SmallClassModel> models=new ArrayList<SmallClassModel>();
		for(Model m:list){
			models.add((SmallClassModel)m);
		}
		SHIJISIZE=models.size();
		for(SmallClassModel sm:models){
			Map<String,String>map=new HashMap<String, String>();
			map.put("name", sm.getName());
			map.put("id",sm.getId());
			data.add(map);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.small_class, menu);
		return true;
	}
	@Override
	public void OnLoadMore() {
		// TODO Auto-generated method stub
		//		Serach(data.size(),data.size()+ONETIMECELEN);
		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute();
	}
	@Override
	public void OnRefresh() {
		// TODO Auto-generated method stub
		mRefreshAsynTask = new RefreshDataAsynTask();
		mRefreshAsynTask.execute();
	}
	class RefreshDataAsynTask extends AsyncTask<Void , Void, Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {

			loadMore();
			


			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			
			adapter.notifyDataSetChanged();
			mListView.onRefreshComplete();

		}
	}
	class LoadMoreDataAsynTask extends AsyncTask<Void , Void, Void>
	{

		@Override
		protected Void doInBackground(Void... arg0) {


			loadMore();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub


			adapter.notifyDataSetChanged();

			if (SHIJISIZE <ONETIMECELEN)
			{
				mListView.onLoadMoreComplete(true);
			}else{
				mListView.onLoadMoreComplete(false);
			}

		}
	}


}
