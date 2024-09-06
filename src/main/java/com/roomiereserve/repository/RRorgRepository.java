package com.roomiereserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.RRorg;

@Repository
public interface RRorgRepository extends MongoRepository<RRorg, String> {
	public RRorg findByName(String name);
}
