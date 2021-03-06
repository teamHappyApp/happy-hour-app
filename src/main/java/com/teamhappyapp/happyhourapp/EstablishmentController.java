package com.teamhappyapp.happyhourapp;

import static org.apache.commons.collections4.CollectionUtils.intersection;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentController {

	@Resource
	private EstablishmentRepository establishmentRepo;

	@Resource
	private FilterRepository filterRepo;

	@Resource
	private FilteredEstablishmentRepository filteredEstabRepo;

	// creates JSON for ALL establishments
	@RequestMapping("/establishments")
	public Iterable<Establishment> allEstablishments() {
		Iterable<Establishment> establishments = establishmentRepo.findAll();
		return establishments;
	}

	// returns JSON by schedule of interest
	@RequestMapping("/establishments/bySchedule/{windowBegin}/{windowEnd}")
	public Iterable<Establishment> establishmentsBySchedule(@PathVariable int windowBegin,
			@PathVariable int windowEnd) {
		Iterable<Establishment> establishmentsBySchedule = establishmentRepo
				.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(windowBegin, windowEnd);
		return establishmentsBySchedule;
	}

	@RequestMapping("/establishments/byFilter/{name}")
	public Iterable<Establishment> establishmentsByFilter(@PathVariable String[] name) {
		Iterable<Establishment> establishmentsByFilter = filteredEstabRepo.findForFiltersNamed(name);
		return establishmentsByFilter;
	}

	@RequestMapping("/establishments/bySchedule/{windowBegin}/{windowEnd}/byFilter/{name}")
	public Iterable<Establishment> establishmentsByScheduleAndFilters(@PathVariable int windowBegin,
			@PathVariable int windowEnd, @PathVariable String[] name) {
		Iterable<Establishment> establishmentsBySchedule = establishmentRepo
				.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(windowBegin, windowEnd);
		Iterable<Establishment> establishmentsByFilter = filteredEstabRepo.findForFiltersNamed(name);
		Iterable<Establishment> establishmentsByFilterAndSchedule = intersection(establishmentsBySchedule,
				establishmentsByFilter);

		return establishmentsByFilterAndSchedule;

	}

}
