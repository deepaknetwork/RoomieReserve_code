package com.roomiereserve.model;

import java.time.LocalDate;
import java.util.List;

public class Room {
	public long houseNo;
	public long roomNo;
	public long area;
	public long beds;
	public long rent;
	public List<Booking> bookings;
	public List<Amenitie> amenities;
	public List<String> images;

}
