package com.roomiereserve.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "houses")
public class House {
	@Id
	private long houseId;
	private long ownerId;
	private String address;
	private List<Room> rooms;

	public House() {
	}

	public House(long houseId, long ownerId, String address, List<Room> rooms) {
		super();
		this.houseId = houseId;
		this.ownerId = ownerId;
		this.address = address;
		this.rooms = rooms;
	}

	public long getId() {
		return houseId;
	}

	public void setId(long id) {
		this.houseId = id;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
