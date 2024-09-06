package com.roomiereserve.dto;

import java.util.List;

import com.roomiereserve.model.Amenitie;

public class UpdateRoom {
	public long beds;
	public long rent;
	public List<Amenitie> amenities;

	public UpdateRoom() {
	}

	public UpdateRoom(long beds, long rent, List<Amenitie> amenities) {
		super();
		this.beds = beds;
		this.rent = rent;
		this.amenities = amenities;
	}

}
