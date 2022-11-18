package com.example.ShareablePlanner.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATUS_ID")
	private long id;
	@Column(name = "STATUS_NAME")
	private String name;

	public Status() {}
	
	public Status(String name) {
		this.name = name;
	}
	
	public long getName() {
		return this.id;
	}
	
	public void setName(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
	    if (!(o instanceof Status))
	    	return false;
	    Status status = (Status) o;
	    return Objects.equals(this.id, status.id) && Objects.equals(this.name, status.name);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.name);
	}
	
	@Override
	public String toString() {
	    return "Status{" + "id=" + this.id + '\'' + ", name='" + this.name + '\'' + '}';
	}
}
