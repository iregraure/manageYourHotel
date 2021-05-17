package com.manageYourHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.model.dto.BuildingDto;
import com.manageYourHotel.model.dto.FloorDto;
import com.manageYourHotel.service.BuildingService;

@CrossOrigin
@RestController
@RequestMapping("/building")
public class BuildingController 
{
	// Services
	@Autowired
	private BuildingService buildingService;
	
	// Get all buildings
	@GetMapping("/buildings")
	public ResponseEntity<?> getAllBuildings()
	{
		ResponseEntity<?> response;
		try
		{
			List<BuildingDto> buildings = buildingService.getBuildings();
			response = ResponseEntity.ok(buildings);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Get all the floors of a building
	@GetMapping("{buildingName}/floors")
	public ResponseEntity<?> getBuildingFloors(@PathVariable String buildingName)
	{
		ResponseEntity<?> response;
		try
		{
			List<FloorDto> floors = buildingService.getFloors(buildingName);
			response = ResponseEntity.ok(floors);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Create a building
	@PostMapping()
	public ResponseEntity<?> addBuilding(@RequestBody BuildingDto dto)
	{
		ResponseEntity<?> response;
		try
		{
			BuildingDto building = buildingService.newBuilding(dto);
			response = ResponseEntity.status(HttpStatus.CREATED).body(building);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(be.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
}
