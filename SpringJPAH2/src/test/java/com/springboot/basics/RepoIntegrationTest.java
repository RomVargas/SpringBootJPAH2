package com.springboot.basics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import com.springboot.basics.entity.Person;
import com.springboot.basics.jpa.PersonJPARepo;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoIntegrationTest {
	
	@Autowired
	PersonJPARepo personRepo;
	
	 @Test
    public void testFindAll() {
           List<Person> persons = personRepo.findAll();
           Person test = new Person(10001,"Roman","Mexico", new Date());
           assertThat(persons).isNotNull().isNotEmpty();
           assertThat(persons.get(0)).isNotNull().describedAs("Roman", test.getName());
           assertThat(persons.get(0)).isNotNull().describedAs("Mexico", test.getLocation());
           assertThat(persons.get(0)).isNotNull().describedAs("10001", test.getId());
    }
	 
	 @Test
    public void testUpdate() {
	 	Person person = new Person(10004,"Roman","test", new Date());
           Person persons = personRepo.update(person);
           assertThat(persons).isNotNull().hasFieldOrProperty("name");
    }
	 
	 @Test
	 public void testInsert() {
		 	Person person = new Person("Roman","test", new Date());
	           Person persons = personRepo.insert(person);
	           assertThat(persons).isNotNull().hasFieldOrProperty("id");
	           assertThat(persons).isNotNull().hasFieldOrProperty("name");
	           assertThat(persons).isNotNull().hasFieldOrProperty("location");
	    }
	 
	 @Test 
	 public void testfindById() {
		 
		 Person personId = personRepo.findById(10003);
		 
		 assertThat(personId).isNotNull().describedAs("Juan", personId.getName());
		 assertThat(personId).isNotNull().describedAs("Amsterdam", personId.getLocation());
		 assertThat(personId).isNotNull().describedAs("10003", personId.getId());
	 }
	 
	 @Test
	 public void testDeleteById() {
		 
		 personRepo.delete(10003);
		 Person personTest = personRepo.findById(10003);
		 assertNull(personTest);
	 }
	 
	 

}
