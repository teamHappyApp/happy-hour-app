package com.teamhappyapp.happyhourapp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Filter {

	@GeneratedValue
	@Id
	private Long id;

	private String name;
	
	private String displayName;

	@JsonIgnore
	@ManyToMany(mappedBy = "filters")
	private Set<Establishment> establishments;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Set<Establishment> getEstablishments() {
		return establishments;
	}

	private Filter() {
	}

	public Filter(String name) {
		this.name = name;
	}

}
