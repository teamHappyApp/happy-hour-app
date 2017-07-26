package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EstablishmentRepositoryJpaTest {

	@Resource
	private EstablishmentRepository establishmentRepo;

	@Resource
	private FilterRepository filterRepo;

	@Test
	public void shouldReturnDemoInSet() {
		Schedule testSchedule = new Schedule(5, 8);
		Establishment testEstablishment = createAndSaveTestEstablishment("name", testSchedule);

		Set<Establishment> results = establishmentRepo
				.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(6, 7);

		// remember the contains matcher actually means "contains exactly these things in this order"
		assertThat(results, contains(testEstablishment));
	}

	private Establishment createAndSaveTestEstablishment(String name, Schedule testSchedule, Filter... filters) {
		return establishmentRepo.save(createTestEstablishment(name, testSchedule, filters));
	}

	private Establishment createTestEstablishment(String name, Schedule testSchedule, Filter...filters) {
		return new Establishment(name, "address", "lat", "long", "phone", testSchedule, filters);
	}

	@Test
	public void shouldReturnDemoByFilter() {

		// using two filters because I feel better about it that way
		Filter patioFilter = filterRepo.save(new Filter("patio"));
		Filter karaokeFilter = filterRepo.save(new Filter("karaoke"));
		Schedule irrelevant = new Schedule(11, 12);
		String[] filterName = {"patio"};
		// you can pass zero or more arguments for varargs, don't need an array
		Establishment patioAndKaraokeEst = createAndSaveTestEstablishment("Bar with patio and karaoke", irrelevant, patioFilter, karaokeFilter);
		createAndSaveTestEstablishment("Bar with karaoke only", irrelevant, karaokeFilter);
		
		Set<Establishment> results = establishmentRepo.findByFiltersName(filterName);

		// assert that only the establishment with patio was returned
		assertThat(results, contains(patioAndKaraokeEst));
	}
}