package com.roomiereserve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {
	@Id
	public String imgid;
	public String img;

	public Image() {
	}

	public Image(String imgid, String img) {
		super();
		this.imgid = imgid;
		this.img = img;
	}

}
