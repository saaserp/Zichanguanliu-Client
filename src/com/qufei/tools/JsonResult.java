package com.qufei.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public  class JsonResult {
	protected String jsonStr="";
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	private List<Model> models;
	
	public List<Model> getList() {
		return getModels();
	}
	@SuppressWarnings("unused")
	private void setList(List<Model> list) {
		this.setModels(list);
	}
	
	/**
	 * 
	 * @param jsonStr JSon×Ö·û´®
	 * @return ½á¹û
	 */
	public List<Model> parse(IRowMapper rowmapper) {
		
		List<Model>list=new ArrayList<Model>();
		this.setModels(list);
		JSONArray jsonArr;
		
		try {
			jsonArr=new JSONArray(this.getJsonStr());
			Log.i("lwc_","this.getJsonStr()   Ö´ÐÐ£ºfor(int i=0;i<jsonArr.length();i++)");
			for(int i=0;i<jsonArr.length();i++){
				Log.i("lwc_","Map<String,String>map=new HashMap<String,String>();");
				@SuppressWarnings("unused")
				Map<String,String>map=new HashMap<String,String>();
				JSONObject jsonObj=jsonArr.getJSONObject(i);
				
				Model md=rowmapper.mappingRow(jsonObj);
				if(md!=null)
				list.add(md);				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setModels(list);
		return list;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
}
