package com.teamhappyapp.happyhourapp;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EstablishmentDbRestController.class)
public class EstablishmentDbRestControllerTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private EstablishmentRepository establishmentRepo;
	
	@Mock
	private Iterable<Establishment> establishments;
	
	@Test
	public void shouldRenderSingleEstablishment() throws Exception {
		mvc.perform(get("/establishmentDatabase")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.jsonPath("$.length", is(greaterThan(0))));      
	}

	
}
