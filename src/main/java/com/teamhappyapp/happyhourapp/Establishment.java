package com.teamhappyapp.happyhourapp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Establishment {

	@GeneratedValue
	@Id
	private Long id; // bc null

	
	@ManyToMany
	private Set<Filter> filters;
	
	
	private String name;

	private String address;

	private String latitude;
	
	private String longitude;
	
	private String phoneNumber;

	@Embedded
	private Schedule schedule;



	public Long getId() {
		return id;
	}
	
	public Set<Filter> getFilters() {
		return filters;
	}


	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Schedule getSchedule() {
		return schedule;
	}
	
	
	public Establishment(String name, String address, String latitude, String longitude, String phoneNumber, Schedule schedule) {
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phoneNumber = phoneNumber;
		this.schedule = schedule;
	}
	
	public Establishment(String name, String address, String latitude, String longitude, String phoneNumber, Schedule schedule, Filter...filters) {
		this(name, address, latitude, longitude, phoneNumber, schedule);
		this.filters = new HashSet<>(Arrays.asList(filters));
	}

	// for jpa
	private Establishment() {
	}

}
