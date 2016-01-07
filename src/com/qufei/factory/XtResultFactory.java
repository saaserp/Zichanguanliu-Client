package com.qufei.factory;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.mapper.XtRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class XtResultFactory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new XtRowMapper());
		return loginres;
	}
	public List<LinkedHashMap<String,String>> getMapList(){
		String jsonStr=super.getJsonStr();
		if(jsonStr==""|| jsonStr==null)
			return null;
		JSONArray ja;
		try {
			ja = new JSONArray(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
		List<LinkedHashMap<String,String>>list=new ArrayList<LinkedHashMap<String,String>>();
		for(int i=0;i<ja.length();i++){
			try {
				JSONObject j=(JSONObject)ja.get(i);
				LinkedHashMap<String,String>mp=new LinkedHashMap<String, String>();
				Iterator keys = j.keys();
				while(keys.hasNext())
				{
					String key=keys.next().toString();
					mp.put(key,j.getString(key));


				}
				list.add(mp);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		return list;

	}

}
