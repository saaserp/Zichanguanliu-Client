package com.qufei.factory;

import com.qufei.mapper.ProductRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class SelectTypeResultFacory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new ProductRowMapper());
		return loginres;
	}

}
