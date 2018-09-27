package com.springboot.basics.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.springboot.basics.entity.Person;

@Repository
@Transactional
public class PersonJPARepo {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAll(){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons",Person.class);
		return namedQuery.getResultList();
				
	}
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}
	
	public Person insert(Person person){
		return entityManager.merge(person);
	}
	
	public Person update(Person person){
		return entityManager.merge(person);
	}
	
	public void delete(int id) {
		Person person = findById(id);
		entityManager.remove(person);
	}
}
