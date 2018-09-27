	package com.springboot.basics;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.basics.entity.Person;
import com.springboot.basics.jdbc.PersonJdbcDAO;
import com.springboot.basics.jpa.PersonJPARepo;

@SpringBootApplication
public class SpringJpah2Application implements CommandLineRunner {
	
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJdbcDAO dao;
	
	@Autowired
	PersonJPARepo personRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpah2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", dao.findAll());
		logger.info("User id 10001 -> {}" , dao.findById(10001));
		logger.info("JPA find -> {}", personRepo.findById(10001));
		
		logger.info("Inserting -> person ", dao.insertPerson(new Person(10004,"testINs", "test", new Date())));
		logger.info("deleted rows -> {}" , dao.deleteById(1));
		
		logger.info("JPA insert -> {}",personRepo.insert(new Person("Roman","Vargas",new Date())));
		logger.info("JPA update -> {}", personRepo.update(new Person(1,"Rom", "test", new Date()))); 
		logger.info("JPA findAll -> {}", personRepo.findAll());
		personRepo.delete(1);
	}
}
