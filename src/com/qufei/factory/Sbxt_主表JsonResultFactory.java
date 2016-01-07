package com.qufei.factory;

import java.util.List;

import com.qufei.mapper.Sbxt_����RowMapper;
import com.qufei.mapper.UserRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;

public class Sbxt_����JsonResultFactory extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		JsonResult loginres=new JsonResult();
		String str=super.getJsonStr();
		if(str=="")
			return null;
		loginres.setJsonStr(str);
		loginres.parse(new Sbxt_����RowMapper());
		return loginres;
	}

}
