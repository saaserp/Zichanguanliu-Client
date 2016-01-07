package com.qufei.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.factory.ImageJsonResultFactory;
import com.qufei.factory.TrueOrFalseJsonResultFactory;
import com.qufei.factory.XtResultFactory;
import com.qufei.imageloader.ProductImages;
import com.qufei.model.ImageModel;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.shengmingzhouqi.Shengmingzhouqi;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.NetworkDetector;
import com.qufei.tools.QufeiSocketClient;

public class ProductInfoActivity extends Activity {

	ListView lst_products;
	RelativeLayout layout;
	List<String>srcList;
	List<Map<String,String>>list;
	String keyword=null;
	String fromPage="";
	ProgressDialog pd;
	ImageView mImageView;
	ArrayList<String>urlList;
	TextView tv_qincha;

	//对应的map里的键的名字
	String [] from={"name","value"};
	int []to={R.id.item_name,R.id.item_value};
	String url;
	String typeStr="";
	int type=1;
	RelativeLayout layout_moreImage;
	JsonResult json=null;
	JsonResultFactory jsfactory=null;
	JSONArray mjsa;

	private class DownloadImageTask extends AsyncTask<String, Void, Drawable> 
	{

		protected Drawable doInBackground(String... urls) {
			return NetworkDetector.loadImageFromNetwork(urls[0]);
		}

