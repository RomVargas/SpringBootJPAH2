package com.springboot.basics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.basics.entity.Person;
import com.springboot.basics.jpa.PersonJPARepo;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	PersonJPARepo personRepo;

	@GetMapping("/findall")
	public List<Person> getAllPersons(){
		return personRepo.findAll();
	}
	
	@GetMapping("/getbyid/{id}")
	public Person getById(@PathVariable int id) {
		return personRepo.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable int id) {
		personRepo.delete(id);
	}
	
	@PostMapping("/create")
	public void createPerson(@RequestBody Person person) {
		personRepo.insert(person);
	}
	
	@PostMapping("/update")
	public void updatePerson(@RequestBody Person person) {
		personRepo.update(person);
	}
	
	public String greeting() {
		return "Hola Mundo";
	}
}
