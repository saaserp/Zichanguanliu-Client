package com.qufei.factory;

import com.qufei.mapper.Tdxt_主表RowMapper;
import com.qufei.mapper.Tsxt_主表RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Tsxt_主表JsonResultFactory  extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Tsxt_主表RowMapper());
		return loginres;
	}

	

}
