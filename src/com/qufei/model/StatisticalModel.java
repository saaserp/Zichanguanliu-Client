package com.qufei.model;

import com.qufei.tools.Model;


public class StatisticalModel extends Model {
	String pName;
	public StatisticalModel(String pName, String pSum) {
		super();
		this.pName = pName;
		this.pSum = pSum;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpSum() {
		return pSum;
	}
	public void setpSum(String pSum) {
		this.pSum = pSum;
	}
	String pSum;

}
