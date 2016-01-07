
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
import com.qufei.androidapp.R;
import com.qufei.factory.Dict_�̶��ʲ�JsonResultFactory;
import com.qufei.model.Dict_�̶��ʲ�Model;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.Model;
import com.qufei.tools.QufeiSocketClient;

public class FenleihaoActivity extends Activity implements OnItemClickListener{
	ProgressDialog pd;
	String pTypeID="3";
	public TextView feileihao;
	private Spinner mySpinner1; 
	private Spinner mySpinner2;
	//private Spinner mySpinner3;
	private ListView resultList;
	private ArrayAdapter<String> adapter1;   
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	int bigSelectPos=0;//����ѡ���pos
	ImageButton imagebtn;//�����ť
	EditText edt_search;
	List<Dict_�̶��ʲ�Model>list1;
	String keyWord="";
	List<Dict_�̶��ʲ�Model>list2=new ArrayList<Dict_�̶��ʲ�Model>();
	List<Dict_�̶��ʲ�Model>list3=new ArrayList<Dict_�̶��ʲ�Model>();
	List<Dict_�̶��ʲ�Model>list4=new ArrayList<Dict_�̶��ʲ�Model>();
	int mpos=0;
	Handler handData=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==2){
				List<String >list=new ArrayList<String>();
				for(Dict_�̶��ʲ�Model m:list2){
					list.add(m.get����());
				}
				
				adapter2 = new ArrayAdapter<String>(FenleihaoActivity.this,android.R.layout.simple_spinner_item, list); 
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
				mySpinner2.setAdapter(adapter2);
				//�ٴε�������
				 search();
				mySpinner2.setOnItemSelectedListener( new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						pd.show();	
						
						final	int pos = arg2;
						

						new Thread(new Runnable() {
							Message msg=handData.obtainMessage();

							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								list3=getData(list2.get(pos).get�㼶��());
								
								if(list3==null)
								{
									
									msg.what=0;
									msg.obj=list2.get(pos).get�㼶��();
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
				for(Dict_�̶��ʲ�Model m:list3){
					list.add(m.get����());
				}
 
				if(edt_search.getText().length()!=0){
					//��������������
					keyWord=edt_search.getText().toString();
				}else{			
				BaseAdapter adapter=new SearchFelleihaoAdapter(FenleihaoActivity.this,list3);
				resultList.setAdapter(adapter);
				}
 
			}
			else{
				Toast.makeText(FenleihaoActivity.this, "��������ʧ��", Toast.LENGTH_SHORT).show();
			}
			check();
			pd.dismiss();
		};
	};
	Handler handInit=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){
				List<String >list=new ArrayList<String>();
	 				for(Dict_�̶��ʲ�Model m:list1){
					list.add(m.get����());
				}

				adapter1 = new ArrayAdapter<String>(FenleihaoActivity.this,android.R.layout.simple_spinner_item, list); 
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
				mySpinner1.setAdapter(adapter1);
				mySpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						bigSelectPos=arg2;
						 
						if(arg2==0){
							search();
							List<String>list=new ArrayList<String>();
							list.add("ȫ��");
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(FenleihaoActivity.this,android.R.layout.simple_spinner_item, list); 
							mySpinner2.setAdapter(adapter);
							
							return ;
						} 
						pd.show();						
						final int pos=arg2;
						new Thread(new Runnable() {
							Message msg= handData.obtainMessage();
							@Override
							public void run() {
							 
								list2=getData(list1.get(pos).get�㼶��());
								list2.set(0,new Dict_�̶��ʲ�Model("", "ȫ��", ""));
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
					 
						Toast.makeText(FenleihaoActivity.this, "Ϊ�ı�ԭ��ѡ��ֵ", Toast.LENGTH_SHORT).show();
					}
				});

			}else{
				Toast.makeText(FenleihaoActivity.this, "����������æ", Toast.LENGTH_SHORT).show();
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
					Dict_�̶��ʲ�Model md=new Dict_�̶��ʲ�Model("", "ȫ��", "");
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
			setContentView(R.layout.activity_fenleihao);
			pTypeID=getIntent().getStringExtra("pTypeID");
			if(pTypeID==null&&pTypeID.equals("")){
				Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();
				return ;
			}
			edt_search=(EditText) this.findViewById(R.id.query);
			edt_search.setHint("����ؼ���");
		
			imagebtn=(ImageButton)this.findViewById(R.id.search_clear);
			imagebtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					edt_search.setText("");
				}
			});
			pd=new ProgressDialog(this);
			pd.setMessage("���ڻ�ȡ����");
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setCancelable(false);
			mySpinner1=(Spinner) this.findViewById(R.id.spinner1);
			mySpinner2=(Spinner) this.findViewById(R.id.spinner2);
			resultList=(ListView) this.findViewById(R.id.result_list);
			resultList.setItemsCanFocus(true);
			resultList.setOnItemClickListener(this);
		 
			feileihao=(TextView)this.findViewById(R.id.textView2);
			initThread();
			 
			this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setResult(RESULT_CANCELED,getIntent());
					 

					FenleihaoActivity.this.finish();
				}
			});
			
			
			edt_search.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					List<String>list=new ArrayList<String>();
					list.add("ȫ��");				 
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(FenleihaoActivity.this,android.R.layout.simple_spinner_item, list); 
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
				
				BaseAdapter adapter=new SearchFelleihaoAdapter(FenleihaoActivity.this,list3);
				resultList.setAdapter(adapter);
				}else{
					list3.clear();
					BaseAdapter adapter=new SearchFelleihaoAdapter(FenleihaoActivity.this,list3);
					resultList.setAdapter(adapter);
					Toast.makeText(FenleihaoActivity.this, "δ�ҵ������", Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
				
			}
		};
		void search(){
			final String keyWord=edt_search.getText().toString();
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
						j.put("fatherCode", list1.get(bigSelectPos).get�㼶��());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jsa.put(j);
					JsonResult js=new Dict_�̶��ʲ�JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("SearchFenleihaoProcesser",jsa)).createResult();
					if(js==null){
						msg.what=0;
						msg.sendToTarget();
						return ;
					}
					List<Dict_�̶��ʲ�Model> list=new ArrayList<Dict_�̶��ʲ�Model>();
					List<Model>modelLst=js.getModels();
					for(Model m:modelLst){
						list.add((Dict_�̶��ʲ�Model) m);
					}
					msg.what=1;
					msg.obj=list;
					 
					list3=list;
					msg.sendToTarget();
					
					
				}
			}){}.start();
		}

		public List<Dict_�̶��ʲ�Model>getFatherData(String ptype){

			List<Dict_�̶��ʲ�Model>	list=new ArrayList<Dict_�̶��ʲ�Model>();
			JSONArray jsa=new JSONArray();
			JSONObject j=new JSONObject();

			try {
				j.put("userID",AppConfig.LoginUser.getUserid());
				j.put("pTypeID", pTypeID);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsa.put(j);
			JsonResult js=new Dict_�̶��ʲ�JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("GetFatherFenleihao",jsa)).createResult();
			List<Model>modelLst=js.getModels();
			for(Model m:modelLst){
				list.add((Dict_�̶��ʲ�Model) m);
			}
			return list;
		}

		public List<Dict_�̶��ʲ�Model>getData(String cengjima){

			//�ɲ㼶���ȡ������
			List<Dict_�̶��ʲ�Model>	list=new ArrayList<Dict_�̶��ʲ�Model>();
			JSONArray jsa=new JSONArray();
			JSONObject j=new JSONObject();

			try {
				j.put("fatherCode", cengjima);
				j.put("userID", AppConfig.LoginUser.getUserid());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsa.put(j);
			JsonResult js=new Dict_�̶��ʲ�JsonResultFactory().setJsonStr(QufeiSocketClient.getInstance().send("GetSonFenleihao",jsa)).createResult();
			List<Model>modelLst=js.getModels();
			for(Model m:modelLst){
				list.add((Dict_�̶��ʲ�Model) m);
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
			adapter = new ArrayAdapter<String>(FenleihaoActivity.this,android.R.layout.simple_spinner_item, list); 
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
			String feileihaoCode=list3.get(arg2).get����().toString();
			String shebeimingcheng=list3.get(arg2).get����();

			intent.putExtra("fenleihao", feileihaoCode);
			intent.putExtra("shebeimingcheng", shebeimingcheng);

			setResult(RESULT_OK,intent);

			FenleihaoActivity.this.finish();
		}
		

}
