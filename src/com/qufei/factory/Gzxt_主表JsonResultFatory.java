package com.qufei.factory;

import com.qufei.mapper.Gzxt_����RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Gzxt_����JsonResultFatory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Gzxt_����RowMapper());
		return loginres;
	}

	

}
