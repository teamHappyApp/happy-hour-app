package com.teamhappyapp.happyhourapp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private Long scheduleId;
	
	private int startTime;

	private int endTime;
	
	@OneToMany(mappedBy = "schedule")
	private Set<Establishment> establishments;

	public Long getScheduleId() {
		return scheduleId;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public Schedule(int startTime, int endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	private Schedule(){};
}
