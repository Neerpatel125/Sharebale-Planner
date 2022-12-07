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

import com.example.ShareablePlanner.domain.Invites;
import com.example.ShareablePlanner.domain.Person;
import com.example.ShareablePlanner.domain.Schedule;
import com.example.ShareablePlanner.exception.InvitesNotFoundException;
import com.example.ShareablePlanner.exception.PersonNotFoundException;
import com.example.ShareablePlanner.model.InvitesModelAssembler;
import com.example.ShareablePlanner.model.PersonModelAssembler;
import com.example.ShareablePlanner.model.ScheduleModelAssembler;
import com.example.ShareablePlanner.repository.InvitesRepository;
import com.example.ShareablePlanner.repository.PersonRepository;
import com.example.ShareablePlanner.repository.ScheduleRepository;

@RestController
public class InvitesController {
	private final InvitesRepository inviteRepository;
	
	private final InvitesModelAssembler inviteAssembler;
	
	private final PersonRepository personRepository;
	
	private final PersonModelAssembler personAssembler;
	
	private final ScheduleRepository scheduleRepository;
	
	private final ScheduleModelAssembler scheduleAssembler;
	
	
	public InvitesController(InvitesRepository inviteRepository, InvitesModelAssembler inviteAssembler, PersonRepository personRepository, PersonModelAssembler personAssembler, ScheduleRepository scheduleRepository, ScheduleModelAssembler scheduleAssembler) {
		this.inviteRepository = inviteRepository;
		this.inviteAssembler = inviteAssembler;
		this.personAssembler = personAssembler;
		this.personRepository = personRepository;
		this.scheduleRepository = scheduleRepository;
		this.scheduleAssembler = scheduleAssembler;
	}
	
	@GetMapping("/invites")
	public CollectionModel<EntityModel<Invites>> all(){
		List<EntityModel<Invites>> Invites = inviteRepository.findAll().stream()
				.map(inviteAssembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(Invites, linkTo(methodOn(PersonController.class).all()).withSelfRel());
	}
	
	@PostMapping("/invites")
	public ResponseEntity<Object> newInvites(@RequestBody Invites newInvites){
		EntityModel<Invites> entityModel = inviteAssembler.toModel(inviteRepository.save(newInvites));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@GetMapping("/invites/{id}")
	public EntityModel<Invites> one(@PathVariable Long id){
		Invites invites = inviteRepository.findById(id)
				.orElseThrow(() -> new InvitesNotFoundException(id));
		return inviteAssembler.toModel(invites);
	}
	
	@GetMapping("/invites/invitee/{inviteeId}")
	public List<Invites> allInviteeId(@PathVariable Person inviteeId){
		List<Invites> invites = inviteRepository.findByInvitee(inviteeId);
		return invites;
	}
	
	@GetMapping("/invites/inviteeAndSchedule/{inviteeId}/{scheduleId}")
	public Invites oneInviteeIdAndScheduleId(@PathVariable Person inviteeId, @PathVariable Schedule scheduleId){
		Invites invites = inviteRepository.findByInviteeAndSchedule(inviteeId, scheduleId);
		return invites;
	}
	
	@GetMapping("/invites/schedule/{scheduleId}")
	public List<Invites> allScheduleId(@PathVariable Schedule scheduleId){
		List<Invites> invites = inviteRepository.findBySchedule(scheduleId);
		return invites;
	}
	
	@PutMapping("/invites/{id}")
	public ResponseEntity<Object> replaceInvites(@RequestBody Invites newInvites, @PathVariable Long id){
		Invites updatedInvites = inviteRepository.findById(id)
				.map(invites -> {
					invites.setInviter(newInvites.getInviter());
					invites.setInvitee(newInvites.getInvitee());
					invites.setSchedule(newInvites.getSchedule());
					return inviteRepository.save(invites);
				})
				.orElseGet(() -> {
					newInvites.setId(id);
					return inviteRepository.save(newInvites);
				});
		EntityModel<Invites> entityModel = inviteAssembler.toModel(updatedInvites);
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	@DeleteMapping("/invites/{id}")
	public ResponseEntity<Object> deleteInvites(@PathVariable Long id) {
		inviteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

