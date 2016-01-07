package com.qufei.model;

import com.qufei.tools.Model;

public class ProductModel extends Model {
	
String	pTypeID;
public ProductModel(String pTypeID, String attribute, String typeName,
		String father) {
	super();
	this.pTypeID = pTypeID;
	this.attribute = attribute;
	this.typeName = typeName;
	this.father = father;
}
public String getpTypeID() {
	return pTypeID;
}
public void setpTypeID(String pTypeID) {
	this.pTypeID = pTypeID;
}
public String getAttribute() {
	return attribute;
}
public void setAttribute(String attribute) {
	this.attribute = attribute;
}
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}
public String getFather() {
	return father;
}
public void setFather(String father) {
	this.father = father;
}
String attribute;
String	typeName;
String	father;


	

}
