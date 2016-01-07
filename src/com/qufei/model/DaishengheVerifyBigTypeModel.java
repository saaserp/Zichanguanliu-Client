package com.qufei.model;

import com.qufei.tools.Model;

public class DaishengheVerifyBigTypeModel extends Model{

	 String typeName;
	 public DaishengheVerifyBigTypeModel(String typeName, String number) {
		super();
		this.typeName = typeName;
		this.number = number;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	String number;
}
