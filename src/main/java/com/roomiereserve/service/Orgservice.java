package com.roomiereserve.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.roomiereserve.exception.NotExist;
import com.roomiereserve.model.House;
import com.roomiereserve.model.Image;
import com.roomiereserve.model.Owner;
import com.roomiereserve.model.RRorg;
import com.roomiereserve.model.Room;
import com.roomiereserve.repository.ImageRepository;
import com.roomiereserve.repository.RRorgRepository;

@Service
public class Orgservice {

	RRorgRepository repo;
	ImageRepository imgrepo;

	public Orgservice(RRorgRepository repo, ImageRepository imgrepo) {
		this.repo = repo;
		this.imgrepo = imgrepo;
	}

	public void startApp() {
		if (repo.findByName("RRorg") == null) {
			repo.save(new RRorg("RRorg", 111, 111, 111));
		}
	}

	public long getHouseId() {
		RRorg org = repo.findByName("RRorg");
		long id = org.getHouseId();
		org.setHouseId(id + 1);
		repo.save(org);
		return id;
	}

	public long getReserveId() {
		RRorg org = repo.findByName("RRorg");
		long id = org.getReserveId();
		org.setReserveId(id + 1);
		repo.save(org);
		return id;
	}

	public long getImageId() {
		RRorg org = repo.findByName("RRorg");
		long id = org.getImageId();
		org.setImageId(id + 1);
		repo.save(org);
		return id;
	}

	public void addRRImage(String name, MultipartFile image) throws IOException {
		imgrepo.save(new Image(name, Base64.getEncoder().encodeToString(image.getBytes())));
	}

	public byte[] getRRImage(String name) throws IOException {
		Image img = imgrepo.findByImgid(name);
		return Base64.getDecoder().decode(img.img);
	}

}
