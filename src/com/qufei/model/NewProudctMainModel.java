package com.qufei.model;

import com.qufei.tools.Model;

public class NewProudctMainModel extends Model{
	String �ʲ����;
	String	�ʲ�����;
	String	�ʲ����;
	String userID;
	public NewProudctMainModel(String �ʲ����, String �ʲ�����, String �ʲ����,
			String userID) {
		super();
		this.�ʲ���� = �ʲ����;
		this.�ʲ����� = �ʲ�����;
		this.�ʲ���� = �ʲ����;
		this.userID = userID;
	}
	public String get�ʲ����() {
		return �ʲ����;
	}
	public void set�ʲ����(String �ʲ����) {
		this.�ʲ���� = �ʲ����;
	}
	public String get�ʲ�����() {
		return �ʲ�����;
	}
	public void set�ʲ�����(String �ʲ�����) {
		this.�ʲ����� = �ʲ�����;
	}
	public String get�ʲ����() {
		return �ʲ����;
	}
	public void set�ʲ����(String �ʲ����) {
		this.�ʲ���� = �ʲ����;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}


}
