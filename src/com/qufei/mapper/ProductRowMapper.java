package com.qufei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.ProductModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class ProductRowMapper  implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject js) {
		// TODO Auto-generated method stub
		
		//String pTypeID, String attribute, String typeName, String father
		ProductModel p = null;
		try {
			p = new ProductModel(js.getString("pTypeID"), js.getString("attribute"), js.getString("typeName"), js.getString("father"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		Map<String,String> json=new HashMap<String, String>();
		ProductModel mdd = (ProductModel)md;
		try {
			//rs.getString("pTypeID"),rs.getString("attribute"), rs.getString("typeName"), rs.getString("father"));
			json.put("pTypeID", mdd.getpTypeID());
			json.put("attribute",mdd.getAttribute());
			json.put("typeName",mdd.getTypeName());
			json.put("father",mdd.getFather());
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	

}
