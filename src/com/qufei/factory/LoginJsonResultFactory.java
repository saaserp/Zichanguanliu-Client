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
		Log.i("lwc_","ִ��JsonResult loginres=new JsonResult();");
		JsonResult loginres=new JsonResult();
		Log.i("lwc_","ִ��loginres.setJsonStr(super.getJsonStr());");
		String str=super.getJsonStr();
		
		if(str=="")
			return null;
		//str="[{\"isok\":\"true\",\"userid\":\"0001\",\"username\":\"��ΰ\",\"usersex\":\"��\",\"rolename\":\"�ʲ�����Ա\",\"rightbit\":\"1,2\"}]";
		Log.i("lwc_","ִ��loginres.parse(new UserRowMapper());"+str);
		loginres.setJsonStr(str);
		loginres.parse(new UserRowMapper());
		
		return loginres;
	}

	




	

	

}
