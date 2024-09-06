package com.roomiereserve.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.dto.UserDetail;
import com.roomiereserve.dto.viewRoom;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.House;
import com.roomiereserve.model.Owner;
import com.roomiereserve.model.Reserve;
import com.roomiereserve.model.Room;
import com.roomiereserve.service.CommonService;
import com.roomiereserve.service.CustomerService;
import com.roomiereserve.service.HouseService;
import com.roomiereserve.service.Orgservice;
import com.roomiereserve.service.OwnerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/common")
public class CommonController {

	CommonService commonser;
	HouseService houseser;
	Orgservice orgser;
	OwnerService ownerser;
	CustomerService customerser;

	public CommonController(CommonService commonser, HouseService houseser, Orgservice orgser, OwnerService ownerser,
			CustomerService customerser) {
		this.commonser = commonser;
		this.houseser = houseser;
		this.orgser = orgser;
		this.ownerser = ownerser;
		this.customerser = customerser;
	}

	// Login process using phone no
	@PostMapping("login/phone")
	public UserDetail authCustomerByPhone(@RequestBody PhoneAuthDetails auth) {
		return commonser.loginPhone(auth);
	}

	// Login process using email id
	@PostMapping("login/email")
	public UserDetail authCustomerByEmail(@RequestBody EmailAuthDetails auth) {
		return commonser.loginEmail(auth);
	}

	// Find owner using phone number
	@GetMapping("owner/{ownerId}")
	public Owner findOwner(@PathVariable Long ownerId) {
		return ownerser.findOwnerByPhone(ownerId);
	}

	// Find customer using phone number
	@GetMapping("customer/{customerId}")
	public Customer findCustomer(@PathVariable Long customerId) {
		return customerser.findCustomerByPhone(customerId);
	}

	// Get all rooms
	@GetMapping("rooms")
	public List<viewRoom> allRooms() {
		return houseser.allRooms();
	}

	// Get house using its id
	@GetMapping("house/{houseNo}")
	public House oneHouse(@PathVariable Long houseNo) {
		return houseser.oneHouse(houseNo);
	}

	// Get all houses
	@GetMapping("house")
	public List<House> allHouse() {
		return houseser.allHouse();
	}

	// Get room using its house id and room number
	@GetMapping("room/{houseNo}/{roomNo}")
	public Room oneRoom(@PathVariable Long houseNo, @PathVariable Long roomNo) {
		return houseser.getRoom(houseNo, roomNo);
	}

	// Get a room image using its id
	@GetMapping(value = "image/{index}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> oneImage(@PathVariable String index) {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(houseser.oneImage(index));
	}

	// Get reserve using its id
	@GetMapping("reserve/id/{id}")
	public Reserve findReserveById(@PathVariable Long id) {
		return houseser.reserveById(id);
	}

	// Store RoomieReserve images
	@PostMapping("roomireserve/image")
	public void addRoom(@RequestParam String name, @RequestParam("image") MultipartFile image) throws IOException {
		orgser.addRRImage(name, image);
	}

	// Get RoomieReserve images
	@GetMapping(value = "roomireserve/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> Image(@PathVariable String name) throws IOException {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(orgser.getRRImage(name));
	}
}
