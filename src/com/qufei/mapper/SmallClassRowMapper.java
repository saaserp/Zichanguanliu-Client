package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.SmallClassModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class SmallClassRowMapper  implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		SmallClassModel bm=null;
		try {
			bm=new SmallClassModel(jsobj.getString("name"),jsobj.getString("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bm;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<String, String>();
		SmallClassModel sm=(SmallClassModel)md;
		map.put("name",sm.getName());
		map.put("id", sm.getId());
		return null;
	}



}
