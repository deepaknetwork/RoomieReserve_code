package com.roomiereserve.dto;

import java.util.List;

import com.roomiereserve.model.Room;

public record HouseDetails(String address, List<Room> rooms) {

}
