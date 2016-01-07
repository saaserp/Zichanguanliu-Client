package com.qufei.model;

import com.qufei.tools.Model;

public class NewProductModel extends Model{
	private String productID;
	
	
	public NewProductModel(String productID, 
			String keyName, String keyType, String keyValue) {
		super();
		this.productID = productID;
		
		
		this.keyName = keyName;
		KeyType = keyType;
		KeyValue = keyValue;

		
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyType() {
		return KeyType;
	}
	public void setKeyType(String keyType) {
		KeyType = keyType;
	}
	public String getKeyValue() {
		return KeyValue;
	}
	public void setKeyValue(String keyValue) {
		KeyValue = keyValue;
	}


	private String keyName;
	private String KeyType;
	private String KeyValue;


}
