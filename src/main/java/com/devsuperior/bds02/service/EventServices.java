package com.devsuperior.bds02.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.Repositories.CityRepositories;
import com.devsuperior.bds02.Repositories.EventRepositories;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;

@Service
public class EventServices {

	@Autowired
	private EventRepositories repo;
	
	@Autowired
	private CityRepositories cityRepo;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			
			City city = cityRepo.findById(dto.getCityId()).orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
			
			Event event = repo.getOne(id);
			event.setCity(city);
			event.setDate(dto.getDate());
			event.setName(dto.getName());
			event.setUrl(dto.getUrl());
			event = repo.save(event);
			return new EventDTO(event);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
		
	}
	
	
	
}
