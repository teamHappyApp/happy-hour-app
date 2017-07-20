package com.teamhappyapp.happyhourapp;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EstablishmentController {

	@Resource
	private EstablishmentRepository establishmentRepo;
	
//	@Resource
//	private ScheduleRepository scheduleRepo;

	@RequestMapping("/establishments")
	public String showAllEstablishments(Model model) {
		Iterable<Establishment> establishments = establishmentRepo.findAll();
		model.addAttribute("allEstablishments", establishments);
		return "allEstablishmentsTemplate";
	}

}
