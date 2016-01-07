package com.qufei.mapper;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.qufei.model.UserModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;



public class UserRowMapper implements IRowMapper {

	@Override
	public Model mappingRow(JSONObject jsobj) {
		UserModel user=null;
		// TODO Auto-generated method stub
		try {
			user=new UserModel(jsobj.getString("isok"),
					jsobj.getString("userid"),
					jsobj.getString("schoolID"),
					jsobj.getString("username"),
					jsobj.getString("usersex"),
					jsobj.getString("rolename"),
					jsobj.getString("rightbit"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		return null;
	}

}
