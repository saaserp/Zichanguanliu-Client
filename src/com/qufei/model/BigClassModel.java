package com.qufei.model;

import com.qufei.tools.Model;

public class BigClassModel extends Model {

	public BigClassModel(String className, String ct, String type, String je) {
		super();
		this.className = className;
		this.ct = ct;
		this.type = type;
		this.je = je;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJe() {
		return je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	private String className;
	private String ct;
	private String type;
	private String je;

}
