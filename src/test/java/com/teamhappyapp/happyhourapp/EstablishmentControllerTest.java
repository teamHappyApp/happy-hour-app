package com.teamhappyapp.happyhourapp;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class EstablishmentControllerTest {

	@InjectMocks
	private EstablishmentController underTest;
	
	@Mock
	private Iterable<Establishment> allEstablishments;
	
	@Mock
	private Model model;
	
	@Mock 
	private EstablishmentRepository establishmentRepo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldShowAllEstablishments() {
		when(establishmentRepo.findAll()).thenReturn(allEstablishments);
		
		underTest.showAllEstablishments(model);
		
		verify(model).addAttribute("allEstablishments", allEstablishments);
	}
	
	@Test
	public void shouldReturnAllEstablishmentsTemplate() {
		String result = underTest.showAllEstablishments(model);
		
		assertThat(result, is("allEstablishmentsTemplate"));
	}
}
