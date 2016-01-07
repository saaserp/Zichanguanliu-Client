package com.qufei.factory;

import com.qufei.mapper.SmallClassRowMapper;
import com.qufei.mapper.StatisticalRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class StatisticalJsonResultFactory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult json=new JsonResult();
		json.setJsonStr(super.getJsonStr());
		json.parse(new StatisticalRowMapper());
		return json;
	}

	

}
