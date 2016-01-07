package com.qufei.factory;

import com.qufei.mapper.BigClassRowMapper;
import com.qufei.mapper.TypeRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class TypeJsonResultFactory  extends JsonResultFactory{

	
	@Override
	public JsonResult createResult() {
		JsonResult json=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		json.setJsonStr(str);
		json.parse(new TypeRowMapper());
		return json;
	}

}
