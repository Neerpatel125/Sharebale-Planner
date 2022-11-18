package com.example.ShareablePlanner.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.ShareablePlanner.controller.PersonController;
import com.example.ShareablePlanner.domain.Person;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>{

	@Override
	public EntityModel<Person> toModel(Person entity) {
		return EntityModel.of(entity, //
				linkTo(methodOn(PersonController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).all()).withRel("persons"));
	}

}
