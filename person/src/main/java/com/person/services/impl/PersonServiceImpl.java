package com.person.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.person.entities.Person;
import com.person.repository.PersonRepository;
import com.person.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository personRepository;
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Optional<Person> save(Person person) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(personRepository.save(person));
	}

	@Override
	public Optional<Person> findById(Long id) {
		// TODO Auto-generated method stub	
		return personRepository.findById(id);
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		personRepository.deleteById(id);
		return true;
	}

}
