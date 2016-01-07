package com.qufei.model;

import com.qufei.tools.Model;

public class TrueOrFalseModel extends Model{

	private String isOk;

	public TrueOrFalseModel(String isOk) {
		super();
		this.isOk = isOk;
	}

	public String getIsOk() {
		return isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

}
