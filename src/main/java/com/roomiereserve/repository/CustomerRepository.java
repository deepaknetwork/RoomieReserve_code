package com.roomiereserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
	public Customer findByPhone(Long phone);

	public Customer findByEmail(String email);

}
