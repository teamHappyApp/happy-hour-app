package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FilteredEstablishmentRepositoryTest {

	@InjectMocks
	private FilteredEstablishmentRepository underTest;

	@Mock
	private FilterRepository filterRepo;

	@Mock
	private Filter patioFilter;

	@Mock
	private Filter karaokeFilter;
	
	@Mock
	private Filter kidsWelcomeFilter; // who wants kids at a happy hour?

	private Establishment patioAndKaraokeEst;
	private Establishment karaokeOnlyEst;
	private Establishment patioOnlyEst;
	
	private Schedule fourToSix;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		fourToSix = new Schedule(4, 6);
		
		patioAndKaraokeEst = createTestEstablishment("Bar with patio and karaoke", fourToSix, patioFilter, karaokeFilter);
		karaokeOnlyEst = createTestEstablishment("Bar with karaoke only", fourToSix, karaokeFilter);
		patioOnlyEst = createTestEstablishment("Bar with patio only", fourToSix, patioFilter);
		
		when(patioFilter.getEstablishments()).thenReturn(asSet(patioAndKaraokeEst, patioOnlyEst));
		when(karaokeFilter.getEstablishments()).thenReturn(asSet(patioAndKaraokeEst, karaokeOnlyEst));
		when(kidsWelcomeFilter.getEstablishments()).thenReturn(Collections.emptySet());
	}

	private Establishment createTestEstablishment(String name, Schedule schedule, Filter... filters) {
		return new Establishment(name, null, null, null, null, schedule, filters);
	}

	private <E> Set<E> asSet(E... elements) {
		return new HashSet<>(Arrays.asList(elements));
	}

	@Test
	public void shouldReturnAllEstablishmentsForASingleFilter() {
		
		when(filterRepo.findByNameIn("patio")).thenReturn(asSet(patioFilter));
		
		String[] patioFilter = {"patio"};
		// I changed findForFiltersNamed to accept String... rather than String[] - will this work with the other stuff?
		Collection<Establishment> results = underTest.findForFiltersNamed(patioFilter);
		
		assertThat(results, containsInAnyOrder(patioAndKaraokeEst, patioOnlyEst));
	}
	

//	@Test
//	public void shouldReturnAllEstablishmentsForMultipleFilters() {
//		
//		when(filterRepo.findByNameIn("patio")).thenReturn(asSet(patioFilter));
//		
//		String[] patioFilter = {"patio"};
//		// I changed findForFiltersNamed to accept String... rather than String[] - will this work with the other stuff?
//		Collection<Establishment> results = underTest.findForFiltersNamed(patioFilter);
//		
//		assertThat(results, containsInAnyOrder(patioAndKaraokeEst, patioOnlyEst));
//	}
	
	// @Test
	// public void shouldReturnAllEstablishmentsForSingleFilterAndSchedule() {
	//
	// when(filterRepo.findByNameIn("patio")).thenReturn(asSet(patioFilter));
	//
	// String[] patioFilter = {"patio"};
	//
	// Collection<Establishment> results =
	// underTest.findForFiltersAndSchedule(patioFilter, 4, 6);
	//
	// assertThat(results, is(notNullValue()));
	// }
}