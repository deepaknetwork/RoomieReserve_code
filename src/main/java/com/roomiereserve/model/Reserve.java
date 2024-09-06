package com.roomiereserve.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reserves")
public class Reserve {
	@Id
	private long reserveId;
	private long houseId;
	private long roomId;
	private String address;
	private long ownerId;
	private long customerId;
	private LocalDate date;
	private LocalDate bookedDate;
	private long member;

	public long getMember() {
		return member;
	}

	public void setMember(long member) {
		this.member = member;
	}

	private long days;
	private long rent;

	public long getId() {
		return reserveId;
	}

	public void setId(long id) {
		this.reserveId = id;
	}

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public long getRent() {
		return rent;
	}

	public void setRent(long rent) {
		this.rent = rent;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Reserve() {
	}

	public Reserve(long reserveId, long houseId, long roomId, String address, long ownerId, long customerId,
			LocalDate date, LocalDate bookedDate, long member, long days, long rent) {
		super();
		this.reserveId = reserveId;
		this.roomId = roomId;
		this.houseId = houseId;
		this.address = address;
		this.ownerId = ownerId;
		this.customerId = customerId;
		this.date = date;
		this.bookedDate = bookedDate;
		this.member = member;
		this.days = days;
		this.rent = rent;
	}

}
