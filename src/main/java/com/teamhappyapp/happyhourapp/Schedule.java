package com.teamhappyapp.happyhourapp;

import javax.persistence.Embeddable;

@Embeddable
public class Schedule {
	
	private int startTime;

	private int endTime;

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public Schedule(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	private Schedule(){};
	
	@Override
	public String toString() {
		return "Start time: " + startTime + " End time:" + endTime;
	}
}
