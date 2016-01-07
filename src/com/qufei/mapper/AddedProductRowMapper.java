package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.AddedProductModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class AddedProductRowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
	 
		AddedProductModel m = null;
		try {
			m = new AddedProductModel(jsobj.getString("id"), jsobj.getString("attribute"), jsobj.getString("writeTime"), jsobj.getString("typeName"),jsobj.getString("pTypeID"));
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
		AddedProductModel m=(AddedProductModel) md;
		map.put("id", m.getId());
		map.put("attribute",m.getAttribute());
		map.put("writeTime", m.getWritTime());
		map.put("typeName", m.getTypeName());
		map.put("pTypeID", m.getpTypeID());
		return map;
	}

}
