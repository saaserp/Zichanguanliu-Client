package com.qufei.factory;

import com.qufei.mapper.Clxt_����RowMapper;
import com.qufei.mapper.Dict_�̶��ʲ�RowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class Dict_�̶��ʲ�JsonResultFactory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Dict_�̶��ʲ�RowMapper());
		return loginres;
	}

	 

}
