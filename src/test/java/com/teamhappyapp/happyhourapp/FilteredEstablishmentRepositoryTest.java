package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


public class FilteredEstablishmentRepositoryTest {

	@Resource
	private FilteredEstablishmentRepository filteredEstabRepo;

	@Resource
	private EstablishmentRepository establishmentRepo;

	@Resource
	private FilterRepository filterRepo;

	private Establishment createAndSaveTestEstablishment(String name, Schedule testSchedule, Filter... filters) {
		return establishmentRepo.save(createTestEstablishment(name, testSchedule, filters));
	}

	private Establishment createTestEstablishment(String name, Schedule testSchedule, Filter... filters) {
		return new Establishment(name, "address", "lat", "long", "phone", testSchedule, filters);
	}

	@Test
	public void shouldReturnDemoByFilter() {

		// using two filters because I feel better about it that way
		Filter patioFilter = filterRepo.save(new Filter("patio"));
		Filter karaokeFilter = filterRepo.save(new Filter("karaoke"));
		Schedule irrelevant = new Schedule(11, 12);
		String[] filterName = { "patio" };
		// you can pass zero or more arguments for varargs, don't need an array
		Establishment patioAndKaraokeEst = createAndSaveTestEstablishment("Bar with patio and karaoke", irrelevant,
				patioFilter, karaokeFilter);
		createAndSaveTestEstablishment("Bar with karaoke only", irrelevant, karaokeFilter);

		Collection<Establishment> results = filteredEstabRepo.findForFiltersNamed(filterName);

		// assert that only the establishment with patio was returned
		assertThat(results, contains(patioAndKaraokeEst));
	}
}
