package com.qufei.factory;

import java.util.List;

import org.json.JSONObject;

import com.qufei.mapper.UserRowMapper;
import com.qufei.tools.JsonResult;
import com.qufei.tools.JsonResultFactory;
import com.qufei.tools.Model;

import android.util.Log;



public class LoginJsonResultFactory extends JsonResultFactory {

	@Override
	public JsonResult createResult() {
		// TODO Auto-generated method stub
		Log.i("lwc_","执行JsonResult loginres=new JsonResult();");
		JsonResult loginres=new JsonResult();
		Log.i("lwc_","执行loginres.setJsonStr(super.getJsonStr());");
		String str=super.getJsonStr();
		
		if(str=="")
			return null;
		//str="[{\"isok\":\"true\",\"userid\":\"0001\",\"username\":\"李伟\",\"usersex\":\"男\",\"rolename\":\"资产管理员\",\"rightbit\":\"1,2\"}]";
		Log.i("lwc_","执行loginres.parse(new UserRowMapper());"+str);
		loginres.setJsonStr(str);
		loginres.parse(new UserRowMapper());
		
		return loginres;
	}

	




	

	

}
