package com.qufei.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public abstract class JsonResultFactory {
	private static final int REQUEST_TIMEOUT = 5*1000;
	private static final int SO_TIMEOUT = 10*1000; 
	/**
	 * 
	 */
	private String jsonStr=null;
	public JsonResultFactory setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
		return this;
	}
	
	private String url;
	public JsonResultFactory(){

	}
	public JsonResultFactory(String url){
		this.url=url;
	}
	

	public String getUrl() {
		return url;
	}
	
	public JsonResultFactory setUrl(String url) {
		try {
			this.url =URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.url=url;
		return this;
		
	}
	public abstract JsonResult createResult();
	
	protected  String getJsonStr(){

		// TODO Auto-generated method stub
		
		if(jsonStr!=null){
			return jsonStr;
		}
		return "";
//		HttpPost request = new HttpPost(url);
//		
//		Log.i("lwc_",url);
//		HttpClient client = getHttpClient();
//		try {
//			
//			HttpResponse response = client.execute(request);
//			jsonStr = EntityUtils.toString(response.getEntity(),"utf-8");
//			Log.i("lwc_",jsonStr);
//
//			return jsonStr;
//
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}



	}

	@SuppressWarnings("unused")
	private HttpClient getHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		HttpClient client = new DefaultHttpClient(httpParams);
		return client;

	}
}
