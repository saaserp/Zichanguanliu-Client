package com.qufei.factory;

import com.qufei.mapper.Bfxt_����RowMapper;
import com.qufei.mapper.Tdxt_����RowMapper;

import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Tdxt_����JsonResultFactory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Tdxt_����RowMapper());
		return loginres;
	}


}
