package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dict_����2010Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dict_����2010RowMapper implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Dict_����2010Model m = null;
		try {
			m = new Dict_����2010Model(jsobj.getString("����"), jsobj.getString("����"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>m=new HashMap<String, String>();
		Dict_����2010Model mdd=(Dict_����2010Model) md;
		m.put("����", mdd.get����());
		m.put("����",mdd.get����());		
		return m;
	}

	

}
