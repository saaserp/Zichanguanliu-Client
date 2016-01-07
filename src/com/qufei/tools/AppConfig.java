package com.qufei.tools;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.os.Environment;

import com.qufei.model.UserModel;

public class AppConfig {
	//public static String hostIP = "www.qufeisoft.com";
	//public static String hostIP="172.16.15.174";
	//public static String hostIP = "192.168.191.1";
	//public static String hostName = "http://" + hostIP + ":80";
	public static AppConfig app=new AppConfig();
	private String httpPort;
	public String getHttpPort() {
		return httpPort;
	}
	public void setHttpPort(String httpPort) {
		this.httpPort = httpPort;
	}
	private String socketPort;
 	public String getSocketPort() {
		return socketPort;
	}
	public void setSocketPort(String port) {
		this.socketPort = port;
	}
	
	public static AppConfig getInstance(){
		return app;
	}
	 public String ip;
	 public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHttpAddress() {
		return httpAddress;
	}
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}
	public String httpAddress;
	public static String images = "/ais/";
	public static int SERVERERROR = 0;
	public static String SERVERERRORSTR = "������δ����";
	public static int NODATAREQUEST = -1;
	public static String NODATAREQUESTSTR = "û���ҵ��������";
	public static int FUNCTIONVIEWDATA = 2;// ��Ӳ���ƾ���ź�����
	public static int FUNCTIONADDDATA = 2;// ��Ӳ���ƾ���ź�����
	public static int FUNCTIONCODE = 0;// ����Ǽ�¼��ǰ�Ľ���Ĺ���ģ��
	public static int TIMEOUT=60000;//�׽��ֳ�ʱʱ��60��
	public static UserModel LoginUser;
	public static Map<String,String>netLocalMap=new HashMap<String, String>();
	public static Map<String,Bitmap>localBitmapMap=new HashMap<String, Bitmap>();
	public static Map<String,String>localNetMap=new HashMap<String, String>();
	public static String CashPath=Environment.getExternalStorageDirectory().getPath()+"/qufeiTemp/";
	 
	public static Map<String,String> getNetLocalInstance(){
		return netLocalMap;
	}
	public static Map<String,Bitmap>getLocalBitmapInstance(){
		return localBitmapMap;
	}
	public static Map<String,String>getLocalNetInstance(){
		return localNetMap;
	}
	public static String toCashName(String imgPath){
		 String cashNameTemp=imgPath.replace(".", "_").replace("/", "_").replace(":", "_");
		  String cashName=cashNameTemp.substring(0,cashNameTemp.lastIndexOf("_"))+"."+cashNameTemp.substring(cashNameTemp.lastIndexOf("_")+1);
		  return cashName;
	}
	public static int getAndroidSDKVersion() {
		   int version = 0;
		   try {
		     version = Integer.valueOf(android.os.Build.VERSION.SDK);
		   } catch (NumberFormatException e) {
		    
		   }
		   return version;
		}

}
