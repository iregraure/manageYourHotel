package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.FloorDto;
import com.manageYourHotel.model.dto.RoomDto;
import com.manageYourHotel.model.entity.Building;
import com.manageYourHotel.model.entity.Floor;
import com.manageYourHotel.model.entity.Room;
import com.manageYourHotel.repo.BuildingRepository;
import com.manageYourHotel.repo.FloorRepository;

@Service
public class FloorService 
{
	// Repository
	@Autowired
	private FloorRepository floorRepo;
	
	@Autowired
	private BuildingRepository buildingRepo;
	
	// Converter
	@Autowired
	private DtoConverter converter;
	
	// Get all the rooms in a floor
	public List<RoomDto> getRooms(String buildingName, int floorNumber) throws BuildingException, FloorException
	{
		// Check if the building exists
		Building building = buildingRepo.findBuildingByName(buildingName);
		if(building == null)
		{
			throw new BuildingException("Building doesn't exist");
		}
		// Check if the floor number exists
		List<Floor> floors = floorRepo.findFloorByNumber(floorNumber);
		if(floors.size() == 0)
		{
			throw new FloorException("There is no floor with that number");
		}
		List<Room> rooms = null;
		// Check if the floor exists in the building
		for(Floor f : floors)
		{
			if(f.getBuilding() == building)
			{
				rooms = f.getRooms();
			}
		}
		if (rooms == null)
		{
			throw new FloorException("The floor doesn't exist in the building");
		}
		// Convert the rooms to roomsDto
		List<RoomDto> dtos = new ArrayList<RoomDto>();
		for(Room room : rooms)
		{
			dtos.add(converter.fromRoomToRoomDto(room));
		}
		return dtos;
	}
	
	// Create a new floor
	public FloorDto newFloor(FloorDto dto) throws BuildingException, FloorException
	{
		Building building = buildingRepo.findBuildingByName(dto.getBuildingName());
		if (building == null)
		{
			throw new BuildingException("Building doesn't exist");
		}
		List<Floor> floors = floorRepo.findFloorByNumber(dto.getNumber());
		if(floors.size() != 0)
		{
			for(Floor f : floors)
			{
				if(f.getBuilding() == building)
				{
					throw new FloorException("Floor number already exists");
				}
			}			
		}
		floorRepo.save(converter.fromFloorDtoToFloor(dto));
		building.addFloor(converter.fromFloorDtoToFloor(dto));
		buildingRepo.save(building);
		return dto;
	}

}
