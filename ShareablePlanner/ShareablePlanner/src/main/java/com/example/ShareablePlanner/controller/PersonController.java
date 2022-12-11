package com.example.ShareablePlanner.controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShareablePlanner.domain.Person;
import com.example.ShareablePlanner.exception.PersonNotFoundException;
import com.example.ShareablePlanner.model.PersonModelAssembler;
import com.example.ShareablePlanner.repository.PersonRepository;

@RestController
public class PersonController {
	
	private final PersonRepository personRepository;
	
	private final PersonModelAssembler personAssembler;
	
	
	public PersonController(PersonRepository personRepository, PersonModelAssembler personAssembler) {
		this.personAssembler = personAssembler;
		this.personRepository = personRepository;
	}
	
	@GetMapping("/persons")
	public CollectionModel<EntityModel<Person>> all(){
		List<EntityModel<Person>> persons = personRepository.findAll().stream()
				.map(personAssembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).all()).withSelfRel());
	}
	//used
	@PostMapping("/persons")
	public ResponseEntity<Object> newPerson(@RequestBody Person newPerson){
		EntityModel<Person> entityModel = personAssembler.toModel(personRepository.save(newPerson));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	//used
	@GetMapping("/persons/id/{id}")
	public EntityModel<Person> one(@PathVariable Long id){
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
		return personAssembler.toModel(person);
	}
	//used
	@GetMapping("/persons/userName/{userName}")
	public List<Person> allUserName(@PathVariable String userName){
		List<Person> persons = personRepository.findByUserName(userName);
		return persons;
	}
	
//	@GetMapping("/persons/password/{password}")
//	public List<Person> allPassword(@PathVariable String password){
//		List<Person> persons = personRepository.findByPassword(password);
//		return persons;
//	}
	
//	@PutMapping("/persons/{id}")
//	public ResponseEntity<Object> replacePerson(@RequestBody Person newPerson, @PathVariable Long id){
//		Person updatedPerson = personRepository.findById(id)
//				.map(person -> {
//					person.setUserName(newPerson.getUserName());
//					person.setPassword(newPerson.getPassword());
//					return personRepository.save(person);
//				})
//				.orElseGet(() -> {
//					newPerson.setId(id);
//					return personRepository.save(newPerson);
//				});
//		EntityModel<Person> entityModel = personAssembler.toModel(updatedPerson);
//		return ResponseEntity
//				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//				.body(entityModel);
//	}
//	
//	@DeleteMapping("/persons/{id}")
//	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
//		personRepository.deleteById(id);
//		return ResponseEntity.noContent().build();
//	}
}
