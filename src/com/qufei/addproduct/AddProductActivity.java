package com.qufei.addproduct;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qufei.adapter.AddProductMainAdapter;
import com.qufei.adapter.AddProductSubAdapter;
import com.qufei.androidapp.R;
import com.qufei.factory.AddProductResultFacory;
import com.qufei.factory.TrueOrFalseJsonResultFactory;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.tools.AppConfig;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.MediaHelper;
import com.qufei.tools.QufeiSocketClient;
import com.qufei.tools.StreamTool;
import com.qufei.tools.UploadLogService;

public class AddProductActivity extends Activity {
	private ImageSelectDialog imageSelectDialog;
	public static final int REQUESTCODE_ONE = 3;
	private String productType="";
	public TextView btnSave;
	public static int tail=0;//录入的时候确保编号唯一
	public static boolean changed=false;
	static ListView lvMain;
	public static ListView lvSub;
	private static String pTypeID;
	private static int mod;//0表示新建;1表示覆盖
	private static String productId; //产品的id
	public static final int REQUESTCODE_ZERO = -1;//设置图片
	public static final int REQUESTCODE_TWO = 5;//给某一项设置图片
	public int maxPicCount=7;
	private Map<String,String>extInfo;
	ProgressDialog pd;
	ProgressDialog uploadbar;
	Set<String> keSetMain;
	static String sydwnewSql="";
	String title;
	String keySetMainArray[];
	public static int SETKEYVALUEACTIVITYCODE=1;
	public static AddProductSubAdapter addProductSubAdapter=null;
	static AddProductMainAdapter addProductMainAdapter=null;
	public static List<Map<String,String>> productSubData;
	public static List<Map<String,Object>> productMainData;
	public static int postion=0;

