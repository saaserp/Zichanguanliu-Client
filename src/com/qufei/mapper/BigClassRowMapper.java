package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.BigClassModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class BigClassRowMapper implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		BigClassModel bm = null;
		try {
			bm = new BigClassModel(jsobj.getString("className"), jsobj.getString("ct"), jsobj.getString("type"), jsobj.getString("je"));
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bm;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		BigClassModel bmd = (BigClassModel) md;
		map.put("className", bmd.getClassName());
		map.put("type", bmd.getType());
		map.put("ct", bmd.getCt());
		map.put("je", bmd.getJe());
		return null;
	}
}
