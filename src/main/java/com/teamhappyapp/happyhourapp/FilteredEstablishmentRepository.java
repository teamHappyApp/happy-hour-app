package com.teamhappyapp.happyhourapp;

import static org.apache.commons.collections4.CollectionUtils.intersection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository // same as @Component, but indicates intent
public class FilteredEstablishmentRepository {

	@Resource
	private FilterRepository filterRepo;
	
	@Resource
	private EstablishmentRepository estabRepo;

	public Collection<Establishment> findForFiltersNamed(String[] filterNames) {

		Set<Filter> selectedFilters = filterRepo.findByNameIn(filterNames);

		// initialize with set from first filter
		Iterator<Filter> filterItr = selectedFilters.iterator();
		Set<Establishment> currentFilterEstablishments = filterItr.next().getEstablishments();
		Collection<Establishment> filtered = new HashSet<>(currentFilterEstablishments);
		while (filterItr.hasNext()) {
			currentFilterEstablishments = filterItr.next().getEstablishments();
			filtered = intersection(filtered, currentFilterEstablishments);
		}

		// could do return new HashSet<>(filtered) if we wanted to return a set
		return filtered;
	}
	
//	public Collection<Establishment> findForFiltersAndSchedule (String[] filterNames, int windowBegin, int windowEnd) {
//
//		Set<Establishment> bySchedule = estabRepo.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(windowBegin, windowEnd);
//		Collection<Establishment> byFilters = findForFiltersNamed(filterNames);
//		
//		Collection<Establishment> byScheduleAndFilters = intersection(byFilters, bySchedule);
//		
//		return byScheduleAndFilters;
//	}
}