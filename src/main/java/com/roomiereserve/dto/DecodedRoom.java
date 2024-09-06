package com.roomiereserve.dto;

import java.util.List;

import com.roomiereserve.model.Amenitie;
import com.roomiereserve.model.Booking;

public class DecodedRoom {
	public long roomNo;
	public long area;
	public long beds;
	public long rent;
	public List<Booking> bookings;
	public List<Amenitie> amenities;
	public List<byte[]> images;

	public DecodedRoom() {
	}

	public DecodedRoom(long roomNo, long area, long beds, long rent, List<Booking> bookings, List<Amenitie> amenities,
			List<byte[]> images) {
		super();
		this.roomNo = roomNo;
		this.area = area;
		this.beds = beds;
		this.rent = rent;
		this.bookings = bookings;
		this.amenities = amenities;
		this.images = images;
	}

}
