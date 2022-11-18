package com.example.ShareablePlanner.domain;

import java.sql.Date;
import java.sql.Time;
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
@Table(name = "SCHEDULE")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCHEDULE_ID")
	private long id;
	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	private Person personId;
	@Column(name = "SCHEDULE_DESCRIPTION")
	private String description;
	@Column(name = "YMD")
	private Date date;
	@Column(name = "TIME")
	private Time time;
	@Column(name = "SCHEDULE_NAME")
	private String name;
	
	public Schedule() {}
	
	public Schedule( Person personId, String description, Date date, Time time, String name) {
		this.personId = personId;
		this.description = description;
		this.date = date;
		this.time = time;
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Person getPersonId() {
		return this.personId;
	}
	
	public void setPersonId(Person personId) {
		this.personId = personId;
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
	    if (!(o instanceof Schedule))
	    	return false;
	    Schedule schedule = (Schedule) o;
	    return Objects.equals(this.id, schedule.id) && Objects.equals(this.personId, schedule.personId)
	    		&& Objects.equals(this.date, schedule.date) && Objects.equals(this.description, schedule.description) && Objects.equals(this.time, schedule.time)  && Objects.equals(this.name, schedule.name);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.description, this.personId, this.time, this.date);
	}
	
	@Override
	public String toString() {
	    return "Order{" + "id=" + this.id + '\'' + ", clientId='" + this.personId.getId() + '\'' +
	    		", description='" + this.description + '\''+ ", date='" + this.date + '\''+ ", time='" + this.time + '\''+ '}';
	}
}

