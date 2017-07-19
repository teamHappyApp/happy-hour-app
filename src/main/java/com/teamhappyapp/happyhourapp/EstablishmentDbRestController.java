package com.teamhappyapp.happyhourapp;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentDbRestController {

	@Resource
	private EstablishmentRepository establishmentRepo;

	@RequestMapping("/establishmentDatabase")
	public Iterable<Establishment> establishment() {
		Iterable<Establishment> establishments = establishmentRepo.findAll();
		return establishments;
	}
}
