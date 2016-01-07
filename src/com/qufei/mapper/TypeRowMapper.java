package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.TypeModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class TypeRowMapper implements IRowMapper{

	

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		TypeModel m = null;
		try {
			m = new TypeModel(
					jsobj.getString("prefix"),
					jsobj.getString("name"),
					jsobj.getString("typeNo")
					);
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
		TypeModel m=(TypeModel)md;
		map.put("prefix", m.getPrefix());
		map.put("name", m.getName());
		map.put("typeNo", m.getTypeNo());
		return map;
	}

}
