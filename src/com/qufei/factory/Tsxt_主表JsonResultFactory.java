package com.qufei.factory;

import com.qufei.mapper.Tdxt_����RowMapper;
import com.qufei.mapper.Tsxt_����RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Tsxt_����JsonResultFactory  extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Tsxt_����RowMapper());
		return loginres;
	}

	

}
