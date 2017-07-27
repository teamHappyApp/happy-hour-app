package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class FilteredEstablishmentRepositoryTest {

	@Test
	public void shouldReturnDemoByFilter() {
		FilteredEstablishmentRepository underTest = new FilteredEstablishmentRepository();

		Filter patioFilter = new Filter("patio");
		Filter karaokeFilter = new Filter("karaoke");

		Schedule irrelevant = new Schedule(11, 12);

		Establishment patioAndKaraokeEst = new Establishment("Bar with patio and karaoke", "address", "lat", "long",
				"phone", irrelevant, patioFilter, karaokeFilter);
		Establishment karaokeEst = new Establishment("Bar with karaoke only", "address", "lat", "long", "phone",
				irrelevant, karaokeFilter);

		String[] filterTest = { "patio" };
		
		Collection<Establishment> results = underTest.findForFiltersNamed(filterTest);

		// assert that only the establishment with patio was returned
		assertThat(results, contains(patioAndKaraokeEst));
	}

}
