package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.exception.RoomException;
import com.manageYourHotel.model.dto.BuildingDto;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.FloorDto;
import com.manageYourHotel.model.entity.Building;
import com.manageYourHotel.model.entity.Floor;
import com.manageYourHotel.model.entity.Room;
import com.manageYourHotel.repo.BuildingRepository;

@Service
public class BuildingService 
{
	// Repostory
	@Autowired
	private BuildingRepository buildingRepo;
	
	// Converter
	@Autowired
	private DtoConverter converter;
	
	// Get all buildings
	public List<BuildingDto> getBuildings() throws BuildingException
	{
		List<Building> buildings = (List<Building>) buildingRepo.findAll();
		if(buildings.size() == 0)
		{
			throw new BuildingException(("There are no buildings"));
		}
		List<BuildingDto> dtos = new ArrayList<BuildingDto>();
		for(Building building : buildings)
		{
			BuildingDto dto = converter.fromBuildingToBuildingDto(building); 
			dtos.add(dto);
		}
		return dtos;
	}
	
	// Get the floors of a building knowing its name
	public List<FloorDto> getFloors(String name) throws BuildingException, FloorException
	{
		Building building = buildingRepo.findBuildingByName(name);
		if(building == null)
		{
			throw new BuildingException("Building doesn't exist");
		}
		List<Floor> floors = building.getFloors();
		if(floors.size() == 0)
		{
			throw new FloorException("There are no floors");
		}
		List<FloorDto> dtos = new ArrayList<FloorDto>();
		for(Floor floor : floors)
		{
			dtos.add(converter.fromFloorToFloorDto(floor));
		}
		return dtos; 
	}
	
	// Create a new building
	public BuildingDto newBuilding(BuildingDto dto) throws BuildingException
	{
		if(dto.getName().contains(" "))
		{
			throw new BuildingException("Name can't contain spaces");
		}
		Building building = buildingRepo.findBuildingByName(dto.getName());
		if (building != null)
		{
			throw new BuildingException("Name already exists");
		}
		buildingRepo.save(converter.fromBuildingDtoToBuilding(dto));
		return dto;
	}
	
}
