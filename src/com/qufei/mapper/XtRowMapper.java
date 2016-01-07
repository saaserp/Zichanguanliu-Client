package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

import com.qufei.model.XtModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class XtRowMapper implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject j ) {
		// TODO Auto-generated method stub
		XtModel m = null;
		try {
			m = new XtModel(j.getString("key"),j.getString("value"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		XtModel m=(XtModel)md;
		Map<String,String>map=new HashMap<String, String>();
		map.put("key", m.getKey());
		map.put("value",m.getValue());
		return map;
	}

}
