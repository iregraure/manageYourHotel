package com.manageYourHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.model.dto.FloorDto;
import com.manageYourHotel.model.dto.RoomDto;
import com.manageYourHotel.service.FloorService;

@CrossOrigin
@RestController
@RequestMapping("/floor")
public class FloorController 
{
	// Services
	@Autowired
	private FloorService floorService;
	
	// Get all the rooms of a floor
	@GetMapping("/{buildingName}/{florNumber}/rooms")
	public ResponseEntity<?> getFloorRooms(@PathVariable String buildingName, int floorNumber)
	{
		ResponseEntity<?> response;
		try
		{
			List<RoomDto> rooms = floorService.getRooms(buildingName, floorNumber);
			response = ResponseEntity.ok(rooms);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(fe.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Create a floor
	@PostMapping()
	public ResponseEntity<?> addFloor(FloorDto dto)
	{
		ResponseEntity<?> response;
		try
		{
			FloorDto floor = floorService.newFloor(dto);
			response = ResponseEntity.status(HttpStatus.CREATED).body(floor);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(fe.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}

}
