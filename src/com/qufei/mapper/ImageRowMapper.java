package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.ImageModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class ImageRowMapper  implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject jsobj) {
		// TODO Auto-generated method stub
		ImageModel m = null;
		try {
			m=new ImageModel(
					jsobj.getString("images")
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
		map.put("images", ((ImageModel)md).getImages());
		return map;
	}

	
}
