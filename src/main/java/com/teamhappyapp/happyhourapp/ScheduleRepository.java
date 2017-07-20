package com.teamhappyapp.happyhourapp;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

	// will need a method to return schedules that include a time of interest
}
