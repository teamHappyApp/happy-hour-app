package com.teamhappyapp.happyhourapp;

import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EstablishmentDbRestController.class)
public class EstablishmentDbRestControllerTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private EstablishmentRepository establishmentRepo;

	@Test
	public void shouldRenderSingleEstablishment() throws Exception {

		// using a real object instead of a mock because JSON serializer doesn't
		// understand mocks
		Schedule fourToSix = new Schedule(4, 6);
		Establishment establishment = new Establishment("test establishment", "foo", "bar", "baz", "867-5309", fourToSix);
		when(establishmentRepo.findAll()).thenReturn(singleton(establishment));

		mvc.perform(get("/establishmentDatabase")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("test establishment")));
	}

}
