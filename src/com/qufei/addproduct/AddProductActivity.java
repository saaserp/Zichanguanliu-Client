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
	public static int tail=0;//¼���ʱ��ȷ�����Ψһ
	public static boolean changed=false;
	static ListView lvMain;
	public static ListView lvSub;
	private static String pTypeID;
	private static int mod;//0��ʾ�½�;1��ʾ����
	private static String productId; //��Ʒ��id
	public static final int REQUESTCODE_ZERO = -1;//����ͼƬ
	public static final int REQUESTCODE_TWO = 5;//��ĳһ������ͼƬ
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
		//��ȡ��ǰͼƬ�ĸ���
		if(getImageCount()>=maxPicCount){
			//��ǰ�ĸ��������˱��ĸ���
			Toast.makeText(AddProductActivity.this, "ͼƬ���ܳ���"+maxPicCount+"��", Toast.LENGTH_LONG).show();
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
			if(m.get("name").equals("ͼƬ")){
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
		if(requestCode==0){//����
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
					Toast.makeText(AddProductActivity.this, "�Ҳ�������", Toast.LENGTH_SHORT).show();
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
				//����漰�����,���Զ��ۼ����
				if(isJine.equals("true")){
					//��ȡ���۵�λ��
					//��ȡ������λ��
					//��ȡ�ܽ���λ��
					int danjiaPos=getPostion("����");
					int shuliangPos=getPostion("����");
					int zongjinePos=getPostion("�ܽ��");
					String danjia;
					String shuliang;
					String zongjine="0";
					if(danjiaPos!=0&&shuliangPos!=0&&zongjinePos!=0){
						//�����۴��ڲ����������ڲ����ܽ�����
						danjia=(String)productMainData.get(danjiaPos).get("default");
						shuliang=(String)productMainData.get(shuliangPos).get("default");
						//�����ֵ�Ƿ�Ϸ�
						double dj;
						int sl;
						if(shuliang.length()>8)
						{
							Toast.makeText(AddProductActivity.this, "������������,����������!",Toast.LENGTH_SHORT).show();

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
		else if(requestCode==1){//���÷�����Լ�����
			if(resultCode==Activity.RESULT_OK){
				String fenleihao=data0.getStringExtra("fenleihao");
				String shebeimingcheng=data0.getStringExtra("shebeimingcheng");
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("name","�����");
				map.put("default", fenleihao);
				map.put("type", "String");
				map.put("optional", "2");//����ű༭
				map.put("tName", "");
				map.put("isNull", "0");//����
				int m=getPostion("�����");
				productMainData.set(m, map);
				Map<String,Object>map2=new HashMap<String, Object>();				
				map2.put("name",productType.replace("���", "����"));
				map2.put("default", shebeimingcheng);
				map2.put("type", "String");
				map2.put("optional", "0");//��ͨ�ı༭
				map2.put("tName", "");
				map2.put("isNull", "0");//����
				int n=getPostion(productType.replace("���", "����"));
				productMainData.set(n, map2);//�����3��ʱ��ĳɶ�̬��
				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
				lvMain.setAdapter(addProductMainAdapter);
				lvMain.setSelection(n);
			}else{
				Toast.makeText(this, "δ�Ķ�", Toast.LENGTH_SHORT).show();
			}

		} else if(requestCode==4){//���ֹ��ʷ���ż�����
			if(resultCode==Activity.RESULT_OK){
				String fenleihao=data0.getStringExtra("fenleihao");
				String shebeimingcheng=data0.getStringExtra("shebeimingcheng");
				Map<String,Object>map=new HashMap<String, Object>();
				map.put("name","���ʷ����");
				map.put("default", fenleihao);
				map.put("type", "String");
				map.put("tName", "");
				map.put("isNull", "0");//����
				int n=getPostion("���ʷ����");
				map.put("optional", "4");//���ʷ���ű༭
				productMainData.set(n, map);
				Map<String,Object>map2=new HashMap<String, Object>();
				map2.put("name","���ʴ���");
				map2.put("default", shebeimingcheng);
				map2.put("type", "String");
				map2.put("optional", "0");//��ͨ�ı༭
				map2.put("tName", "");
				map2.put("isNull", "0");//����
				productMainData.set(getPostion("���ʴ���"), map2);//�����3��ʱ��ĳɶ�̬��
				addProductMainAdapter=new AddProductMainAdapter(AddProductActivity.this, productMainData,extInfo);
				lvMain.setAdapter(addProductMainAdapter);
				lvMain.setSelection(n);

			}else{
				Toast.makeText(this, "δ�Ķ�", Toast.LENGTH_SHORT).show();
			}

		}else			
			if(requestCode==REQUESTCODE_ONE){
				if(resultCode==Activity.RESULT_OK){

					Uri selectedImage = data0.getData();
					if(selectedImage==null){
						Bundle bundle = data0.getExtras();  
						Bitmap  bitmap1 = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ  
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
					map.put("name", "ͼƬ");
					map.put("default", imageFilePath);	
					map.put("type","String");
					map.put("optional","3");//3��ʾͼƬ����
					map.put("tName", "");
					map.put("isNull", "1");//ͼƬ����Ϊ��
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
			else if(requestCode==REQUESTCODE_TWO){//��ʾ������������

				if(resultCode==Activity.RESULT_OK){
					int pos=data0.getIntExtra("pos", postion);
					Uri selectedImage = data0.getData();
					if(selectedImage==null){
						Bundle bundle = data0.getExtras();  
						Bitmap  bitmap1 = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ  
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
					map.put("name", "ͼƬ");
					map.put("default", imageFilePath);	
					map.put("type","String");
					map.put("optional","3");//3��ʾͼƬ����
					map.put("tName", "");
					map.put("isNull", "1");//ͼƬ����Ϊ��
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
				btnSave.setText("����");
				btnSave.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						setUpSaveMainTread();
					}
				});

			}else{
				Toast.makeText(AddProductActivity.this, "����δ����", Toast.LENGTH_SHORT).show();
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
		//��listView��ʾ���
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

		//����Ĳ���
		pTypeID=getIntent().getStringExtra("pTypeID");
		if(pTypeID==null){
			Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();
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
			Toast.makeText(this, "�����ڲ�����", Toast.LENGTH_LONG).show();
		}


		productSubData=new ArrayList<Map<String,String>>();
		productMainData=new ArrayList<Map<String,Object>>();
		TextView tv_title=(TextView) this.findViewById(R.id.title_text);

		tv_title.setText(title);
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(changed)//ֻ���ڰ��˱��水ť���ˢ��
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
			tv_title.setText(title+"�½��ʲ�");
		else if(mod==1){
			tv_title.setText("�޸��ʲ�");
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
		pd.setMessage("���ڼ���...");
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



		Log.i("attribute","ҳ���"+attribute);
		JSONArray atJsonA=null;
		try {
			atJsonA=new JSONArray(attribute);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for(int i=0;i<atJsonA.length();i++){
			//��ȡÿһ��
			try {

				JSONObject attrJson=atJsonA.getJSONObject(i);
				Map<String,Object>mp=new HashMap<String, Object>();
				if(mod==0){//����������ӣ����Ʒ���Ҫ�仯
					if(attrJson.getString("name").contains("���")==true){
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
				//��ȡ���ͼƬ����
				if(attrJson.getString("name").equals("ͼ����")==true){
					maxPicCount=Integer.parseInt( attrJson.getString("default"));

				}
				mp.put("type", ptype);
				mp.put("optional", attrJson.getString("optional"));
				if(attrJson.getString("name").contains("ͼƬ")){
					//�����ͼƬ��������ͼƬ������
					String httpPath=attrJson.getString("default");

					Bitmap bitmap;
					String cashName=AppConfig.toCashName(httpPath);
					if(!AppConfig.getNetLocalInstance().containsKey(httpPath))//����ڱ��汾�ؼ�¼����û��
					{
						//�����ļ�
						bitmap=MediaHelper.getHttpBitmap("http://"+AppConfig.getInstance().getIp()+"/"+httpPath);
						if(bitmap!=null){
							//���سɹ�
							String fileName=MediaHelper.saveBitmap(cashName, bitmap);
							if(null==fileName)
							{
								//ͼƬ����ʧ��
								Log.i("cashName","���ر���ʧ��");
							}
							else{
								//ͼƬ����ɹ�

								AppConfig.getNetLocalInstance().put(httpPath, fileName);
								if(!AppConfig.getLocalBitmapInstance().containsKey(fileName))
									AppConfig.getLocalBitmapInstance().put(fileName,bitmap);
								mp.put("default",fileName);//�����ļ���Ϊdata���ֵ
							}
						}else{

							Log.i("imagedownloadfaild","ͼƬ����ʧ��");
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
		if( baos.toByteArray().length / 1024>1024) {//�ж����ͼƬ����1M,����ѹ������������ͼƬ��BitmapFactory.decodeStream��ʱ���    
			baos.reset();//����baos�����baos
			image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//����ѹ��50%����ѹ��������ݴ�ŵ�baos��
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
		float hh = 800f;//�������ø߶�Ϊ800f
		float ww = 480f;//�������ÿ��Ϊ480f
		//���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��
		int be = 1;//be=1��ʾ������
		if (w > h && w > ww) {//�����ȴ�Ļ����ݿ�ȹ̶���С����
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//����߶ȸߵĻ����ݿ�ȹ̶���С����
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//�������ű���
		newOpts.inPreferredConfig = Config.RGB_565;//����ͼƬ��ARGB888��RGB565
		//���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);//ѹ���ñ�����С���ٽ�������ѹ��
	}
	private Bitmap compressImage(Bitmap image) {  

		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��  
		int options = 100;  
		while ( baos.toByteArray().length / 1024>100) {  //ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��         
			baos.reset();//����baos�����baos  
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//����ѹ��options%����ѹ��������ݴ�ŵ�baos��  
			options -= 10;//ÿ�ζ�����10  
		}  
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//��ѹ���������baos��ŵ�ByteArrayInputStream��  
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//��ByteArrayInputStream��������ͼƬ  
		return bitmap;  
	}  



	Handler handSaveMain=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.arg1==1){
				//��������
				//setUpsaveSub();
				changed=true;
				Toast.makeText(AddProductActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
				if(mod==0)//������½�ģʽ
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
				Toast.makeText(AddProductActivity.this, "����ɹ�", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(AddProductActivity.this, "����ʧ��", Toast.LENGTH_LONG).show();
			}
			pd.dismiss();
		};
	};
	Handler handUploadImage=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==1){
				@SuppressWarnings("unchecked")
				Map<String,String>map=(Map<String, String>) msg.obj;
				String fileServerPath=map.get("filePath");//��������·��
				String filePath=map.get("fileKey");//���ص�ַ
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
					//Toast.makeText(AddProductActivity.this, "�ɹ�", 1).show();
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
			if(souceid==null){//����ԭ��û���ϴ������ļ��������ݿ����һ���󶨼�¼  
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
			map.put("filePath", responseFilePath);//�µ�ַ
			map.put("fileKey",fileKey);//�ɵ�ַ
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
				//ֻҪ��������ΪNULL��
				if(((String)productMainData.get(i).get("default")).equals("")){
					return false;
				}
			}



		}
		return true;
	}
	private void setUpSaveMainTread(){
		pd.show();
		pd.setMessage("���ڱ���...");
		pd.setCancelable(false);
		uploadbar.show();
		if(!checkFild()){
			Toast.makeText(this, "����Ҫ��д��Ҫ�ֶ�", Toast.LENGTH_LONG).show();
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
				Log.i("upload","���ϴ�����"+images.size());	
				boolean isok=false;
				if(getImageCount()!=0){


					for(String file:images){

						File f=new File(file);
						Log.i("upload",f.getPath());	
						if(f.exists()==false){
							Log.i("upload",f.getPath()+"�ļ�������");	
						}

						if(saveImage(file,f)!=true){

							//msg.arg1=0;
							//msg.sendToTarget();
							Log.i("upload","�ļ��ϴ�ʧ��");	
							isok=false;
						}
						isok=true;

					}

				}else{
					msg.arg1=3;//ȡ����������
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
						if(name.equals("ͼƬ"))
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


				Log.i("upload","��ʼ��������");	
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
			//�������޸�		
			Map<String, Object> mmp=(Map<String, Object>) msg.obj;	 
			productMainData.set(msg.what, mmp);
			if(mmp.get("name").equals("����λ"))
			{
				//����ǹ���λ
				//����ʹ�õ�λ����
				//��ȡʹ�õ�λ��postion
				//1.���-ǰ�������
				//2.�������ı���
				int shiyongdanweiPos=getPostion("ʹ�õ�λ");
				String preStr=mmp.get("default").toString().split("-")[0].trim();

				//(select * from Dict_ʹ�õ�λ where ���� like '%%') a
				//String sql="(select * from Dict_ʹ�õ�λ  where ���� like '%"+preStr+"%' and len(����)>3) a";

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
		 *��Model��װ��json 
		 *
		 */
		//NewProudctMainModel main=new NewProudctMainModel( productMainData.get(3).get("keyValue"), productMainData.get(2).get("keyValue"), productMainData.get(1).get("keyValue"), productMainData.get(0).get("keyValue"));
		JSONArray jsonArray=new JSONArray();

		//type=int, optional=, default=80055522, name=�豸���
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
			//����ȡ��������һ�����ı�����jsonArray��
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
			//�½�
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
