package com.devsuperior.bds02.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.bds02.entities.Event;

@Repository
public interface EventRepositories extends JpaRepository<Event, Long> {

	
	
}
