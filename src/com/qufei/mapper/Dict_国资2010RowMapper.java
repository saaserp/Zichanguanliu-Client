package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dict_国资2010Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dict_国资2010RowMapper implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Dict_国资2010Model m = null;
		try {
			m = new Dict_国资2010Model(jsobj.getString("代码"), jsobj.getString("名称"));
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
		Dict_国资2010Model mdd=(Dict_国资2010Model) md;
		m.put("代码", mdd.get代码());
		m.put("名称",mdd.get名称());		
		return m;
	}

	

}
