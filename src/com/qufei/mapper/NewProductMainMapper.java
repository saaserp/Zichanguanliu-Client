package com.qufei.mapper;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import android.os.Handler;

import com.qufei.model.NewProudctMainModel;
import com.qufei.tools.IRowMapper;
import com.qufei.tools.Model;

public class NewProductMainMapper implements IRowMapper{

	@Override
	public Model mappingRow(org.json.JSONObject jsobj) {
		// TODO Auto-generated method stub
		NewProudctMainModel md=null;
		try {//{"userID":"","productID":"","productType":"","procuctName":""}
			md=new NewProudctMainModel(jsobj.getString("资产编号")==""?" ":jsobj.getString("资产编号"),
					jsobj.getString("资产名称")==""?" ":jsobj.getString("资产名称"),
					jsobj.getString("资产类别")==""?" ":jsobj.getString("资产类别"),
					jsobj.getString("userID")==""?" ":jsobj.getString("userID"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return md;
	}

	@Override
	public Map<String, String> mappingRow(Model md) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<String, String>();
		NewProudctMainModel mdd=(NewProudctMainModel)md;
		map.put("资产编号", mdd.get资产编号()==""?"":mdd.get资产编号());
		map.put("资产名称", mdd.get资产名称()==""?"":mdd.get资产名称());
		map.put("资产类别", mdd.get资产类别()==""?"":mdd.get资产类别());
		map.put("userID", mdd.getUserID());
		return map;
	}

	

}
