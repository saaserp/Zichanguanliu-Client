package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.MyProductModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class MyProductRowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		MyProductModel m = null;
		try {
			m = new MyProductModel(
					jsobj.getString("name"), jsobj.getString("pTypeID"), jsobj.getString("optional"), jsobj.getString("default"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<String, String>();
		MyProductModel m=(MyProductModel)md;
		map.put("name",m.getName());
		map.put("pTypeID",m.getpTypeID());
		map.put("optional",m.getOptional());
		map.put("default",m.getDefaultValue());
		
		
		return map;
	}

}
