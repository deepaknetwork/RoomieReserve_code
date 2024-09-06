package com.roomiereserve.dto;

import java.util.List;

import com.roomiereserve.model.Room;

public class DecodedHouse {

	public long houseId;
	public long ownerId;
	public String address;
	public List<DecodedRoom> rooms;

	public DecodedHouse() {
	}

	public DecodedHouse(long houseId, long ownerId, String address, List<DecodedRoom> rooms) {
		super();
		this.houseId = houseId;
		this.ownerId = ownerId;
		this.address = address;
		this.rooms = rooms;
	}
}
