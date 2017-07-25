package com.teamhappyapp.happyhourapp;

import java.util.HashSet;
import static java.util.Arrays.asList;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EstablishmentRepositoryJPATest {

	@Resource
	private EstablishmentRepository establishmentRepo;

	private Schedule testSchedule;

	private Establishment testEstablishment;

	private Filter[] filters = {new Filter("patio")};

	@Before
	public void createTestEstablishment() {
		testSchedule = new Schedule(5, 8);
		testEstablishment = new Establishment("name", "address", "lat", "long", "phone", testSchedule, filters);
	}


	
	@Test
	public void shouldReturnDemoInSet() {
		establishmentRepo.save(testEstablishment);
		establishmentRepo.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(6, 7);
	}

	@Test
	public void shouldReturnDemoByFilter() {
		establishmentRepo.save(testEstablishment);
		establishmentRepo.findByFilterName("patio");
	}
}
