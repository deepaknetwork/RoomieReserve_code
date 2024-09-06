package com.roomiereserve.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roomiereserve.model.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, Long> {
	public Image findByImgid(String imgId);

	public void deleteByImgid(String imgId);
}
