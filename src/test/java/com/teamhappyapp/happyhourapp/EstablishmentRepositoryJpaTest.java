package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

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


}