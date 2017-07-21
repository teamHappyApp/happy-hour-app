package com.teamhappyapp.happyhourapp;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {

	Set<Establishment> findByScheduleStartTimeLessThanAndScheduleEndTimeGreaterThanEqual(int windowBegin, int windowEnd);
	
}
