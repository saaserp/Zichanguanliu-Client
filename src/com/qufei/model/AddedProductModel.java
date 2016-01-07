package com.qufei.model;

import com.qufei.tools.Model;

public class AddedProductModel extends Model {
	String pTypeID="";
	public String getpTypeID() {
		return pTypeID;
	}
	public void setpTypeID(String pTypeID) {
		this.pTypeID = pTypeID;
	}
	public AddedProductModel(String id ,String attribute, String writTime, String typeName,String pTypeID) {
		super();
		this.id=id;
		this.attribute = attribute;
		this.writTime = writTime;
		this.typeName = typeName;
		this.pTypeID=pTypeID;
		
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getWritTime() {
		return writTime;
	}
	public void setWritTime(String writTime) {
		this.writTime = writTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String attribute;
	public String writTime;
	public String typeName;
	public String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
