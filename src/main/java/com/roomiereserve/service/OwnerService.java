package com.roomiereserve.service;

import org.springframework.stereotype.Service;

import com.roomiereserve.dto.ChangePassword;
import com.roomiereserve.dto.EmailAuthDetails;
import com.roomiereserve.dto.PhoneAuthDetails;
import com.roomiereserve.dto.Signup;
import com.roomiereserve.exception.AlreadyExist;
import com.roomiereserve.exception.NotExist;
import com.roomiereserve.exception.Restricted;
import com.roomiereserve.model.Owner;
import com.roomiereserve.repository.CustomerRepository;
import com.roomiereserve.repository.OwnerRepository;

@Service
public class OwnerService {

	OwnerRepository ownerrepo;
	CustomerRepository customerrepo;

	public OwnerService(OwnerRepository ownerrepo, CustomerRepository customerrepo) {
		this.ownerrepo = ownerrepo;
		this.customerrepo = customerrepo;
	}

	public void addOwner(Signup owner) {
		if (ownerrepo.findByPhone(owner.phone()) != null || customerrepo.findByPhone(owner.phone()) != null) {
			throw new AlreadyExist("Phone number already registered");
		}
		if (ownerrepo.findByEmail(owner.email()) != null || customerrepo.findByEmail(owner.email()) != null) {
			throw new AlreadyExist("Email id already registered");
		}
		try {
			ownerrepo.save(new Owner(owner.phone(), owner.email(), owner.name(), owner.password(), 30000));
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public Owner authOwnerByPhone(PhoneAuthDetails auth) {
		Owner owner = ownerrepo.findByPhone(auth.phone());
		if (owner == null) {
			return owner;
		}
		if (!auth.password().equals(owner.getPassword())) {
			throw new Restricted("Wrong password");
		}
		return owner;
	}

	public Owner authOwnerByEmail(EmailAuthDetails auth) {
		Owner owner = ownerrepo.findByEmail(auth.email());
		if (owner == null) {
			return owner;
		}
		if (!auth.password().equals(owner.getPassword())) {
			throw new Restricted("Wrong password");
		}
		return owner;
	}

	public Owner findOwnerByPhone(Long phone) {
		Owner owner;
		try {
			owner = ownerrepo.findByPhone(phone);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
		if (owner == null) {
			throw new NotExist("No owner found");
		}
		return owner;
	}

	public Owner findOwnerByEmail(String email) {
		Owner owner;
		try {
			owner = ownerrepo.findByEmail(email);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
		if (owner == null) {
			throw new NotExist("No owner found");
		}
		return owner;
	}

	public void topup(Long phone, Long ammount) {
		try {
			Owner owner = findOwnerByPhone(phone);
			owner.setBalance(owner.getBalance() + ammount);
			ownerrepo.save(owner);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public void withdraw(Long phone, Long ammount) {
		try {
			Owner owner = findOwnerByPhone(phone);
			owner.setBalance(owner.getBalance() - ammount);
			ownerrepo.save(owner);
		} catch (Exception e) {
			throw new RuntimeException("Something went wrong, try again later");
		}
	}

	public void changePassword(Long phone, ChangePassword change) {
		Owner owner = findOwnerByPhone(phone);
		if (owner.getPassword().equals(change.oldPassword())) {
			owner.setPassword(change.newPassword());
			ownerrepo.save(owner);
		} else {
			throw new Restricted("wrong old password");
		}
	}

}
