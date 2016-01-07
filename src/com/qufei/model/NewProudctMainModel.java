package com.qufei.model;

import com.qufei.tools.Model;

public class NewProudctMainModel extends Model{
	String 资产编号;
	String	资产名称;
	String	资产类别;
	String userID;
	public NewProudctMainModel(String 资产编号, String 资产名称, String 资产类别,
			String userID) {
		super();
		this.资产编号 = 资产编号;
		this.资产名称 = 资产名称;
		this.资产类别 = 资产类别;
		this.userID = userID;
	}
	public String get资产编号() {
		return 资产编号;
	}
	public void set资产编号(String 资产编号) {
		this.资产编号 = 资产编号;
	}
	public String get资产名称() {
		return 资产名称;
	}
	public void set资产名称(String 资产名称) {
		this.资产名称 = 资产名称;
	}
	public String get资产类别() {
		return 资产类别;
	}
	public void set资产类别(String 资产类别) {
		this.资产类别 = 资产类别;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}


}
