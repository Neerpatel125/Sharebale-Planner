package com.example.ShareablePlanner.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.ShareablePlanner.controller.InvitesController;
import com.example.ShareablePlanner.domain.Invites;

@Component
public class InvitesModelAssembler implements RepresentationModelAssembler<Invites, EntityModel<Invites>>{

	@Override
	public EntityModel<Invites> toModel(Invites entity) {
		return EntityModel.of(entity, //
				linkTo(methodOn(InvitesController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(InvitesController.class).all()).withRel("persons"));
	}

}