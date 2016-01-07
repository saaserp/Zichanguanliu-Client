package com.qufei.adapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.qufei.addproduct.AddProductActivity;
import com.qufei.addproduct.FenleihaoActivity;
import com.qufei.addproduct.Guozifenleihao;
import com.qufei.addproduct.ImageSelectDialog;
import com.qufei.addproduct.MDatePicker;
import com.qufei.addproduct.MySelector;
import com.qufei.addproduct.ProductTypeActivity;
import com.qufei.addproduct.SetValueActivity;
import com.qufei.androidapp.R;
import com.qufei.tools.AppConfig;

public class AddProductMainAdapter extends BaseAdapter{

	private List<Map<String,Object>> data;
	Context mContext;

	private Map<String,String>extInfo;
	public AddProductMainAdapter(Context context, List<Map<String,Object>> data,Map<String,String> extInfo){

		mContext = context;
		this.data = data;

		this.extInfo=extInfo;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}


	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}


	Handler handImage=new Handler(){
		public void handleMessage(android.os.Message msg) {
			@SuppressWarnings("unchecked")
			Map<String,Object> map=(Map<String, Object>) msg.obj;
			ImageView imageView=(ImageView)map.get("imageView");
			Bitmap bitmap=(Bitmap)map.get("bitmap");
			if(msg.what==1){
				if(bitmap!=null){
					imageView.setImageBitmap(bitmap);
				}else{
					imageView.setImageResource(R.drawable.vm);
				}

			}else
			{
				imageView.setImageResource(R.drawable.vm);
				Toast.makeText(mContext, "有一张图片获取失败，请重试。", Toast.LENGTH_SHORT).show();
			}
		};
	};

	
	@Override
	public View getView(int index, View convertView, ViewGroup arg2) {

		final String optional=(String)data.get(index).get("optional");
		final int pos=index;

		convertView= LayoutInflater.from(mContext).inflate(com.qufei.androidapp.R.layout.item_add_product_image, null);






		if(optional.equals("3")){//如果是图片，则切换到图片项

			TextView tv_k=(TextView) convertView.findViewById(R.id.tv_id1);
			tv_k.setText((String) data.get(index).get("name"));
			String imgPath=(String) data.get(index).get("default"); 
			final ImageView imgView=(ImageView)convertView.findViewById(R.id.imageView1);

			//Bitmap bitmap =(Bitmap) data.get(index).get("bitMap");
			Bitmap bitmap=AppConfig.getLocalBitmapInstance().get(imgPath);
			if(bitmap!=null){
				imgView.setImageBitmap(bitmap);
			}
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final ImageSelectDialog imageSelectDialog=new ImageSelectDialog(mContext);
					 
					imageSelectDialog.show();
					AddProductActivity.postion=pos;
					ListView lv_imageselect_dialog = (ListView) imageSelectDialog.findViewById(R.id.lv_imageselect_dialog);
					 
					lv_imageselect_dialog.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							Intent intent = null;



							switch (position) {
							case 0:
								intent = new Intent("android.media.action.IMAGE_CAPTURE");

								((Activity)mContext).startActivityForResult(intent, AddProductActivity.REQUESTCODE_TWO);
								break;
							case 1:
							 				

								intent = new Intent(
										Intent.ACTION_PICK,
										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								((Activity)mContext).startActivityForResult(intent, AddProductActivity.REQUESTCODE_TWO);

								break;
							default:
								break;
							}
							imageSelectDialog.dismiss();
						}
					});
				}
			});
			convertView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
					dialog.setTitle("提示").setIcon(android.R.drawable.ic_dialog_info).setMessage("是否要删除该图片").setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//转跳到另外一个Activity
							// TODO Auto-generated method stub
							data.remove(pos);
							AddProductMainAdapter.this.notifyDataSetChanged();

							Toast.makeText(mContext, "图片删除成功！", Toast.LENGTH_SHORT).show();
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {


						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();//取消弹出框
						}
					}).create().show();
					return false;
				}
			});


			return convertView;
		}

		convertView = LayoutInflater.from(mContext).inflate(com.qufei.androidapp.R.layout.item_add_product_txt, null);
		TextView txt_Key=(TextView) convertView.findViewById(R.id.txt_key);
		TextView txt_value=(TextView) convertView.findViewById(R.id.txt_value);
		final String name=(String) data.get(index).get("name");
		final String value=(String) data.get(index).get("default");
		final String tName=(String)data.get(index).get("tName");
		final String isNull=(String)data.get(index).get("isNull");
		final String type=(String)data.get(index).get("type");
		


		if(name==null)
		{
			return convertView;
		}

		//optional
		//-2 不显示
		//-1不可编辑

		//0普通编辑
		//1,2 跳转页面的id
		//3图片
		//4国资分类号
		if(optional.equals("-2")){
			convertView=LayoutInflater.from(mContext).inflate(com.qufei.androidapp.R.layout.item_temp, null); ;
		}else 
		{
			txt_Key.setText(name);
			if(name.equals("录入人")){

				txt_value.setText(AppConfig.LoginUser.getUsername());
			}else{

				txt_value.setText(value);
			}
		}






		convertView.setOnClickListener(new OnClickListener() {


			 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AddProductActivity.postion=pos;
				if(optional.equals("0")){//普通的编辑
					//支持各种类型,String,int,DateTime
					Intent intent=new Intent(mContext,SetValueActivity.class);
					intent.putExtra("pos", pos);
					intent.putExtra("name", name);
					intent.putExtra("default", value);
					intent.putExtra("type", type);//数据类型
					intent.putExtra("optional", optional);
					intent.putExtra("tName", tName);
					intent.putExtra("isNull", isNull);
					if(name.equals("单价")||name.equals("数量")){
						//涉及到有金额
						intent.putExtra("isJine", "true");
					}else{
						intent.putExtra("isJine", "false");
					}
					
					((Activity) mContext).startActivityForResult(intent, 0);
				}else
					if(optional.equals("1")){//产品类别
						Intent intent=new Intent(mContext,ProductTypeActivity.class);
						intent.putExtra("pos", pos);
						intent.putExtra("name", name);
						intent.putExtra("default", value);
						intent.putExtra("type", type);//数据类型
						intent.putExtra("optional", optional);
						intent.putExtra("tName", tName);
						intent.putExtra("isNull", isNull);
						((Activity) mContext).startActivityForResult(intent, 0);

					}
					else if(optional.equals("2")){//分类号
						Intent intent=new Intent(mContext,FenleihaoActivity.class);
						//((Activity) mContext).startActivity(intent);
						intent.putExtra("pTypeID", extInfo.get("pTypeID"));
						((Activity) mContext).startActivityForResult(intent, 1);
					}else if(optional.equals("3")){
						//图片，目前放在外面做了
					}if(optional.equals("4")){//国资分类号
						Intent intent=new Intent(mContext,Guozifenleihao.class);
						intent.putExtra("pTypeID", extInfo.get("pTypeID"));
						((Activity) mContext).startActivityForResult(intent, 4);
					}if(optional.equals("5")){//日期	
						Map<String,String>mmp=new HashMap<String, String>();
						mmp.put("pos", pos+"");
						mmp.put("name", name);
						mmp.put("default", value);
						mmp.put("type", type);
						mmp.put("optional", optional);
						mmp.put("tName",tName);
						mmp.put("isNull",isNull);
						MDatePicker dp=new MDatePicker(mmp);
						dp.show( ((Activity)mContext).getFragmentManager(),"datePicker"); 
					}
					if(optional.equals("6")){
					//资产来源等....
						Map<String,String>mmp=new HashMap<String, String>();
						mmp.put("pos", pos+"");
					 	mmp.put("name", name);
						mmp.put("default", value);
						mmp.put("type", type);
						mmp.put("optional", optional);
						mmp.put("tName",tName);
						mmp.put("isNull",isNull);
						MySelector myselector=new MySelector(mContext,mmp);						 
						myselector.show(((Activity) mContext).getFragmentManager(), "myselector");
						
					} 
					 
					 
			}
		});
		return convertView;
	}
	
	
	

}
