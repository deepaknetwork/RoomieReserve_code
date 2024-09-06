package com.roomiereserve.model;

import java.time.LocalDate;

public class Booking {
	public long reserveId;
	public long customerId;
	public LocalDate date;
	public long membmer;
	public long day;

	public Booking() {
	}

	public Booking(long reserveId, long customerId, LocalDate date, long membmer, long day) {
		super();
		this.reserveId = reserveId;
		this.customerId = customerId;
		this.date = date;
		this.membmer = membmer;
		this.day = day;
	}
}
