package com.qufei.model;

import com.qufei.tools.Model;

public class Dict_国资2010Model extends Model{
	String 代码;
	public Dict_国资2010Model(String 代码, String 名称) {
		super();
		this.代码 = 代码;
		this.名称 = 名称;
	}
	public String get代码() {
		return 代码;
	}
	public void set代码(String 代码) {
		this.代码 = 代码;
	}
	public String get名称() {
		return 名称;
	}
	public void set名称(String 名称) {
		this.名称 = 名称;
	}
	String 名称;	
}
