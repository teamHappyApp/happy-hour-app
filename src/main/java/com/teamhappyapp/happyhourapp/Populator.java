package com.teamhappyapp.happyhourapp;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {

	@Resource
	private EstablishmentRepository establishmentRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Establishment bossygrrls = new Establishment("Bossy Grrl's");
		establishmentRepo.save(bossygrrls);
		
		Establishment aceofcups = new Establishment("Ace of Cups");
		establishmentRepo.save(aceofcups);
		
		Establishment tatoheads = new Establishment("Tatoheads");
		establishmentRepo.save(tatoheads);
	}

}
