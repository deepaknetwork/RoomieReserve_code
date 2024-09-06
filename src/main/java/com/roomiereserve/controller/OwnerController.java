package com.roomiereserve.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roomiereserve.dto.AddHouse;
import com.roomiereserve.dto.ChangePassword;
import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.HouseDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.dto.Signup;
import com.roomiereserve.dto.UpdateRoom;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.House;
import com.roomiereserve.model.Owner;
import com.roomiereserve.model.Reserve;
import com.roomiereserve.model.Room;
import com.roomiereserve.service.CustomerService;
import com.roomiereserve.service.HouseService;
import com.roomiereserve.service.OwnerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("owner")
public class OwnerController {

	OwnerService ownerser;
	HouseService houseser;

	// Injecting the dependencies
	public OwnerController(OwnerService ownerser, HouseService houseser) {
		this.ownerser = ownerser;
		this.houseser = houseser;

	}

	// Add owner
	@PostMapping("signup")
	public void addOwner(@RequestBody Signup owner) {
		ownerser.addOwner(owner);
	}

	// Find owner using phone number
	@GetMapping("phone/{phone}")
	public Owner findOwnerByPhone(@PathVariable Long phone) {
		return ownerser.findOwnerByPhone(phone);
	}

	// Find owner using email id
	@GetMapping("email/{email}")
	public Owner findOwnerByEmail(@PathVariable String email) {
		return ownerser.findOwnerByEmail(email);
	}

	// Add a new house
	@PostMapping("house/{phone}")
	public void addHouse(@PathVariable Long phone, @RequestBody AddHouse house) {
		houseser.addHouse(phone, house);
	}

	// Get all houses of a owner
	@GetMapping("house/{phone}")
	public List<House> allHouse(@PathVariable Long phone) {
		return houseser.OwnerHouse(phone);
	}

	// Get all reserves of owner using phone number
	@GetMapping("reserve/{phone}")
	public List<Reserve> findReserveByCustomerId(@PathVariable Long phone) {
		return houseser.reserveByOwnerId(phone);
	}

	// Add new room
	@PostMapping("room/{ownerId}")
	public void addRoom(@PathVariable Long ownerId, @RequestBody Room room) {
		houseser.addRoom(ownerId, room);
	}

	// Store a room image
	@PostMapping("image")
	public void addImage(@RequestParam Long ownerId, @RequestParam Long houseId, @RequestParam Long roomNo,
			@RequestParam("image") MultipartFile image) throws IOException {
		houseser.addRoomWithImage(ownerId, houseId, roomNo, image);
	}

	// Remove a room image
	@DeleteMapping("image/{houseid}/{roomid}/{id}")
	public void deleteImage(@PathVariable Long houseid, @PathVariable Long roomid, @PathVariable String id) {
		houseser.deleteImage(houseid, roomid, id);
	}

	// Update a room
	@PatchMapping("room/{hosueid}/{roomid}")
	public void patchRoom(@PathVariable Long hosueid, @PathVariable Long roomid, @RequestBody UpdateRoom room) {
		houseser.patchRoom(hosueid, roomid, room);
	}

	// Delete a room
	@DeleteMapping("room/{hosueid}/{roomid}")
	public void deleteRoom(@PathVariable Long hosueid, @PathVariable Long roomid) {
		houseser.deleteRoom(hosueid, roomid);
	}

	// Delete a house
	@DeleteMapping("house/{hosueid}")
	public void deleteHouse(@PathVariable Long hosueid) {
		houseser.deleteHouse(hosueid);
	}

	// Get all reserves os a room
	@GetMapping("bookings/{houseId}/{roomId}")
	public List<Reserve> getbookingsRoom(@PathVariable Long houseId, @PathVariable Long roomId) {
		return houseser.getbookingsRoom(houseId, roomId);
	}

	// Topup owner wallet
	@PostMapping("topup/{phone}/{amount}")
	public void topup(@PathVariable Long phone, @PathVariable Long amount) {
		ownerser.topup(phone, amount);
	}

	// Withdraw from wallet
	@PostMapping("withdraw/{phone}/{amount}")
	public void withdraw(@PathVariable Long phone, @PathVariable Long amount) {
		ownerser.withdraw(phone, amount);
	}

	// Change password of owner
	@PostMapping("changepassword/{phone}")
	public void chnagepassword(@PathVariable Long phone, @RequestBody ChangePassword change) {
		ownerser.changePassword(phone, change);
	}

}
