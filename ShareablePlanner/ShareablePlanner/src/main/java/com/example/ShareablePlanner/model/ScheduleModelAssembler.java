package com.example.ShareablePlanner.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.ShareablePlanner.controller.ScheduleController;
import com.example.ShareablePlanner.domain.Schedule;

@Component
public class ScheduleModelAssembler implements RepresentationModelAssembler<Schedule, EntityModel<Schedule>>{
	@Override
	public EntityModel<Schedule> toModel(Schedule entity){
		return EntityModel.of(entity,
				linkTo(methodOn(ScheduleController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(ScheduleController.class).all()).withRel("Schedules"));
	}

}