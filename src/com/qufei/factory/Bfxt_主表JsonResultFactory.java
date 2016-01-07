package com.qufei.factory;

import com.qufei.mapper.Bfxt_����RowMapper;

import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Bfxt_����JsonResultFactory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Bfxt_����RowMapper());
		return loginres;
	}


}
