package com.qufei.factory;

import com.qufei.mapper.Gzxt_主表RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Gzxt_主表JsonResultFatory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Gzxt_主表RowMapper());
		return loginres;
	}

	

}
