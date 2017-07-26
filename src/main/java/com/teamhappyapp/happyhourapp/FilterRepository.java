package com.teamhappyapp.happyhourapp;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface FilterRepository extends CrudRepository<Filter, Long> {

	Filter findByNameIgnoreCase(String name);
	Set<Filter> findByNameIn(String... names);
}
