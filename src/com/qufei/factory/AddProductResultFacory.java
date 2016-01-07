package com.qufei.factory;

import com.qufei.mapper.MyProductRowMapper;
import com.qufei.mapper.NewProductMainMapper;
import com.qufei.mapper.ProductRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class AddProductResultFacory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new MyProductRowMapper());
		return loginres;
	}

	

}
