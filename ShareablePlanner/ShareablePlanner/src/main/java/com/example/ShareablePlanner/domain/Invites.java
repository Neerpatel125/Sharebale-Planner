package com.example.ShareablePlanner.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "INVITES")
public class Invites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INVITES_ID")
	private Long id;
	@OneToOne
	@JoinColumn(name = "INVITER_ID")
	private Person inviter;
	@OneToOne
	@JoinColumn(name = "INVITEE_ID")
	private Person invitee;
	@OneToOne
	@JoinColumn(name = "SCHEDULE_ID")
	private Schedule schedule;
	@OneToOne
	@JoinColumn(name = "STATUS_ID")
	private Status status;
	
	public Invites() {}
	
	public Invites(Person inviter, Person invitee, Schedule schedule, Status status) {
		this.inviter = inviter;
		this.invitee = invitee;
		this.schedule = schedule;
		this.status = status;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Person getInviter() {
		return this.inviter;
	}
	
	public void setInviter(Person inviter) {
		this.inviter = inviter;
	}
	
	public Person getInvitee() {
		return this.invitee;
	}
	
	public void setInvitee(Person invitee) {
		this.invitee = invitee;
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
	    if (!(o instanceof Person))
	    	return false;
	    Invites invites = (Invites) o;
	    return Objects.equals(this.id, invites.id) && Objects.equals(this.invitee, invites.invitee)
	        && Objects.equals(this.inviter, invites.inviter) && Objects.equals(this.schedule, invites.schedule) && Objects.equals(this.status, invites.status);
	}
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.inviter, this.invitee, this.schedule, this.status);
	}
	
	@Override
	public String toString() {
	    return "Invites{" + "id=" + this.id + '\'' + ", Inviter='" + this.inviter + '\'' + ", Invitee=" + this.invitee
	    		+ '\'' + ", schedule" + this.schedule + '\'' + ", status=" + this.status 
	    		+ '\'' + '}';
	}
}
