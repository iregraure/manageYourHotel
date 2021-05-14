package com.manageYourHotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.exception.PersonException;
import com.manageYourHotel.exception.RoomException;
import com.manageYourHotel.exception.RoomStateException;
import com.manageYourHotel.model.dto.RoomDto;
import com.manageYourHotel.model.dto.StayDto;
import com.manageYourHotel.service.RoomService;
import com.manageYourHotel.service.UpdateService;

@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {

	// Services
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private UpdateService updateService;
	
	// Get all the rooms of the building
	@GetMapping("/{buildingName}/rooms")
	public ResponseEntity<?> getRoomsBuilding(@PathVariable String buildingName)
	{
		ResponseEntity<?> response;
		try
		{
			List<RoomDto> rooms = roomService.getRoomsDtos(buildingName);
			response = ResponseEntity.ok(rooms);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Get a room
	@GetMapping("/{buildingName}/{roomNumber}")
	public ResponseEntity<?> getRoom(@PathVariable String buildingName, @PathVariable int roomNumber)
	{
		ResponseEntity<?> response;
		try
		{
			RoomDto room = roomService.getRoom(buildingName, roomNumber);
			response = ResponseEntity.ok(room);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Create a room
	@PostMapping()
	public ResponseEntity<?> addRoom(RoomDto dto)
	{
		ResponseEntity<?> response;
		try
		{
			RoomDto room = roomService.newRoom(dto);
			response = ResponseEntity.status(HttpStatus.CREATED).body(room);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(fe.getMessage());
		}
		catch(RoomStateException rse)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rse.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Update a room
	@PutMapping("/{buildingName}")
	public ResponseEntity<?> updateRoom(RoomDto dto)
	{
		ResponseEntity<?> response;
		try
		{
			RoomDto room = roomService.newRoom(dto);
			response = ResponseEntity.ok(room);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(fe.getMessage());
		}
		catch(RoomStateException rse)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rse.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Change state
	@PutMapping("/{buildingName}/{roomNumber}")
	public ResponseEntity<?> changeState(String state, @PathVariable String buildingName, @PathVariable int roomNumber)
	{
		ResponseEntity<?> response;
		try
		{
			RoomDto room = roomService.changeState(buildingName, roomNumber, state);
			response = ResponseEntity.ok(room);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(fe.getMessage());
		}
		catch(RoomStateException rse)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rse.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
	
	// Add a stay
	@PostMapping("/room/{buildingName}/addStay")
	public ResponseEntity<?> addStay(StayDto dto, @PathVariable String buildingName)
	{
		ResponseEntity<?> response;
		try
		{
			StayDto stay = roomService.addStay(dto, buildingName);
			response = ResponseEntity.ok(stay);
		}
		catch(BuildingException be)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(be.getMessage());
		}
		catch(FloorException fe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(fe.getMessage());
		}
		catch(RoomStateException rse)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(rse.getMessage());
		}
		catch(RoomException re)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
		}
		catch(PersonException pe)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(pe.getMessage());
		}
		catch(ClientException ce)
		{
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
		}
		catch(Exception e)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
		}
		return response;
	}
}
