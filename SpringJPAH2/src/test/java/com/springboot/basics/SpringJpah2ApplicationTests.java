package com.springboot.basics;



import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.basics.entity.Person;
import com.springboot.basics.jpa.PersonJPARepo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringJpah2Application.class)
public class SpringJpah2ApplicationTests {
	
	@Autowired
	PersonJPARepo personRepo;

	@Test
	public void contextLoads() {
	
	}
	
	@Test
	public void testFindById(){
		Person person = personRepo.findById(10001);
		assertEquals("Roman", person.getName());
		assertEquals(10001,person.getId());
		assertEquals("Mexico", person.getLocation());
	}
	
	@Test
	public void testFindAll() {
		
		List<Person> persons = personRepo.findAll();
		assertEquals(4, persons.size());
		assertEquals("Roman",persons.get(0).getName());
		assertEquals("James",persons.get(1).getName());
		assertEquals("Juan",persons.get(2).getName());
		assertEquals("testINs", persons.get(3).getName());
		assertEquals("Mexico",persons.get(0).getLocation());
		assertEquals("New York",persons.get(1).getLocation());
		assertEquals("Amsterdam",persons.get(2).getLocation());
		assertEquals("test",persons.get(3).getLocation());
	}
	
	@Test
	public void testUpdatePerson() {
		Person person = new Person(10004, "Roman Test", "mex", new Date());
		personRepo.update(person);
		Person test = personRepo.findById(10004);
				
		assertEquals("Roman Test", test.getName());
		assertEquals("mex", test.getLocation());
		
	}
	
	@Test
	public void testDeleteById() {
		personRepo.delete(10004);
		assertNull(personRepo.findById(10004));
	}
	
	

}
