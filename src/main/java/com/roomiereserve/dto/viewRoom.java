package com.roomiereserve.dto;

import java.util.List;

import com.roomiereserve.model.Amenitie;
import com.roomiereserve.model.Booking;

public class viewRoom {
	public long houseNo;
	public long roomNo;
	public String address;
	public long area;
	public long beds;
	public long rent;
	public List<Booking> bookings;
	public List<Amenitie> amenities;
	public List<String> images;

	public viewRoom() {
	}

	public viewRoom(long houseNo, long roomNo, String address, long area, long beds, long rent, List<Booking> bookings,
			List<Amenitie> amenities, List<String> images) {
		super();
		this.houseNo = houseNo;
		this.roomNo = roomNo;
		this.address = address;
		this.area = area;
		this.beds = beds;
		this.rent = rent;
		this.bookings = bookings;
		this.amenities = amenities;
		this.images = images;
	}

}
