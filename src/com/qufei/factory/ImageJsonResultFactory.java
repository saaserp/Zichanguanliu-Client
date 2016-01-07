package com.qufei.factory;

import com.qufei.mapper.Clxt_Ö÷±íRowMapper;
import com.qufei.mapper.ImageRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class ImageJsonResultFactory  extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new ImageRowMapper());
		return loginres;
	}

	

}
