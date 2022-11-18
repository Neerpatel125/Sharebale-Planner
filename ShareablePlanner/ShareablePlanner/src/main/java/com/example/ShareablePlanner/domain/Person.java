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
@Table(name = "PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID")
	private Long id;
	@Column(name = "PERSON_FIRST_NAME")
	private String firstName;
	@Column(name = "PERSON_LAST_NAME")
	private String lastName;
	@Column(name = "PERSON_PHONE")
	private String phoneNumber;
	@Column(name = "PERSON_EMAIL")
	private String email;
	//@Column(name = "POSITION_ID")
	//private String role;

	
	public Person() {}
	
	public Person(String firstName, String lastName, String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public void setName(String name) {
		String[] str = name.split(" ");
		this.firstName = str[0];
		this.lastName = str[1];
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}
	
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
	    if (!(o instanceof Person))
	    	return false;
	    Person employee = (Person) o;
	    return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
	        && Objects.equals(this.lastName, employee.lastName);
	}
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.firstName, this.lastName, this.phoneNumber, this.email);
	}
	
	@Override
	public String toString() {
	    return "Order{" + "id=" + this.id + '\'' + ", firstName='" + this.firstName + '\'' + ", lastName=" + this.lastName 
	    		+ '\'' + ", phoneNumber" + this.phoneNumber + '\'' + ", email=" + this.email 
	    		+ '\'' + '}';
	}
}
