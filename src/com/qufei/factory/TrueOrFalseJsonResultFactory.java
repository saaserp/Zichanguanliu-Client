package com.qufei.factory;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.mapper.SmallClassRowMapper;
import com.qufei.model.TrueOrFalseModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;

public class TrueOrFalseJsonResultFactory  extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult json=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		json.setJsonStr(str);
		json.parse(new IRowMapper() {
			
			@Override
			public Map<String, String> mappingRow(Model md) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Model mappingRow(JSONObject jsobj) {
				// TODO Auto-generated method stub
				TrueOrFalseModel tm=null;
				try {
					tm=new TrueOrFalseModel(jsobj.getString("isOk"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return tm;
			}
		});
		return json;
	}

	
}
