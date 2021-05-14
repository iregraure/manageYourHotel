package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.exception.PersonException;
import com.manageYourHotel.exception.RoomException;
import com.manageYourHotel.exception.RoomStateException;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.RoomDto;
import com.manageYourHotel.model.dto.StayDto;
import com.manageYourHotel.model.entity.Building;
import com.manageYourHotel.model.entity.Floor;
import com.manageYourHotel.model.entity.Room;
import com.manageYourHotel.model.entity.RoomState;
import com.manageYourHotel.model.entity.Stay;
import com.manageYourHotel.repo.BuildingRepository;
import com.manageYourHotel.repo.FloorRepository;
import com.manageYourHotel.repo.RoomRepository;
import com.manageYourHotel.repo.RoomStateRepository;
import com.manageYourHotel.repo.StayRepository;

@Service
public class RoomService {

	// Repository
	@Autowired
	private BuildingRepository buildingRepo;
	
	@Autowired
	private FloorRepository floorRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private RoomStateRepository roomStateRepo;
	
	@Autowired
	private StayRepository stayRepo;
	
	// Services
	@Autowired
	private UpdateService updateService;
	
	// Converter
	@Autowired
	private DtoConverter converter;
	
	// Get all the roomsDtos of the building
		public List<RoomDto> getRoomsDtos(String name) throws BuildingException, RoomException {
		Building building = buildingRepo.findBuildingByName(name);
		if (building == null) {
			throw new BuildingException("There is no building with that name");
		}
		List<Room> rooms = new ArrayList<Room>();
		for (Floor floor : building.getFloors()) {
			for (Room room : floor.getRooms()) {
				rooms.add(room);
			}
		}
		if(rooms.size() == 0)
		{
			throw new RoomException("There are no rooms");
		}
		List<RoomDto> dtos = new ArrayList<RoomDto>();
		for(Room r : rooms)
		{
			dtos.add(converter.fromRoomToRoomDto(r));
		}
		return dtos;
	}
	
	// Get all the roomsDtos of the building
	public List<Room> getRooms(String name) throws BuildingException, RoomException {
		Building building = buildingRepo.findBuildingByName(name);
		if (building == null) {
			throw new BuildingException("There is no building with that name");
		}
		List<Room> rooms = new ArrayList<Room>();
		for (Floor floor : building.getFloors()) {
			for (Room room : floor.getRooms()) {
				rooms.add(room);
			}
		}
		if(rooms.size() == 0)
		{
			throw new RoomException("There are no rooms");
		}
		return rooms;
	}

	// Get a room
	public RoomDto getRoom(String buildingName, int roomNumber) throws BuildingException, RoomException {
		List<Room> rooms = getRooms(buildingName);
		Room room = null;
		for (int i = 0; i < rooms.size() && room == null; i++) {
			Room r = rooms.get(i);
			if (r.getNumber() == roomNumber) {
				room = r;
			}
		}
		if (room == null) {
			throw new RoomException("Room dosn't exist");
		}
		return converter.fromRoomToRoomDto(room);
	}
	
	// Create a new room
	public RoomDto newRoom(RoomDto dto) throws BuildingException, FloorException, RoomException, RoomStateException
	{
		Building building = buildingRepo.findBuildingByName(dto.getBuildingName());
		if(building == null)
		{
			throw new BuildingException("Building doesn't exist");
		}
		List<Floor> floors = floorRepo.findFloorByNumber(dto.getFloorNumber());
		if(floors.size() == 0)
		{
			throw new FloorException("Floor doesn't exist");
		}
		Floor floor = null;
		for (Floor f : floors)
		{
			if(f.getBuilding() == building)
			{
				List<Room> rooms = roomRepo.findRoomByNumber(dto.getNumber());
				for (Room r : rooms)
				{
					if (r.getFloor().getNumber() == dto.getFloorNumber() && r.getFloor().getBuilding().getName().equalsIgnoreCase(dto.getBuildingName()))
					{
						throw new RoomException("Room already exist");
					}
				}
			}
		}
		if(floor == null)
		{
			throw new FloorException("Floor doesn't exist");
		}
		roomRepo.save(converter.fromRoomDtoToRoom(dto));
		floor.addRoom(converter.fromRoomDtoToRoom(dto));
		floorRepo.save(floor);
		return dto;
	}
	
	// Update a room
	public RoomDto updateRoom(String buildingName, RoomDto sent) throws BuildingException, RoomException, FloorException, RoomStateException
	{
		RoomDto roomDto = getRoom(buildingName, sent.getNumber());
		Room room = converter.fromRoomDtoToRoom(roomDto);
		updateService.UpdateRoom(room, converter.fromRoomDtoToRoom(sent));
		roomRepo.save(room);
		return converter.fromRoomToRoomDto(room);
	}
	
	// Change state
	public RoomDto changeState(String buildingName, int roomNumber, String roomState) throws BuildingException, RoomException, RoomStateException, FloorException
	{
		RoomDto roomDto = getRoom(buildingName, roomNumber);
		Room room = converter.fromRoomDtoToRoom(roomDto);
		RoomState state = roomStateRepo.findRoomStateByState(roomState);
		if(state == null)
		{
			throw new RoomStateException("Room state doesn't exist");
		}
		room.setState(state);
		return converter.fromRoomToRoomDto(room);
	}
	
	// Add a stay
	public StayDto addStay(StayDto dto, String buildingName) throws BuildingException, RoomException, PersonException, ClientException, RoomStateException, FloorException
	{
		RoomDto roomDto = getRoom(buildingName, dto.getRoomNumber());
		Room room = converter.fromRoomDtoToRoom(roomDto);
		Stay stay = converter.fromStayDtoToStay(buildingName, dto);
		stayRepo.save(stay);
		room.addStay(stay);
		roomRepo.save(room);
		return dto;
	}
	
}
