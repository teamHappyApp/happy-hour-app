package com.teamhappyapp.happyhourapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EstablishmentController.class)
public class EstablishmentControllerMvcTest {

	@Resource
	private MockMvc mvc;

	// similar, but specifically for spring - mocks it and replaces that bean in
	// the spring context
	@MockBean
	private EstablishmentRepository establishmentRepo;

	@Mock
	private Establishment establishment;

	@Test
	public void shouldNavigateToAllEstablishments() throws Exception {
		// singleton creates a set with one element 
//		when(establishmentRepo.findAll()).thenReturn(Collections.singleton(establishment));
		// perform a fake request for this url - good way to test w/out starting server
		mvc.perform(get("/establishments")).andExpect(status().isOk());
	}

}
