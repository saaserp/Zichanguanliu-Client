package com.qufei.factory;

import com.qufei.mapper.Bfxt_����RowMapper;
import com.qufei.mapper.Jjxt_����RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Jjxt_����JsonResultFactory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Jjxt_����RowMapper());
		return loginres;
	}


}
