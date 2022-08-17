package com.devsuperior.bds02.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.service.CityServices;

@RestController
@RequestMapping(value="/cities")
public class CityController {
	
	@Autowired
	CityServices service;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> reponse = service.findAll();
		return ResponseEntity.ok().body(reponse);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert (@RequestBody CityDTO city) {
		CityDTO response = service.insert(city);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
		
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
