
package com.qufei.addproduct;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SearchRecentSuggestions;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.adapter.SearchFelleihaoAdapter;
import com.qufei.adapter.SearchGuoziFelleihaoAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.Dict_固定资产JsonResultFactory;
import com.qufei.factory.Dict_国资2010JsonResultFactory;
import com.qufei.model.Dict_国资2010Model;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class Guozifenleihao extends Activity implements OnItemClickListener{
	//1.加载第一项数据
	//2.设置首项为全部
	
	//当点击第一项时,才加载第二项
	//搜索有文字时,连同第一级一起,查询
	//搜索没有文字时,如果第二级不为全部,查询
	//搜索有文字时,第二级必为全部
	
	
	
	ProgressDialog pd;
	String pTypeID="3";
	public TextView feileihao;
	private Spinner mySpinner1; 
	private Spinner mySpinner2;
 
	private ListView resultList;
	private ArrayAdapter<String> adapter1;   
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	int bigSelectPos=0;//大类选择的pos
	ImageButton imagebtn;//清除按钮
	String keyWord="";
	EditText edt_search;
	List<Dict_国资2010Model>list1;
	List<Dict_国资2010Model>list2=new ArrayList<Dict_国资2010Model>();
	List<Dict_国资2010Model>list3=new ArrayList<Dict_国资2010Model>();
	List<Dict_国资2010Model>list4=new ArrayList<Dict_国资2010Model>();
	int mpos=0;
	Handler handData=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==2){
				List<String >list=new ArrayList<String>();
				for(Dict_国资2010Model m:list2){
					list.add(m.get名称());
				}
				
				adapter2 = new ArrayAdapter<String>(Guozifenleihao.this,android.R.layout.simple_spinner_item, list); 
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
				mySpinner2.setAdapter(adapter2);
				//再次调用搜索
				  search();
				mySpinner2.setOnItemSelectedListener( new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						
 
						
						final	int pos = arg2;
						if(pos==0)
						{//如果是第一项,这一项一定是全部,则
							  search();
							 
							return ;
						}
						pd.show();	
						new Thread(new Runnable() {
							Message msg=handData.obtainMessage();

							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								list3=getData(list2.get(pos).get代码());
								
								if(list3==null)
								{
									
									msg.what=0;
									msg.obj=list2.get(pos).get代码();
								}else
								{
									msg.what=3;
								}
								msg.sendToTarget();
							}
						}){}.start();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

			}
			else if(msg.what==3){
				List<String >list=new ArrayList<String>();
				for(Dict_国资2010Model m:list3){
					list.add(m.get名称());
				}
 
				if(edt_search.getText().length()!=0){
					//搜索里面有内容
					keyWord=edt_search.getText().toString();
				}else{			
					keyWord="";
				BaseAdapter adapter=new SearchGuoziFelleihaoAdapter(Guozifenleihao.this,list3);
				resultList.setAdapter(adapter);
				}
 
			}
			else{
				Toast.makeText(Guozifenleihao.this, "请求数据失败", Toast.LENGTH_SHORT).show();
			}
			check();
			pd.dismiss();
		};
	};
	Handler handInit=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				List<String >list=new ArrayList<String>();
				
				for(Dict_国资2010Model m:list1){
					list.add(m.get名称());
				}

				adapter1 = new ArrayAdapter<String>(Guozifenleihao.this,android.R.layout.simple_spinner_item, list); 
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
				mySpinner1.setAdapter(adapter1);
				mySpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						bigSelectPos=arg2;
						 
						 
						if(arg2==0){
							
							List<String>list=new ArrayList<String>();
							list.add("全部");
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(Guozifenleihao.this,android.R.layout.simple_spinner_item, list); 
							mySpinner2.setAdapter(adapter);
							return ;
						} 
						
						
						pd.show();
						
						final int pos=arg2;
						new Thread(new Runnable() {
							Message msg= handData.obtainMessage();
							@Override
							public void run() {
								// TODO Auto-generated method stub
								list2=getData(list1.get(pos).get代码());
								
								list2.add(0,new Dict_国资2010Model("", "全部" ));
								
								if(list2==null)
								{
									
									msg.what=0;
								}else
								{
									msg.what=2;
								}
								msg.sendToTarget();
							}
						}){}.start();
						
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(Guozifenleihao.this, "为改变原来选中值", Toast.LENGTH_SHORT).show();
					}
				});

			}else{
				Toast.makeText(Guozifenleihao.this, "服务器正在忙", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();

		};};
		public void initThread(){
			pd.show();
			new Thread(new Runnable() {
				Message msg=handInit.obtainMessage();
				@Override
				public void run() {
					// TODO Auto-generated method stub
					list1=getFatherData(pTypeID);
					Dict_国资2010Model md=new Dict_国资2010Model("", "全部" );
					//在首行插入"全部"
					list1.add(0,md);
					if(list1!=null){
						msg.what=1;
					}else{
						msg.what=0;
					}

					msg.sendToTarget();

				}
			}){}.start();
		}
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.activity_guozifenleihao);
			pTypeID=getIntent().getStringExtra("pTypeID");
			if(pTypeID==null&&pTypeID.equals("")){
				Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
				return ;
			}
			edt_search=(EditText) this.findViewById(R.id.query);
			edt_search.setHint("输入关键词");
		
			imagebtn=(ImageButton)this.findViewById(R.id.search_clear);
			imagebtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					edt_search.setText("");
				}
			});
			pd=new ProgressDialog(this);
			pd.setMessage("正在获取数据");
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setCancelable(false);
			mySpinner1=(Spinner) this.findViewById(R.id.spinner1);
			mySpinner2=(Spinner) this.findViewById(R.id.spinner2);
			resultList=(ListView) this.findViewById(R.id.result_list);
			resultList.setItemsCanFocus(true);
			resultList.setOnItemClickListener(this);
			//			mySpinner4=(Spinner) this.findViewById(R.id.spinner4);
			feileihao=(TextView)this.findViewById(R.id.textView2);
			initThread();
			 
			this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setResult(RESULT_CANCELED,getIntent());
					 

					Guozifenleihao.this.finish();
				}
			});
			
			
			edt_search.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					List<String>list=new ArrayList<String>();
					list.add("全部");				 
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(Guozifenleihao.this,android.R.layout.simple_spinner_item, list); 
					mySpinner2.setAdapter(adapter);
					search();
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					final String keyWord=edt_search.getText().toString();
					if(keyWord.length()!=0)
					imagebtn.setVisibility(View.VISIBLE);
					else
					imagebtn.setVisibility(View.INVISIBLE);
				}
			});
		 
		}
		final Handler handSearch=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what==1){
				
				BaseAdapter adapter=new SearchGuoziFelleihaoAdapter(Guozifenleihao.this,list3);
				resultList.setAdapter(adapter);
				}else{
					list3.clear();
					BaseAdapter adapter=new SearchGuoziFelleihaoAdapter(Guozifenleihao.this,list3);
					resultList.setAdapter(adapter);
					Toast.makeText(Guozifenleihao.this, "未找到相关项", Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
				
			}
		};
		void search(){
		  keyWord=edt_search.getText().toString();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message msg=handSearch.obtainMessage();
					JSONArray jsa=new JSONArray();
					JSONObject j=new JSONObject();
					try {
						j.put("userID", AppConfig.getInstance().LoginUser.getUserid());
						j.put("keyWord", keyWord);
						j.put("fatherCode", list1.get(bigSelectPos).get代码());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jsa.put(j);
					JsonResult js=new Dict_国资2010JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("SearchGuoziFenleihaoProcesser",jsa)).createResult();
					if(js==null){
						msg.what=0;
						msg.sendToTarget();
						return ;
					}
					List<Dict_国资2010Model> list=new ArrayList<Dict_国资2010Model>();
					List<Model>modelLst=js.getModels();
					for(Model m:modelLst){
						list.add((Dict_国资2010Model) m);
					}
					msg.what=1;
					msg.obj=list;
					 
					list3=list;
					msg.sendToTarget();
					
					
				}
			}){}.start();
		}

		public List<Dict_国资2010Model>getFatherData(String ptype){

			List<Dict_国资2010Model>	list=new ArrayList<Dict_国资2010Model>();
			JSONArray jsa=new JSONArray();
			JSONObject j=new JSONObject();

			try {
				j.put("userID",AppConfig.LoginUser.getUserid());
				j.put("pTypeID", pTypeID);
				j.put("keyWord",keyWord );
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsa.put(j);
			JsonResult js=new Dict_国资2010JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("GetFatherGuoziFenleihao",jsa)).createResult();
			List<Model>modelLst=js.getModels();
			for(Model m:modelLst){
				list.add((Dict_国资2010Model) m);
			}
			return list;
		}

		public List<Dict_国资2010Model>getData(String cengjima){

			//由层级码获取到数据
			List<Dict_国资2010Model>	list=new ArrayList<Dict_国资2010Model>();
			JSONArray jsa=new JSONArray();
			JSONObject j=new JSONObject();

			try {
				j.put("fatherCode", cengjima);
				j.put("userID", AppConfig.LoginUser.getUserid());
				j.put("keyWord",keyWord);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsa.put(j);
			JsonResult js=new Dict_国资2010JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("SearchGuoziFenleihaoProcesser",jsa)).createResult();
			if(js==null)
			{
				return null;
			}
			List<Model>modelLst=js.getModels();
			for(Model m:modelLst){
				list.add((Dict_国资2010Model) m);
			}
			return list;







		}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.fenleihao, menu);
			return true;
		}

		public void check(){
			ArrayAdapter<String> adapter;
			List<String>list=new ArrayList<String>();
			adapter = new ArrayAdapter<String>(Guozifenleihao.this,android.R.layout.simple_spinner_item, list); 
			if(list1.size()==0){
				list2.clear();
				mySpinner2.setAdapter(adapter);
			}
			if(list2.size()==0)
			{
				list3.clear();
				resultList.setAdapter(adapter);			 

			}
		}
		 
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Uri data=Uri.parse("");
			Intent intent=new Intent(null,data);
			String feileihaoCode=list3.get(arg2).get代码().toString();
			String shebeimingcheng=list3.get(arg2).get名称();

			intent.putExtra("fenleihao", feileihaoCode);
			intent.putExtra("shebeimingcheng", shebeimingcheng);

			setResult(RESULT_OK,intent);

			Guozifenleihao.this.finish();
		}
		

}
