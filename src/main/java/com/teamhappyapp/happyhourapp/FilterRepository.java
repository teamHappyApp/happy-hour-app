package com.teamhappyapp.happyhourapp;

import org.springframework.data.repository.CrudRepository;

public interface FilterRepository extends CrudRepository<Filter, Long> {

	Filter findByNameIgnoreCase(String name);
}
