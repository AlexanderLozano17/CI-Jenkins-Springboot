package com.person.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
			return ResponseEntity.ok(response("SUCCESS", true, result.get()));
		}
		return ResponseEntity.ok(response("ERROR", false, null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> finById(@PathVariable Long id) {
		Optional<Person> result = personService.finById(id);
		if (result.isPresent()) {
			return ResponseEntity.ok(response("SUCCESS", true, result.get()));
		}
		return ResponseEntity.ok(response("ERROR", false, null));
	}
	
	@GetMapping()
	public ResponseEntity<?> findAll() {
		List<Person> result = personService.findAll();
		if (!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.ok(response("ERROR", false, null));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(Long id) {
		boolean isDelete = personService.deleteById(id);
		if (isDelete) {
			return ResponseEntity.ok(response("SUCCESS", true, null));
		}
		return ResponseEntity.ok(response("ERROR", false, null));
	}
	
	private Map<String, String> response(String status, boolean message, Person person) {
		Map<String, String> map = new HashMap<>();
		map.put("status", status);
		map.put("message", message ? "Ejecución exitosa" : "Fallo la Ejecución");
		map.put("data", person != null ? person.toString() : "");
		return map;
	}
	
}