		protected void onPostExecute(Drawable result) {
			mImageView.setImageDrawable(result);
		}
	}
	Handler handQincha=new Handler(){public void handleMessage(Message msg) {
		if(msg.what==0){
			Toast.makeText(ProductInfoActivity.this, "清查失败", Toast.LENGTH_SHORT).show();

		}else{
			Toast.makeText(ProductInfoActivity.this, "清查成功", Toast.LENGTH_SHORT).show();
			recive();
		}
	};};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_product_info);
		layout_moreImage=(RelativeLayout)this.findViewById(R.id.layout_moreImages);
		Intent intent=getIntent();
		tv_qincha=(TextView) this.findViewById(R.id.title_right);

		lst_products=(ListView)this.findViewById(R.id.lst_products);
		mImageView=(ImageView)this.findViewById(R.id.productImage0);
		pd=new ProgressDialog(this);
		pd.setMessage("正在查询...");
		pd.setCancelable(false);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//获取关键词
		keyword=intent.getStringExtra("keyWord");
		typeStr=intent.getStringExtra("type");
		fromPage=intent.getStringExtra("fromPage");
		if(fromPage!=null){
			if(fromPage.equals("main"))
			{
				//主页
				tv_qincha.setVisibility(View.VISIBLE);
				tv_qincha.setEnabled(true);
				tv_qincha.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						new Thread(new   Runnable() {
							Message msg=handQincha.obtainMessage();
							public void run() {
								JSONArray ja=new JSONArray();
								JSONObject j=new JSONObject();

								try {
									j.put("userID", AppConfig.LoginUser.getUserid());
									j.put("key", keyword);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								ja.put(j);
								TrueOrFalseJsonResultFactory fac=new TrueOrFalseJsonResultFactory();
								fac.setJsonStr(QufeiSocketClient.getInstance().send("QinchaProcesser", ja));
								JsonResult re=fac.createResult();
								if(re==null){
									msg.what=0;
									msg.sendToTarget();
									return ;
								} 
								TrueOrFalseModel m=(TrueOrFalseModel) re.getModels().get(0);
								if(m.getIsOk().equals("true")){
									msg.what=1;
									msg.sendToTarget();
								}else{
									msg.what=0;
									msg.sendToTarget();
								}
							}
						}){}.start();


					}
				});
			}
			else
				if(fromPage.equals("query"))
				{
					//查询
					tv_qincha.setVisibility(View.VISIBLE);
					tv_qincha.setEnabled(true);
					tv_qincha.setText("生命周期");
					tv_qincha.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(ProductInfoActivity.this,Shengmingzhouqi.class
									);
							intent.putExtra("id", keyword);
							
							startActivity(intent);
						}
					});
				} 
			 

		}
		else
		{
			tv_qincha.setEnabled(false);
		}


		urlList=new ArrayList<String>();





		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ProductInfoActivity.this.finish();
			}
		});
		layout_moreImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ProductInfoActivity.this,ProductImages.class);
				urlList.clear();				
				//				urlList.add(AppConfig.hostName+"/images/"+"p1.png");
				//				urlList.add(AppConfig.hostName+"/images/"+"p2.png");
				//				urlList.add(AppConfig.hostName+"/images/"+"p3.png");
				//				urlList.add(AppConfig.hostName+"/images/"+"p4.png");
				//				urlList.add(AppConfig.hostName+"/images/"+"p5.png");
				//				urlList.add(AppConfig.hostName+"/images/"+"p6.png");
				if(srcList==null){

					Toast.makeText(ProductInfoActivity.this, "没有可以显示的图片", Toast.LENGTH_SHORT).show();
					return ;
				}
				for(String s:srcList){
					urlList.add( "http://"+AppConfig.getInstance().getIp()+":"+AppConfig.getInstance().getHttpPort() +AppConfig.images+s);
				}
				intent.putStringArrayListExtra("imageURLs", urlList);
				startActivity(intent);

			}
		});



		recive();







	}
	public void recive(){
		String userID=AppConfig.getInstance().LoginUser.getUserid();

		mjsa=new JSONArray();
		JSONObject mjson=new JSONObject();
		try {
			mjson.put("userID", userID);
			mjson.put("keyWord", keyword);
			mjson.put("type", typeStr);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mjsa.put(mjson);
		pd.show();
		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=handler.obtainMessage();
				Map<String,String>map = null;
				Set<String> keSet;
				String keyName;
				try{
					JSONArray imagesJsa=new JSONArray();
					JSONObject imagej=new JSONObject();

					imagej.put("key",keyword);
					imagesJsa.put(imagej);
					JsonResult imagesRes=new ImageJsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("GetImage",imagesJsa)).createResult();
					srcList=((ImageModel)imagesRes.getModels().get(0)).getSrcList(); 




					XtResultFactory xtFac=new XtResultFactory();
					JSONArray ja2=new JSONArray();
					JSONObject j2=new JSONObject();
					j2.put("userID", AppConfig.LoginUser.getUserid());
					j2.put("keyWord", keyword);
					ja2.put(j2);
					xtFac.setJsonStr(QufeiSocketClient.getInstance().send("GetProductDetails", ja2));
					xtFac.createResult();					

					map=xtFac.getMapList().get(0);



					if(map==null){
						msg.arg1=0;

					}

					keSet=map.keySet();


					list=new ArrayList<Map<String,String>>();
					for (Iterator<String> iterator = keSet.iterator(); iterator.hasNext();) {

						keyName = iterator.next(); 
						Map<String,String>map2=new HashMap<String, String>();
						map2.put("name", keyName);
						map2.put("value", map.get(keyName));
						if(!keyName.contains("图片"))
							list.add(map2);
					}
					msg.arg1=1;

				}catch(Exception e){
					msg.arg1=0;
				}


				msg.sendToTarget();
			}
		});
		thread.start();

	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.arg1==1){
				SimpleAdapter adapter=new SimpleAdapter(ProductInfoActivity.this,list,R.layout.layout_product_info_item,from,to);       
				lst_products.setAdapter(adapter);
				tv_qincha.setEnabled(true);
				new DownloadImageTask().execute("http://"+AppConfig.getInstance().getIp()+":"+AppConfig.getInstance().getHttpPort()+AppConfig.images+srcList.get(0)) ;
			}else{
				tv_qincha.setEnabled(false);

				Toast.makeText(ProductInfoActivity.this, "查询不存在", Toast.LENGTH_SHORT);
				ProductInfoActivity.this.finish();
			}
			pd.dismiss();
		}

	};


}
