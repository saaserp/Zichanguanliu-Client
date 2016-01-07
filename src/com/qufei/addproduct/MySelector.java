package com.qufei.addproduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarOutputStream;
import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.androidapp.R;
import com.qufei.tools.AppConfig;
import com.qufei.tools.QufeiSocketClient;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MySelector extends DialogFragment   implements OnClickListener{
	Context context;
	public  Map<String,String>data0;
	public List<Map<String,String>>data;
	public MySelector(Context context,Map<String,String>data1){
		this.context=context;
		this.data0=data1;
		data=new ArrayList<Map<String,String>>();

		new Thread(new Runnable() {


			Message msg=hand.obtainMessage();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSONArray jsa=new JSONArray();
				JSONObject
				jo=new JSONObject();
				Test();
				try {
					jo.put("userID",AppConfig.app.LoginUser.getUserid());

					if(data0.get("name").equals("使用单位"))
					{
						//管理单位比较特殊,需要级联
						jo.put("tName", AddProductActivity.sydwnewSql);
					}else
					{
						jo.put("tName", (String)data0.get("tName"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				jsa.put(jo);
				String s=QufeiSocketClient.getInstance().send("GetXuanxiang",jsa);
				if(s==null){
					msg.what=0;
					msg.sendToTarget();
					return ;

				}
				try {
					JSONArray ja=new JSONArray(s);
					for(int i=0;i<ja.length();i++){
						JSONObject j=ja.getJSONObject(i);
						Map<String,String>m=new HashMap<String, String>();

						m.put("name", j.getString("code")+" - "+j.getString("name"));
						data.add(m);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				msg.what=1;
				msg.obj=data;
				msg.sendToTarget();

			}
		}){}.start();
	}
	public void Test(){
		;;

		return ;

	}
	ListView list;
	Handler hand =new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==1){
				String []from={"name"};
				int [] to={R.id.text};
				SimpleAdapter adp=new SimpleAdapter(context, data, R.layout.item_xuanxiang, from,to);
				list.setAdapter(adp);

			}else
			{
				Toast.makeText(context, "加载失败,请稍后再试!", Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}
	};
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState){  
		int style = DialogFragment.STYLE_NO_TITLE, theme = 0; 
		setStyle(style,theme);  

		View view = inflater.inflate(R.layout.layout_xuanxiang, container);
		list=(ListView) view.findViewById(R.id.list);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub				
				String selectName=data.get(arg2).get("code")+data.get(arg2).get("name");
				selectName=selectName.replace("null", "");
				Message msg=AddProductActivity.handModyfi.obtainMessage();
				msg.what= AddProductActivity.postion;
				data0.remove("pos");
				data0.remove("default");
				data0.put("default",selectName);


				msg.obj=data0;
				msg.sendToTarget();
				MySelector.this.dismiss();


			}
		});
		return view;  
	}  
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
