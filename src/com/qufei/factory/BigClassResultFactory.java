package com.qufei.factory;

import com.qufei.mapper.BigClassRowMapper;
import com.qufei.mapper.Sbxt_Ö÷±íRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class BigClassResultFactory extends JsonResultFactory{


	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult json=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		json.setJsonStr(str);
		json.parse(new BigClassRowMapper());
		return json;
		
	}

}
