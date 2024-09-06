package com.roomiereserve.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="RRorg")
public class RRorg {
	@Id
	private String name;
	private long houseId;
	private long reserveId;
	private long imageId;
	public RRorg() {}
	public RRorg(String name, long houseId, long reserveId,long imageId) {
		super();
		this.name = name;
		this.houseId = houseId;
		this.reserveId = reserveId;
		this.imageId=imageId;
	}
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getHouseId() {
		return houseId;
	}
	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	public long getReserveId() {
		return reserveId;
	}
	public void setReserveId(long reserveId) {
		this.reserveId = reserveId;
	}
	
	
}
