package com.person.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.person.entities.Person;
import com.person.services.PersonService;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	@Test
	void shoulReturnPersonById() throws Exception {
		
		Person person  = new Person(2L, "EStefani", "Lozano Velasco", 27, "Velasco@gmail.com", "");
		Mockito.when(personService.findById(2L)).thenReturn(Optional.of(person));
		
        mockMvc.perform(get("/api/person/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.names").value("EStefani"))
        .andExpect(jsonPath("$.lastName").value("Lozano Velasco"));
			
	}
	
}
