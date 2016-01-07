package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;

import com.qufei.model.StatisticalModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class StatisticalRowMapper implements IRowMapper{


	public Model mappingRow(ResultSet rs) {
		// TODO Auto-generated method stub
		StatisticalModel s = null;
		try {
		s=new StatisticalModel(
					rs.getString("pName"),
					rs.getString("pSum")
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

//	@Override
//	public JSONObject mappingRow(Model md) {
//		// TODO Auto-generated method stub
//		StatisticalModel s=(StatisticalModel )md;
//		JSONObject json=new JSONObject();
//		try {
//			json.put("pName",s.getpName() );
//			json.put("pSum", s.getpSum());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return json;
//	}

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		StatisticalModel m=null;
		try {
			m=new StatisticalModel(jsobj.getString("pName"), jsobj.getString("pSum"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		StatisticalModel mdd=(StatisticalModel)md;
		Map<String,String>m=new HashMap<String, String>();
		m.put("pName", mdd.getpName());
		m.put("pSum", mdd.getpSum());
		return m;
	}

}
