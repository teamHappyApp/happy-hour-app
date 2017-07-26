package com.teamhappyapp.happyhourapp;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface EstablishmentRepository extends CrudRepository<Establishment, Long> {

	Set<Establishment> findByScheduleStartTimeLessThanEqualAndScheduleEndTimeGreaterThanEqual(int windowBegin, int windowEnd);
	Set<Establishment> findByFiltersName(String[] name);
}
