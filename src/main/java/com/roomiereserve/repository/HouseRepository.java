package com.roomiereserve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.House;

@Repository
public interface HouseRepository extends MongoRepository<House, Long> {
	public House findByAddress(String address);

	public House findByHouseId(Long id);

	public void deleteByHouseId(Long id);

	public List<House> findAllByOwnerId(Long ownerId);

}
