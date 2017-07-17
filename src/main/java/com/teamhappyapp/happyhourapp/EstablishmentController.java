package com.teamhappyapp.happyhourapp;

import javax.annotation.Resource;

import org.springframework.ui.Model;

public class EstablishmentController {

	@Resource
	private EstablishmentRepository establishmentRepo;
	
	public String showAllEstablishments(Model model) {
		Iterable<Establishment> establishments = establishmentRepo.findAll();
		
		model.addAttribute("allEstablishments", establishments);
		
		return "allEstablishmentsTemplate";
	}

}
