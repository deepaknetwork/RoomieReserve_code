package com.roomiereserve.dto;

public class UserDetail {
	public Long id;
	public String name;
	public String role;

	public UserDetail() {
	}

	public UserDetail(Long id, String name, String role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}

}
