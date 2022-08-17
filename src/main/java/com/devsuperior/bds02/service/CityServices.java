package com.devsuperior.bds02.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.Repositories.CityRepositories;
import com.devsuperior.bds02.service.exceptions.DataBaseException;
import com.devsuperior.bds02.service.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;

@Service
public class CityServices {

	@Autowired
	CityRepositories repository;

	@Transactional
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		List<CityDTO> response = list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
		;
		return response;
	}

	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		city.setName(cityDTO.getName());
		city = repository.save(city);
		return new CityDTO(city);
	}


	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found!");
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}	
	}
}
