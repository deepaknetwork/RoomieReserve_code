package com.roomiereserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.Customer;
import com.roomiereserve.model.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, Long> {
	public Owner findByPhone(Long phone);

	public Owner findByEmail(String email);
}