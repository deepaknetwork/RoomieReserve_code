package com.roomiereserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roomiereserve.dto.Signup;
import com.roomiereserve.dto.ChangePassword;
import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.exception.AlreadyExist;
import com.roomiereserve.exception.NotExist;
import com.roomiereserve.exception.Restricted;
import com.roomiereserve.model.Customer;
import com.roomiereserve.model.Owner;
import com.roomiereserve.repository.CustomerRepository;
import com.roomiereserve.repository.OwnerRepository;

@Service
public class CustomerService {

	CustomerRepository customerrepo;
	OwnerRepository ownerrepo;

	public CustomerService(CustomerRepository customerrepo, OwnerRepository ownerrepo) {
		this.customerrepo = customerrepo;
		this.ownerrepo = ownerrepo;
	}

	public void addCustomer(Signup customer) {
		if (customerrepo.findByPhone(customer.phone()) != null || ownerrepo.findByPhone(customer.phone()) != null) {
			throw new AlreadyExist("Phone number already registered");
		}
		if (customerrepo.findByEmail(customer.email()) != null || ownerrepo.findByEmail(customer.email()) != null) {
			throw new AlreadyExist("Email id already registered");
		}
		try {
			customerrepo.save(
					new Customer(customer.phone(), customer.email(), customer.name(), customer.password(), 30000));
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public Customer authCustomerByPhone(PhoneAuthDetails auth) {
		Customer customer = customerrepo.findByPhone(auth.phone());
		if (customer == null) {
			return customer;
		}
		if (!auth.password().equals(customer.getPassword())) {
			throw new Restricted("Wrong password");
		}
		return customer;
	}

	public Customer authCustomerByEmail(EmailAuthDetails auth) {
		Customer customer = customerrepo.findByEmail(auth.email());
		if (customer == null) {
			return customer;
		}
		if (!auth.password().equals(customer.getPassword())) {
			throw new Restricted("Wrong password");
		}
		return customer;
	}

	public Customer findCustomerByPhone(Long phone) {
		Customer customer;
		try {
			customer = customerrepo.findByPhone(phone);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
		if (customer == null) {
			throw new NotExist("No customer found");
		}
		return customer;
	}

	public Customer findCustomerByEmail(String email) {
		Customer customer;
		try {
			customer = customerrepo.findByEmail(email);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
		if (customer == null) {
			throw new NotExist("No customer found");
		}
		return customer;
	}

	public void topup(Long phone, Long ammount) {
		try {
			Customer customer = findCustomerByPhone(phone);
			customer.setBalance(customer.getBalance() + ammount);
			customerrepo.save(customer);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public void changePassword(Long phone, ChangePassword change) {
		Customer customer = findCustomerByPhone(phone);
		if (customer.getPassword().equals(change.oldPassword())) {
			customer.setPassword(change.newPassword());
			customerrepo.save(customer);
		} else {
			throw new Restricted("wrong old password");
		}
	}

}
