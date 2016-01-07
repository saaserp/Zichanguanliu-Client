package com.qufei.model;

import com.qufei.tools.Model;

public class MyProductModel extends Model{
	private String name;
	public MyProductModel(String name, String pTypeID, String optional,
			String defaultValue) {
		super();
		this.name = name;
		this.pTypeID = pTypeID;
		this.optional = optional;
		this.defaultValue = defaultValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpTypeID() {
		return pTypeID;
	}
	public void setpTypeID(String pTypeID) {
		this.pTypeID = pTypeID;
	}
	public String getOptional() {
		return optional;
	}
	public void setOptional(String optional) {
		this.optional = optional;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	private String pTypeID;
	private String optional;
	private String defaultValue;

}
