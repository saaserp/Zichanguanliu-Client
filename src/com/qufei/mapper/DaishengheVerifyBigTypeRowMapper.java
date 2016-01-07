package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.DaishengheVerifyBigTypeModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class DaishengheVerifyBigTypeRowMapper  implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		DaishengheVerifyBigTypeModel md = null;
		try {
			md = new DaishengheVerifyBigTypeModel(jsobj.getString("typeName"), jsobj.getString("number"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<String, String>();
		DaishengheVerifyBigTypeModel m=(DaishengheVerifyBigTypeModel)md;
		map.put("typeName", m.getTypeName());
		map.put("number",m.getNumber());
		return map;
	}

	 
}
