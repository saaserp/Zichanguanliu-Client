package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dict_固定资产Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dict_固定资产RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Dict_固定资产Model m = null;
		try {
			m = new  Dict_固定资产Model(
					jsobj.getString("代码"),
					jsobj.getString("名称"),
					jsobj.getString("层级码")
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
		Dict_固定资产Model m=(Dict_固定资产Model)md;
		map.put("代码", m.get代码());
		map.put("名称",m.get名称());
		map.put("层级码",m.get层级码());
		return map;
	}

	 

}
