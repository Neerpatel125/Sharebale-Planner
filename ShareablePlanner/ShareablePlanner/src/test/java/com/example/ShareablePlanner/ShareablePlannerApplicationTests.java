package com.example.ShareablePlanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.ShareablePlanner.controller.PersonController;
import com.example.ShareablePlanner.domain.Person;
import com.example.ShareablePlanner.model.PersonModelAssembler;
import com.example.ShareablePlanner.repository.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShareablePlannerApplicationTests {
	@InjectMocks
	PersonController personController;
	@Mock
	PersonModelAssembler assembler;
	@Mock
	PersonRepository personRepository;
	@Mock
	EntityModel<Person> entityModel;
	@Mock
	Link link;
	
	@Test
	void personGetSetUserName() {
		Person person1 = new Person();
		person1.setUserName("Daniel");
		assertEquals("Daniel", person1.getUserName());
	}
	
	@Test
	void personGetSetEmail() {
		Person person1 = new Person();
		person1.setEmail("dtran32@gmu.edu");
		assertEquals("dtran32@gmu.edu", person1.getEmail());
	}
	
	@Test
	void personGetSetPassword() {
		Person person1 = new Person();
		person1.setPassword("testing1234");
		assertEquals("testing1234", person1.getPassword());
	}
	
	@Test
	void personGetSetId() {
		Person person1 = new Person();
		person1.setId((long) 5);
		assertEquals(5, person1.getId());
	}
	
	@Test
	void personControllerTest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Person person1 = new Person("Daniel", "dan@gmu.edu", "test123");
		person1.setId((long)1);
		
		personController = new PersonController(personRepository , assembler);
		//when(employeeRepository.save(employee1)).thenReturn(employee1);
		entityModel = EntityModel.of(person1, //
		        linkTo(methodOn(PersonController.class).one(person1.getId())).withSelfRel(),
		        linkTo(methodOn(PersonController.class).all()).withRel("employees"));
		
		when(assembler.toModel(personRepository.save(person1))).thenReturn(entityModel);
		ResponseEntity<Object> responseEntity =  personController.newPerson(person1);
		
		assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
	}

	@Test
	void contextLoads() {
	}

}
