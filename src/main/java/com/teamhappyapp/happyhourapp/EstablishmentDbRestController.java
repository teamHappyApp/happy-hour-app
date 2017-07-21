package com.teamhappyapp.happyhourapp;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentDbRestController {

	@Resource
	private EstablishmentRepository establishmentRepo;

	// creates JSON for ALL establishments
	@RequestMapping("/establishmentDatabase")
	public Iterable<Establishment> allEstablishments() {
		Iterable<Establishment> establishments = establishmentRepo.findAll();
		return establishments;
	}
	
	@RequestMapping("/establishmentsBySchedule/{windowBegin}/{windowEnd}")
	public Iterable<Establishment> establishmentsBySchedule(@PathVariable  int windowBegin, @PathVariable int windowEnd){
		Iterable<Establishment> establishmentsBySchedule = establishmentRepo.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(windowBegin, windowEnd);
		return establishmentsBySchedule;
	}
}
