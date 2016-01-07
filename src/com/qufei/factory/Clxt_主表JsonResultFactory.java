package com.qufei.factory;

import com.qufei.mapper.Clxt_主表RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Clxt_主表JsonResultFactory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Clxt_主表RowMapper());
		return loginres;
		
	}

	
}
