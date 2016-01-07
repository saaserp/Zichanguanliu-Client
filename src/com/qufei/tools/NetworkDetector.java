package com.qufei.tools;

import java.io.IOException;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkDetector {
	private static final int REQUEST_TIMEOUT = 5*1000;
	private static final int SO_TIMEOUT = 10*1000; 
	public static boolean detect(Activity act) {  

		ConnectivityManager manager = (ConnectivityManager) act  
				.getApplicationContext().getSystemService(  
						Context.CONNECTIVITY_SERVICE);  

		if (manager == null) {  
			return false;  
		}  

		NetworkInfo networkinfo = manager.getActiveNetworkInfo();  

		if (networkinfo == null || !networkinfo.isAvailable()) {  
			return false;  
		}  

		return true;  
	}
	public static boolean isNetworkAvailable(Context con)
	{
		ConnectivityManager cm = (ConnectivityManager)con.getSystemService(Context.CONNECTIVITY_SERVICE);
		if( cm == null )
			return false;
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo == null )
		{
			return false;
		}
		if(netinfo.isConnected())
		{
			return true;
		}
		return false;
	}
	public static void setNetworkMethod(final Context context){
		//��ʾ�Ի���
		AlertDialog.Builder builder=new Builder(context);
		builder.setTitle("�������").setMessage("���ɿͻ�����Ҫ�õ����������������,�Ƿ��������?").setPositiveButton("����", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent=null;
				//�ж��ֻ�ϵͳ�İ汾  ��API����10 ����3.0�����ϰ汾 
				if(android.os.Build.VERSION.SDK_INT>10){
					intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				}else{
					intent = new Intent();
					ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				context.startActivity(intent);
			}
		}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//((Activity) context).finish();
				dialog.dismiss();
			}
		}).show();
	}
	public static HttpClient getHttpClient()
    {
    	BasicHttpParams httpParams = new BasicHttpParams();
    	HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
    	HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
    	HttpClient client = new DefaultHttpClient(httpParams);
    	return client;
    }
	public static Drawable loadImageFromNetwork(String imageUrl)
	{
	 Drawable drawable = null;
	 try {
	  // ����������ͨ���ļ������жϣ��Ƿ񱾵��д�ͼƬ
	  drawable = Drawable.createFromStream(
	    new URL(imageUrl).openStream(), "image.jpg");
	 } catch (IOException e) {
	  Log.d("test", e.getMessage());
	 }
	 if (drawable == null) {
	  Log.d("test", "null drawable");
	 } else {
	  Log.d("test", "not null drawable");
	 }
	 
	 return drawable ;
	}
}
