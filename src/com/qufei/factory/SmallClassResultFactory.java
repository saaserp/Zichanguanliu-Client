package com.qufei.factory;

import com.qufei.mapper.SmallClassRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class SmallClassResultFactory extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult json=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		json.setJsonStr(str);
		json.parse(new SmallClassRowMapper());
		return json;
	}

	

}
