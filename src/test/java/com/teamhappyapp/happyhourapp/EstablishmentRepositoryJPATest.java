package com.teamhappyapp.happyhourapp;

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
	
	@Before
	public void createTestEstablishment() {
		testSchedule = new Schedule(5, 8);
		testEstablishment = new Establishment("name", "address", "lat", "long", "phone", testSchedule);
	}
	
	@Test
	public void shouldReturnDemoInSet() {
		establishmentRepo.save(testEstablishment);
		establishmentRepo.findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(6, 7);
	}

}
