package com.person.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.person.entities.Person;
import com.person.services.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersonService personService;
	
	public PersonController( PersonService personService) {
		 this.personService = personService;
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Person person) {
		
		Optional<Person> result = personService.save(person);
		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail process save record");	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
	    Optional<Person> result = personService.findById(id);

	    if (result.isPresent()) {
	        return ResponseEntity.ok(result.get());
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found record with id " + id);
	}
	
	@GetMapping()
	public ResponseEntity<?> findAll() {
		List<Person> result = personService.findAll();
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No found data");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		boolean isDelete = personService.deleteById(id);
		if (isDelete) {
			return ResponseEntity.ok("Registro eliminado con exito " +  id);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail process delete record with id " + id);
	}
	
}