	public  void showdialog(int position1) {	 
		//获取当前图片的个数
		if(getImageCount()>=maxPicCount){
			//当前的个数超过了标点的个数
			Toast.makeText(AddProductActivity.this, "图片不能超过"+maxPicCount+"个", Toast.LENGTH_LONG).show();
			return ;
		}
		imageSelectDialog=new ImageSelectDialog(this);

		imageSelectDialog.show();

		ListView lv_imageselect_dialog = (ListView) imageSelectDialog.findViewById(R.id.lv_imageselect_dialog);

		lv_imageselect_dialog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;



				switch (position) {
				case 0:
					intent = new Intent("android.media.action.IMAGE_CAPTURE");

					startActivityForResult(intent, REQUESTCODE_ONE);
					break;
				case 1:


					intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(intent, REQUESTCODE_ONE);

					break;
				default:
					break;
				}
				imageSelectDialog.dismiss();
			}
		});
	}
	public int getImageCount(){
		int n=0;
		for(int i=0;i<productMainData.size();i++)
		{
			Map<String,Object>m=productMainData.get(i);
			if(m.get("name").equals("图片")){
				n++;				
			}
		}
		return n;
	}
	public static int getPostion(String name){
		int n=0;
		for(int i=0;i<productMainData.size();i++){
			Map<String,Object>m=productMainData.get(i);
			if(m.get("name").equals(name)){
				n=i;
			}
		}
		return n;
	}
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data0){
		super.onActivityResult(requestCode, resultCode, data0);

		Log.i("result",requestCode+"|"+resultCode);
		if(requestCode==0){//主表
			if(resultCode==Activity.RESULT_OK){
				int pos=data0.getIntExtra("pos", -1);
				String newValue=data0.getStringExtra("default");
				String dataType=data0.getStringExtra("type");
				String optional=data0.getStringExtra("optional");
				String name=data0.getStringExtra("name");
				String tName=data0.getStringExtra("tName");
				String isNull=data0.getStringExtra("isNull");
				String isJine=data0.getStringExtra("isJine");
				if(pos<0){
					Toast.makeText(AddProductActivity.this, "找不到对象", Toast.LENGTH_SHORT).show();
					return ;
				}

				Map<String,Object>map=new HashMap<String, Object>();
				map.put("name",name);
				map.put("default", newValue);
				map.put("type", dataType);
				map.put("optional", optional);
				map.put("tName", tName);
				map.put("isNull", isNull);
				productMainData.set(pos, map);
				//如果涉及到金额,则自动累加求和
				if(isJine.equals("true")){
					//获取单价的位置
					//获取数量的位置
					//获取总金额的位置
					int danjiaPos=getPostion("单价");
					int shuliangPos=getPostion("数量");
					int zongjinePos=getPostion("总金额");
					String danjia;
					String shuliang;
					String zongjine="0";
					if(danjiaPos!=0&&shuliangPos!=0&&zongjinePos!=0){
						//若单价存在并且数量存在并且总金额存在
						danjia=(String)productMainData.get(danjiaPos).get("default");
						shuliang=(String)productMainData.get(shuliangPos).get("default");
						//检测数值是否合法
						double dj;
						int sl;
						if(shuliang.length()>8)
						{
							Toast.makeText(AddProductActivity.this, "数量输入有误,请重新输入!",Toast.LENGTH_SHORT).show();

							dj=0;

							sl=0;

						}else{
							dj=Double.parseDouble(danjia.trim().equals("")?"0":danjia.trim()) ;

							sl=Integer.parseInt(shuliang.trim().equals("")?"0":shuliang.trim());
						}


						double zje=dj*sl;
						Map<String,Object>zmp=new HashMap<String, Object>();
						zmp.put("name",(String)productMainData.get(zongjinePos).get("name"));
						zmp.put("default", zje+"");
						zmp.put("type", (String)productMainData.get(zongjinePos).get("type"));
						zmp.put("optional",  (String)productMainData.get(zongjinePos).get("optional"));
						zmp.put("tName",  (String)productMainData.get(zongjinePos).get("tName"));
						zmp.put("isNull", (String)productMainData.get(zongjinePos).get("isNull"));
						productMainData.set(zongjinePos, zmp);

					}

				}

				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
				lvMain.setAdapter(addProductMainAdapter);
				lvMain.setSelection(pos);
			}
		}
		else if(requestCode==1){//设置分类号以及名称
			if(resultCode==Activity.RESULT_OK){
				String fenleihao=data0.getStringExtra("fenleihao");
				String shebeimingcheng=data0.getStringExtra("shebeimingcheng");
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("name","分类号");
				map.put("default", fenleihao);
				map.put("type", "String");
				map.put("optional", "2");//分类号编辑
				map.put("tName", "");
				map.put("isNull", "0");//必填
				int m=getPostion("分类号");
				productMainData.set(m, map);
				Map<String,Object>map2=new HashMap<String, Object>();				
				map2.put("name",productType.replace("编号", "名称"));
				map2.put("default", shebeimingcheng);
				map2.put("type", "String");
				map2.put("optional", "0");//普通的编辑
				map2.put("tName", "");
				map2.put("isNull", "0");//必填
				int n=getPostion(productType.replace("编号", "名称"));
				productMainData.set(n, map2);//这里的3到时候改成动态的
				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
				lvMain.setAdapter(addProductMainAdapter);
				lvMain.setSelection(n);
			}else{
				Toast.makeText(this, "未改动", Toast.LENGTH_SHORT).show();
			}

		} else if(requestCode==4){//这种国资分类号及名称
			if(resultCode==Activity.RESULT_OK){
				String fenleihao=data0.getStringExtra("fenleihao");
				String shebeimingcheng=data0.getStringExtra("shebeimingcheng");
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("name","国资分类号");
				map.put("default", fenleihao);
				map.put("type", "String");
				map.put("tName", "");
				map.put("isNull", "0");//必填
				int n=getPostion("国资分类号");
				map.put("optional", "4");//国资分类号编辑
				productMainData.set(n, map);
				Map<String,Object>map2=new HashMap<String, Object>();
				map2.put("name","国资大类");
				map2.put("default", shebeimingcheng);
				map2.put("type", "String");
				map2.put("optional", "0");//普通的编辑
				map2.put("tName", "");
				map2.put("isNull", "0");//必填
				productMainData.set(getPostion("国资大类"), map2);//这里的3到时候改成动态的
				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
				lvMain.setAdapter(addProductMainAdapter);
				lvMain.setSelection(n);

			}else{
				Toast.makeText(this, "未改动", Toast.LENGTH_SHORT).show();
			}

		}else			
			if(requestCode==REQUESTCODE_ONE){
				if(resultCode==Activity.RESULT_OK){

					Uri selectedImage = data0.getData();
					if(selectedImage==null){
						Bundle bundle = data0.getExtras();  
						Bitmap  bitmap1 = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
						if (data0.getData() != null)  
						{  
							selectedImage = data0.getData();  
						}  
						else  
						{  
							selectedImage  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap1, null,null));      
						}  
					}


					String[] filePathColumn = { MediaStore.Images.Media.DATA };
					ContentResolver resolver = getContentResolver();
					Cursor cursor = resolver.query(selectedImage, filePathColumn,
							null, null, null);

					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();

					String imageFilePath = cursor.getString(colunm_index);





					FileInputStream fis = null;
					try {
						fis = new FileInputStream(imageFilePath);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					Bitmap bitmap = BitmapFactory.decodeStream(fis);

					bitmap = comp(bitmap);
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("name", "图片");
					map.put("default", imageFilePath);	
					map.put("type","String");
					map.put("optional","3");//3表示图片类型
					map.put("tName", "");
					map.put("isNull", "1");//图片可以为空
					//map.put("bitMap",bitmap);
					if(!AppConfig.getLocalBitmapInstance().containsKey(imageFilePath)){
						AppConfig.getLocalBitmapInstance().put(imageFilePath, bitmap);
					}
					if(!AppConfig.getNetLocalInstance().containsKey(imageFilePath)){
						AppConfig.getNetLocalInstance().put(imageFilePath, imageFilePath);
					}
					productMainData.add(map);			
					addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
					lvMain.setAdapter(addProductMainAdapter);
					lvMain.setSelection(addProductMainAdapter.getCount()-1);

				}
			}
			else if(requestCode==REQUESTCODE_TWO){//表示从项里跳出的

				if(resultCode==Activity.RESULT_OK){
					int pos=data0.getIntExtra("pos", postion);
					Uri selectedImage = data0.getData();
					if(selectedImage==null){
						Bundle bundle = data0.getExtras();  
						Bitmap  bitmap1 = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
						if (data0.getData() != null)  
						{  
							selectedImage = data0.getData();  
						}  
						else  
						{  
							selectedImage  = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap1, null,null));      
						}  
					}


					String[] filePathColumn = { MediaStore.Images.Media.DATA };
					ContentResolver resolver = getContentResolver();
					Cursor cursor = resolver.query(selectedImage, filePathColumn,
							null, null, null);

					int colunm_index = cursor
							.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();

					String imageFilePath = cursor.getString(colunm_index);





					FileInputStream fis = null;
					try {
						fis = new FileInputStream(imageFilePath);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					Bitmap bitmap = BitmapFactory.decodeStream(fis);

					bitmap = comp(bitmap);




					Map<String,Object> map=new HashMap<String, Object>();
					map.put("name", "图片");
					map.put("default", imageFilePath);	
					map.put("type","String");
					map.put("optional","3");//3表示图片类型
					map.put("tName", "");
					map.put("isNull", "1");//图片可以为空
					//map.put("bitMap",bitmap);
					if(!AppConfig.getLocalBitmapInstance().containsKey(imageFilePath)){
						AppConfig.getLocalBitmapInstance().put(imageFilePath, bitmap);
					}
					if(!AppConfig.getNetLocalInstance().containsKey(imageFilePath)){
						AppConfig.getNetLocalInstance().put(imageFilePath, imageFilePath);
					}
					productMainData.set(pos, map);			
					addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
					lvMain.setAdapter(addProductMainAdapter);
					lvMain.setSelection(pos);
				}


			}



	}
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1){


				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);

				lvMain.setAdapter(addProductMainAdapter);
				btnSave.setText("保存");
				btnSave.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						setUpSaveMainTread();
					}
				});

			}else{
				Toast.makeText(AddProductActivity.this, "网络未连接", Toast.LENGTH_SHORT).show();
			}
			pd.dismiss();
		};
	};
	private String   attribute;



	public void addTextItemOnSub(String key,String value){
		Map<String,String>map=new HashMap<String, String>();
		map.put("keyName", key);
		map.put("keyValue", value);

		productSubData.add(map);
		addProductSubAdapter=new AddProductSubAdapter(this, productSubData);
		lvSub.setAdapter(addProductSubAdapter);
		//让listView显示最后
		lvSub.smoothScrollToPosition(productSubData.size() - 1);
	}
	@Override
	public boolean  onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_newproduct:
			this.init();			
			break;
		}
		return false;



	}
	public void init(){

		//传入的参数
		pTypeID=getIntent().getStringExtra("pTypeID");
		if(pTypeID==null){
			Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
		}
		extInfo=new HashMap<String, String>();
		extInfo.put("pTypeID", pTypeID);
		Log.i("pTypeID",pTypeID);
		try{
			productId=getIntent().getStringExtra("id");
		}catch(Exception e){
			mod=0;
		}
		attribute=getIntent().getStringExtra("attribute");
		mod=getIntent().getIntExtra("mod", 0);
		Log.i("mod",mod+"");
		if(pTypeID==null||pTypeID==""){
			pTypeID="1";
			Toast.makeText(this, "这是在测试呢", Toast.LENGTH_LONG).show();
		}


		productSubData=new ArrayList<Map<String,String>>();
		productMainData=new ArrayList<Map<String,Object>>();
		TextView tv_title=(TextView) this.findViewById(R.id.title_text);

		tv_title.setText(title);
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(changed)//只有在按了保存按钮后才刷新
					setResult(RESULT_OK);


				AddProductActivity.this.finish();
			}
		});




		lvMain=(ListView)this.findViewById(R.id.lvConstantKeyValue);

		lvSub=(ListView) this.findViewById(R.id.lvKeyValue);

		doThread();

		addProductSubAdapter=new AddProductSubAdapter(this, productSubData);
		lvSub.setAdapter(addProductSubAdapter);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_add_product);	
		title=getIntent().getStringExtra("title");
		if(title==null){
			title="";
		}
		uploadbar=new ProgressDialog(this);
		uploadbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		uploadbar.setCancelable(false);
		btnSave =(TextView) this.findViewById(R.id.title_right);
		btnSave.setText("");		 

		init();
		TextView tv_title=(TextView) this.findViewById(R.id.title_text);
		if(mod==0)
			tv_title.setText(title+"新建资产");
		else if(mod==1){
			tv_title.setText("修改资产");
		}

		this.findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showdialog(0);
			}
		});

	}



	void doThread(){
		pd=new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("正在加载...");
		pd.show();
		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg =hand.obtainMessage();

				productMainData=getMainData();
				if(productMainData==null){
					msg.what=0;
				}else{
					msg.what=1;
				}
				msg.sendToTarget();
			}
		});
		thread.start();
	}
	List<Map<String,Object>> getMainData(){
		List<Map<String,Object>>data=new ArrayList<Map<String,Object>>();
		new AddProductResultFacory();



		Log.i("attribute","页面的"+attribute);
		JSONArray atJsonA=null;
		try {
			atJsonA=new JSONArray(attribute);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for(int i=0;i<atJsonA.length();i++){
			//获取每一项
			try {

				JSONObject attrJson=atJsonA.getJSONObject(i);
				Map<String,Object>mp=new HashMap<String, Object>();
				if(mod==0){//如果是新增加，则产品编号要变化
					if(attrJson.getString("name").contains("编号")==true){
						String oldDefault=attrJson.getString("default");
						productType=attrJson.getString("name");
						attrJson.remove("default");
						attrJson.put("default", oldDefault+(tail++));
					}
				}
				String ptype=null;
				try{
					ptype=attrJson.getString("pTypeID");
				}catch(Exception e){
					ptype=attrJson.getString("type");
				}
				mp.put("tName", attrJson.getString("tName"));
				mp.put("isNull", attrJson.getString("isNull"));
				mp.put("name",attrJson.getString("name"));
				//获取最大图片个数
				if(attrJson.getString("name").equals("图个数")==true){
					maxPicCount=Integer.parseInt( attrJson.getString("default"));

				}
				mp.put("type", ptype);
				mp.put("optional", attrJson.getString("optional"));
				if(attrJson.getString("name").contains("图片")){
					//如果有图片，则下载图片到本地
					String httpPath=attrJson.getString("default");

					Bitmap bitmap;
					String cashName=AppConfig.toCashName(httpPath);
					if(!AppConfig.getNetLocalInstance().containsKey(httpPath))//如果在保存本地记录里面没有
					{
						//下载文件
						bitmap=MediaHelper.getHttpBitmap("http://"+AppConfig.getInstance().getIp()+"/"+httpPath);
						if(bitmap!=null){
							//下载成功
							String fileName=MediaHelper.saveBitmap(cashName, bitmap);
							if(null==fileName)
							{
								//图片保存失败
								Log.i("cashName","本地保存失败");
							}
							else{
								//图片保存成功

								AppConfig.getNetLocalInstance().put(httpPath, fileName);
								if(!AppConfig.getLocalBitmapInstance().containsKey(fileName))
									AppConfig.getLocalBitmapInstance().put(fileName,bitmap);
								mp.put("default",fileName);//本地文件作为data里的值
							}
						}else{

							Log.i("imagedownloadfaild","图片下载失败");
						}

					}else{
						mp.put("default",AppConfig.netLocalMap.get(httpPath));
					}
				}else{		


					mp.put("default", attrJson.getString("default"));
				}
				data.add(mp);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}




		return data;






	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product, menu);



		return true;
	}
	private Bitmap comp(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();        
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出    
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;//这里设置高度为800f
		float ww = 480f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
	}
	private Bitmap compressImage(Bitmap image) {  

		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
		int options = 100;  
		while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
			baos.reset();//重置baos即清空baos  
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
			options -= 10;//每次都减少10  
		}  
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
		return bitmap;  
	}  



	Handler handSaveMain=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.arg1==1){
				//保存子项
				//setUpsaveSub();
				changed=true;
				Toast.makeText(AddProductActivity.this, "保存成功", Toast.LENGTH_LONG).show();
				if(mod==0)//如果是新建模式
				{

					AddProductActivity.this.finish();


				}


			}else{
				uploadbar.dismiss();

			}

			pd.dismiss();
		};
	};
	Handler handSaveSub=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.arg1==1){
				Toast.makeText(AddProductActivity.this, "保存成功", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(AddProductActivity.this, "保存失败", Toast.LENGTH_LONG).show();
			}
			pd.dismiss();
		};
	};
	Handler handUploadImage=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==1){
				@SuppressWarnings("unchecked")
				Map<String,String>map=(Map<String, String>) msg.obj;
				String fileServerPath=map.get("filePath");//服务器的路径
				String filePath=map.get("fileKey");//本地地址
				if(!AppConfig.getLocalNetInstance().containsKey(filePath))
					AppConfig.getLocalNetInstance().put(filePath, fileServerPath);
				uploadbar.dismiss();
			}else{ 
				int length = msg.getData().getInt("size");  
				uploadbar.setProgress(length);  
				@SuppressWarnings("unused")
				float num = (float)uploadbar.getProgress()/(float)uploadbar.getMax();  
				// resulView.setText(result+ "%");  
				if(uploadbar.getProgress()==uploadbar.getMax()){  
					//Toast.makeText(AddProductActivity.this, "成功", 1).show();
					uploadbar.setProgress(0);
					uploadbar.dismiss();
				}  
			}
		};
	};
	private boolean start=true;
	private UploadLogService logService; 
	private boolean saveImage(String fileKey ,final File uploadFile){
		Socket socket = null ;
		try {

			uploadbar.setMax((int)uploadFile.length());  
			logService = new UploadLogService(this);  
			String souceid = logService.getBindId(uploadFile);  
			String head = "Content-Length="+ uploadFile.length() + ";filename="+ uploadFile.getName() + ";sourceid="+  
					(souceid==null? "" : souceid)+"\r\n";  
			socket = new Socket(AppConfig.getInstance().getIp() ,Integer.parseInt(AppConfig.getInstance().getSocketPort())); 
			OutputStream outStream = socket.getOutputStream();  
			outStream.write(head.getBytes()); 
			PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());
			String response = StreamTool.readLine(inStream);
			Log.i("response",response);

			String[] items = response.split(";");  
			String responseid = items[0].substring(items[0].indexOf("=")+1);  
			String position = items[1].substring(items[1].indexOf("=")+1);  
			String responseFilePath=items[2].substring(items[2].indexOf("=")+1);
			if(souceid==null){//代表原来没有上传过此文件，往数据库添加一条绑定记录  
				logService.save(responseid, uploadFile);  
			}  
			RandomAccessFile fileOutStream = new RandomAccessFile(uploadFile, "r");  
			fileOutStream.seek(Integer.valueOf(position));  
			byte[] buffer = new byte[1024*1024];  
			int len = -1;  
			int length = Integer.valueOf(position);  



			while(start&&(len = fileOutStream.read(buffer)) != -1){  
				outStream.write(buffer, 0, len);  
				length += len;  
				Message msg = new Message();  
				msg.getData().putInt("size", length); 
				msg.what=2;
				handUploadImage.sendMessage(msg);  
			}  

			fileOutStream.close();
			outStream.close(); 




			Message msg = new Message();  
			msg.what=1;
			Map<String,String>map=new HashMap<String, String>();
			map.put("filePath", responseFilePath);//新地址
			map.put("fileKey",fileKey);//旧地址
			msg.obj=map;

			handUploadImage.sendMessage(msg);

			inStream.close();  
			socket.close();  
			if(length==uploadFile.length())
				logService.delete(uploadFile);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}  
		return true;

	}
	private boolean checkFild(){
		for(int i=0;i<productMainData.size();i++){
			if(!((String)productMainData.get(i).get("isNull")).equals("1")){
				//只要不是允许为NULL的
				if(((String)productMainData.get(i).get("default")).equals("")){
					return false;
				}
			}



		}
		return true;
	}
	private void setUpSaveMainTread(){
		pd.show();
		pd.setMessage("正在保存...");
		pd.setCancelable(false);
		uploadbar.show();
		if(!checkFild()){
			Toast.makeText(this, "您需要填写必要字段", Toast.LENGTH_LONG).show();
			uploadbar.dismiss();
			pd.dismiss();
			return ;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub


				Message msg=handSaveMain.obtainMessage();

				Set<String>images=AppConfig.getLocalBitmapInstance().keySet();
				Log.i("upload","待上传个数"+images.size());	
				boolean isok=false;
				if(getImageCount()!=0){


					for(String file:images){

						File f=new File(file);
						Log.i("upload",f.getPath());	
						if(f.exists()==false){
							Log.i("upload",f.getPath()+"文件不存在");	
						}

						if(saveImage(file,f)!=true){

							//msg.arg1=0;
							//msg.sendToTarget();
							Log.i("upload","文件上传失败");	
							isok=false;
						}
						isok=true;

					}

				}else{
					msg.arg1=3;//取消掉进读条
					msg.sendToTarget();
					msg=handSaveMain.obtainMessage();
					isok=true;
				}
				if(isok==true){
					for(int i=0;i<productMainData.size();i++){

						Map<String,Object>mp=productMainData.get(i);
						String name=(String) mp.get("name");
						String type=(String)mp.get("type");
						String optional=(String)mp.get("optional");
						String defaultS=(String)mp.get("default");
						String tName=(String)mp.get("tName");
						String isNull=(String)mp.get("isNull");
						String newDeaultS;
						if(name.equals("图片"))
						{
							newDeaultS=AppConfig.getLocalNetInstance().get(defaultS);


							Map<String, Object> mmp=new HashMap<String, Object>();
							mmp.put("name", name);
							mmp.put("type", type);
							mmp.put("optional", optional);
							mmp.put("default", newDeaultS);
							mmp.put("tName",tName);
							mmp.put("isNull",isNull);

							productMainData.set(i, mmp);



						}
					}
				}


				Log.i("upload","开始保存文字");	
				if(isok&&saveMain(mod)){
					msg.arg1=1;

				}else{
					msg.arg1=0;
				}
				msg.sendToTarget();
			}
		}){}.start();
	}
	public static Handler handModyfi=new Handler(){
		public void handleMessage(Message msg) {
			//代理处理修改		
			Map<String, Object> mmp=(Map<String, Object>) msg.obj;	 
			productMainData.set(msg.what, mmp);
			if(mmp.get("name").equals("管理单位"))
			{
				//如果是管理单位
				//设置使用单位级联
				//获取使用单位的postion
				//1.求出-前面的数字
				//2.设置他的表名
				int shiyongdanweiPos=getPostion("使用单位");
				String preStr=mmp.get("default").toString().split("-")[0].trim();

				//(select * from Dict_使用单位 where 代码 like '%%') a
				//String sql="(select * from Dict_使用单位  where 代码 like '%"+preStr+"%' and len(代码)>3) a";

				Map<String,Object>sydwmap=productMainData.get(shiyongdanweiPos);
				String oldSql=sydwmap.get("tName").toString();
				sydwnewSql=oldSql.replace("@key", "'"+preStr+"%'");


				sydwmap.remove("default");
				sydwmap.put("default", "");

				productMainData.set(shiyongdanweiPos, sydwmap);


			}
			addProductMainAdapter.notifyDataSetChanged();	 
			lvMain.setSelection(msg.what);

		};
	};

	private boolean saveMain(int mod){
		/**
		 *将Model封装成json 
		 *
		 */
		//NewProudctMainModel main=new NewProudctMainModel( productMainData.get(3).get("keyValue"), productMainData.get(2).get("keyValue"), productMainData.get(1).get("keyValue"), productMainData.get(0).get("keyValue"));
		JSONArray jsonArray=new JSONArray();

		//type=int, optional=, default=80055522, name=设备编号
		String proKeys[]={"type","optional","default","name","tName","isNull"};
		for(int i=0;i<productMainData.size();i++){
			JSONObject json=new JSONObject();
			try {
				int j=0;
				while(j++<proKeys.length){
					json.put(proKeys[j%proKeys.length],productMainData.get(i).get(proKeys[i%proKeys.length])==null?" ":productMainData.get(i).get(proKeys[j%proKeys.length]));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将获取到的数据一个个的保存在jsonArray里
			jsonArray.put(json);

		}
		JSONArray mmjsa=new JSONArray();
		JSONObject mmjso=new JSONObject();
		try {
			mmjso.put("userID", AppConfig.LoginUser.getUserid());
			mmjso.put("pTypeID", pTypeID);
			mmjso.put("id",productId);
			mmjso.put("attribute", jsonArray.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mmjsa.put(mmjso);
		JsonResultFactory fac=new TrueOrFalseJsonResultFactory();
		if(mod==0){
			//新建
			fac.setJsonStr( QufeiSocketClient.getInstance().send("InsertNewProductMain",mmjsa));
		}else
		{
			fac.setJsonStr( QufeiSocketClient.getInstance().send("UpdateProductMain",mmjsa));

		}
		JsonResult res=fac.createResult();
		if(res==null)
		{
			return false;
		}
		TrueOrFalseModel tmodel=(TrueOrFalseModel) res.getModels().get(0);
		if(tmodel==null){
			return false;
		}
		boolean b= tmodel.getIsOk().equals("true")?true:false;
		return b;

	}

}
