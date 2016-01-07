package com.qufei.factory;

import com.qufei.mapper.Dict_�̶��ʲ�RowMapper;
import com.qufei.mapper.Dict_����2010RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Dict_����2010JsonResultFactory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Dict_����2010RowMapper());
		return loginres;
	}

	 

}
