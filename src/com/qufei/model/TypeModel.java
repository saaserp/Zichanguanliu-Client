package com.qufei.model;

import com.qufei.tools.Model;

public class TypeModel extends Model {
	String prefix;
	String name;
	String typeNo;//资产分类号
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TypeModel(String prefix, String name,String typeNo) {
		super();
		this.prefix = prefix;
		this.name = name;
		this.typeNo=typeNo;
	}
	

}
