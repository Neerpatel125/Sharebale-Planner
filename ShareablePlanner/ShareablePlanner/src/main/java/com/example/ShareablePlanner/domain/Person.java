package com.example.ShareablePlanner.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PERSON")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_ID")
	private Long id;
	@Column(name = "PERSON_USER_NAME")
	private String userName;
	@Column(name = "PERSON_EMAIL")
	private String email;
	@Column(name = "PERSON_PASSWORD")
	private String password;
	//@Column(name = "POSITION_ID")
	//private String role;

	
	public Person() {}
	
	public Person(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	    return Objects.equals(this.id, employee.id) && Objects.equals(this.userName, employee.userName) && Objects.equals(this.password, employee.password);
	}
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.userName, this.password, this.email);
	}
	
	@Override
	public String toString() {
	    return "Order{" + "id=" + this.id + '\'' + ", firstName='" + this.userName
	    		+ '\'' + ", password" + this.password + '\'' + ", email=" + this.email 
	    		+ '\'' + '}';
	}
}
