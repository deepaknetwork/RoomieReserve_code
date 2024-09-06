package com.roomiereserve.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.House;
import com.roomiereserve.model.Reserve;

@Repository
public interface ReserveRepository extends MongoRepository<Reserve, Long> {
	public Reserve findByReserveId(Long reserveId);

	public List<Reserve> findAllByCustomerId(Long customerId);

	public List<Reserve> findAllByOwnerId(Long ownerId);

	public List<Reserve> findAllByHouseId(Long houseId);

}
