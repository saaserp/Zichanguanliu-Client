package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.Dict_�̶��ʲ�Model;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class Dict_�̶��ʲ�RowMapper implements IRowMapper{

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		Dict_�̶��ʲ�Model m = null;
		try {
			m = new  Dict_�̶��ʲ�Model(
					jsobj.getString("����"),
					jsobj.getString("����"),
					jsobj.getString("�㼶��")
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
		Dict_�̶��ʲ�Model m=(Dict_�̶��ʲ�Model)md;
		map.put("����", m.get����());
		map.put("����",m.get����());
		map.put("�㼶��",m.get�㼶��());
		return map;
	}

	 

}
