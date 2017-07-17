package com.teamhappyapp.happyhourapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Establishment {

	@GeneratedValue
	@Id
	private Long establishmentId; // bc null

	private String name; 

	public Long getEstablishmentId() {
		return establishmentId;
	}

	public String getName() {
		return name;
	}

	public Establishment(String name) {
		this.name = name;
	}

	// for jpa
	private Establishment() {
	}

}
