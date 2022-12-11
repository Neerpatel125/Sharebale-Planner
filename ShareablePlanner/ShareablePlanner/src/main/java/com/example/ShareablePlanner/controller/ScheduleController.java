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
import com.example.ShareablePlanner.domain.Schedule;
import com.example.ShareablePlanner.exception.PersonNotFoundException;
import com.example.ShareablePlanner.exception.ScheduleNotFoundException;
import com.example.ShareablePlanner.model.ScheduleModelAssembler;
import com.example.ShareablePlanner.repository.PersonRepository;
import com.example.ShareablePlanner.repository.ScheduleRepository;

@RestController
public class ScheduleController {
	
	private final ScheduleRepository scheduleRepository;
	private final ScheduleModelAssembler scheduleAssembler;
	private final PersonRepository personRepository;
	
	public ScheduleController(ScheduleRepository scheduleRepository, ScheduleModelAssembler scheduleAssembler, PersonRepository personRepository) {
		this.scheduleAssembler = scheduleAssembler;
		this.scheduleRepository = scheduleRepository;
		this.personRepository = personRepository;
	}
	
	@GetMapping("/schedules")
	public CollectionModel<EntityModel<Schedule>> all(){
		List<EntityModel<Schedule>> availability = scheduleRepository.findAll().stream()
				.map(scheduleAssembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(availability, linkTo(methodOn(ScheduleController.class).all()).withSelfRel());
	}
	//used
	@PostMapping("/schedules")
	public ResponseEntity<Object> newSchedule(@RequestBody Schedule newSchedule){

		Person person = personRepository.findById(newSchedule.getPersonId().getId()).get();
		if(person == null) {
			throw new PersonNotFoundException(newSchedule.getPersonId().getId());
		}
		else {
			newSchedule.setPersonId(person);
		}
		
		EntityModel<Schedule> entityModel = scheduleAssembler.toModel(scheduleRepository.save(newSchedule));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@GetMapping("/schedules/{id}")
	public EntityModel<Schedule> one(@PathVariable Long id){
		Schedule schedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new ScheduleNotFoundException(id));
		return scheduleAssembler.toModel(schedule);
	}
	//used
	@GetMapping("/schedules/{ymd}/{personId}")
	public List<Schedule> allYMDpersonId(@PathVariable String ymd, @PathVariable Person personId){
		List<Schedule> schedule = scheduleRepository.findByDateAndPersonId(ymd, personId);
		return schedule;
	}
	//used
	@GetMapping("/schedules/{personId}/{date}/{time}/{name}")
	public Schedule onePersonIdDateTimeName(@PathVariable String date, @PathVariable Person personId, @PathVariable String time, @PathVariable String name){
		Schedule schedule = scheduleRepository.findByPersonIdAndDateAndTimeAndName(personId, date, time, name);
		return schedule;
	}
	
//	@PutMapping("/schedules/{id}")
//	public ResponseEntity<Object> replaceSchedule(@RequestBody Schedule newSchedule, @PathVariable Long id){
//		Person person = personRepository.findById(newSchedule.getPersonId().getId()).get();
//		if(person == null) {
//			throw new PersonNotFoundException(newSchedule.getPersonId().getId());
//		}
//		else {
//			newSchedule.setPersonId(person);
//		}
//
//		Schedule updatedSchedule = scheduleRepository.findById(id)
//				.map(schedule -> {
//					schedule.setPersonId(newSchedule.getPersonId());
//					schedule.setDescription(newSchedule.getDescription());
//					schedule.setName(newSchedule.getName());
//					schedule.setDate(newSchedule.getDate());
//					schedule.setTime(newSchedule.getTime());
//					return scheduleRepository.save(schedule);
//				})
//				.orElseGet(() -> {
//					return scheduleRepository.save(newSchedule);
//				});
//		EntityModel<Schedule> entityModel = scheduleAssembler.toModel(updatedSchedule);
//		return ResponseEntity
//				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//				.body(entityModel);
//	}
	//used
	@DeleteMapping("/schedules/{id}")
	public ResponseEntity<Object> deleteSchedule(@PathVariable Long id){
		scheduleRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
