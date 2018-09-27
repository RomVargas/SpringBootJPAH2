package com.springboot.basics.jdbc;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.basics.entity.Person;

@Repository
public class PersonJdbcDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//select from * person
	public List<Person> findAll(){
		System.out.println("ejecutando");
		return jdbcTemplate.query("select * from person",
				new BeanPropertyRowMapper<>(Person.class));
	}
	
	public Person findById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id=?",
				new Object[] {id},new BeanPropertyRowMapper<>(Person.class));
	}
	
	public void updateById(Person person){
		//jdbcTemplate.query("update person where id=?", rse)
	}
	
	public int deleteById(int id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] {id});
	}
	
	public int insertPerson(Person person) {
		return jdbcTemplate.update("insert into person (id,name,location,birth_date)"
				+ "values (?,?,?,?)",new Object[] {person.getId(),person.getName(),person.getLocation(),
						new Timestamp(person.getBirth_date().getTime())});
	}
	
	public int updatePerson(Person person) {
		return jdbcTemplate.update("update person name=?, location=?, birth_date=? "			
				+ "where id = ?",new Object[] {person.getName(),person.getLocation(),
						new Timestamp(person.getBirth_date().getTime()),person.getId()});
	}

}
