package com.person.services;

import java.util.List;
import java.util.Optional;

import com.person.entities.Person;

public interface PersonService {

	Optional<Person> save(Person person);
	
	Optional<Person> findById(Long id);
	
	List<Person> findAll();
	
	boolean deleteById(Long id);
	
	
	
}
