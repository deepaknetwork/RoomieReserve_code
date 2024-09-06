package com.roomiereserve.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.roomiereserve.dto.Signup;
import com.roomiereserve.dto.ChangePassword;
import com.roomiereserve.dto.DecodedHouse;
import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.dto.ReserveDetails;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.House;
import com.roomiereserve.model.Reserve;
import com.roomiereserve.service.CustomerService;
import com.roomiereserve.service.HouseService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("customer")
public class CustomerController {

	CustomerService customerser;
	HouseService houseser;

	// Injecting the dependencies
	public CustomerController(CustomerService customerser, HouseService houseser) {
		this.customerser = customerser;
		this.houseser = houseser;
	}

	// Add customer
	@PostMapping("signup")
	public void addCustomer(@RequestBody Signup customer) {
		customerser.addCustomer(customer);
	}

	// Get customer using phone number
	@GetMapping("phone/{phone}")
	public Customer findCustomerByPhone(@PathVariable Long phone) {
		return customerser.findCustomerByPhone(phone);
	}

	// Get customer using email id
	@GetMapping("email/{email}")
	public Customer findCustomerByEmail(@PathVariable String email) {
		return customerser.findCustomerByEmail(email);
	}

	// Reserve a room
	@PostMapping("reserve/{phone}")
	public void reserve(@PathVariable Long phone, @RequestBody ReserveDetails reserve) {
		houseser.reserveHouse(phone, reserve);
	}

	// Get all reserves of customer using phone number
	@GetMapping("reserve/{phone}")
	public List<Reserve> findReserveByCustomerId(@PathVariable Long phone) {
		return houseser.reserveByCustomerId(phone);
	}

	// Topup customer wallet balance
	@PostMapping("topup/{phone}/{amount}")
	public void topup(@PathVariable Long phone, @PathVariable Long amount) {
		customerser.topup(phone, amount);
	}

	// Change a customer's password
	@PostMapping("changepassword/{phone}")
	public void chnagepassword(@PathVariable Long phone, @RequestBody ChangePassword change) {
		customerser.changePassword(phone, change);
	}

}
