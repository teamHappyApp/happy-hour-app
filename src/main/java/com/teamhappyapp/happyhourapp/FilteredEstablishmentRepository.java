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
}