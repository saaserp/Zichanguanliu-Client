package com.qufei.model;

import com.qufei.tools.Model;


public class UserModel extends Model {
	String isok;
	String userid;
	String username;
	String usersex;
	String rolename;
	String rightbit;
	String schoolID;
	
	public String getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(String schoolID) {
		this.schoolID = schoolID;
	}
	public UserModel(String isok, String userid,String schoolID, String username,
			String usersex, String rolename, String rightbit) {
		super();
		this.isok = isok;
		this.userid = userid;
		this.schoolID=schoolID;
		this.username = username;
		this.usersex = usersex;
		this.rolename = rolename;
		this.rightbit = rightbit;
	}
	public String getIsok() {
		return isok.trim();
	}
	public void setIsok(String isok) {
		this.isok = isok;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRightbit() {
		return rightbit;
	}
	public void setRightbit(String rightbit) {
		this.rightbit = rightbit;
	}
}
