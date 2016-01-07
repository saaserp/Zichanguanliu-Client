package com.qufei.model;

import java.util.ArrayList;
import java.util.List;

import com.qufei.tools.Model;

public class ImageModel extends Model {

String images;
public List<String> srcList;
public List<String> getSrcList(){
	String []s=images.split(",");
	for(String sr:s){
		srcList.add(sr);
	}
	return srcList;
}

public ImageModel(String images) {
	super();
	srcList=new ArrayList<String>();
	this.images = images;
}

public String getImages() {
	return images;
}

public void setImages(String images) {
	this.images = images;
}

}
