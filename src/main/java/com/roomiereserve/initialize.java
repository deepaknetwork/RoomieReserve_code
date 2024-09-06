package com.roomiereserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.roomiereserve.model.RRorg;
import com.roomiereserve.repository.RRorgRepository;
import com.roomiereserve.service.Orgservice;

@Component
public class initialize implements CommandLineRunner {

	@Autowired
	Orgservice service;

	@Override
	public void run(String... args) throws Exception {
		service.startApp();
	}

}
