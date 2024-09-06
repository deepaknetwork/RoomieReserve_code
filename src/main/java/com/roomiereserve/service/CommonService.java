package com.roomiereserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.dto.UserDetail;
import com.roomiereserve.exception.NotExist;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.Owner;

@Service
public class CommonService {

	OwnerService ownerser;
	CustomerService customerser;

	public CommonService(OwnerService ownerser, CustomerService customerser) {
		this.ownerser = ownerser;
		this.customerser = customerser;
	}

	public UserDetail loginPhone(PhoneAuthDetails details) {
		Owner owner = ownerser.authOwnerByPhone(details);
		if (owner != null) {
			return new UserDetail(owner.getPhone(), owner.getName(), "OWNER");
		}
		Customer customer = customerser.authCustomerByPhone(details);
		if (customer != null) {
			return new UserDetail(customer.getPhone(), customer.getName(), "GUEST");
		}
		throw new NotExist("No user found with this mobile number");
	}

	public UserDetail loginEmail(EmailAuthDetails details) {
		Owner owner = ownerser.authOwnerByEmail(details);
		if (owner != null) {
			return new UserDetail(owner.getPhone(), owner.getName(), "OWNER");
		}
		Customer customer = customerser.authCustomerByEmail(details);
		if (customer != null) {
			return new UserDetail(customer.getPhone(), customer.getName(), "GUEST");
		}
		throw new NotExist("No user found with this email");
	}
}
