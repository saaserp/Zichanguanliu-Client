package com.qufei.factory;

import com.qufei.mapper.AddedProductRowMapper;
import com.qufei.mapper.MyProductRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;

public class AddedProductJSONResultFactory extends JsonResultFactory{

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
				JsonResult j=new JsonResult();
				String str=super.getJsonStr();
				if(str=="")
					return null;
				j.setJsonStr(str);
				j.parse(new AddedProductRowMapper());
				return j;
	}

}
