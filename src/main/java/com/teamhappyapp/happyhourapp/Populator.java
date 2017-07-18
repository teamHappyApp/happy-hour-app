//package com.teamhappyapp.happyhourapp;
//
//import javax.annotation.Resource;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Populator implements CommandLineRunner {
//
//	@Resource
//	private EstablishmentRepository establishmentRepo;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		Establishment bossygrrls = new Establishment("Bossy Grrl's PinUp Joint", "2598 N. High Street, Columbus, OH 43202", "40.0152724", "-83.0136126");
//		establishmentRepo.save(bossygrrls);
//		
//		Establishment aceofcups = new Establishment("Ace of Cups", "2619 N High St, Columbus, OH 43202", "40.0152724", "-83.0136126");
//		establishmentRepo.save(aceofcups);
//		
//		Establishment tatoheads = new Establishment("Tatoheads Public House", "1297 Parsons Ave, Columbus, OH 43206", "39.9369271", "-82.9858721");
//		establishmentRepo.save(tatoheads);
//	}
//
//}
