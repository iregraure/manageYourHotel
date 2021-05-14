package com.manageYourHotel.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manageYourHotel.exception.BuildingException;
import com.manageYourHotel.exception.ClientException;
import com.manageYourHotel.exception.FloorException;
import com.manageYourHotel.exception.PersonException;
import com.manageYourHotel.exception.RoomException;
import com.manageYourHotel.exception.RoomStateException;
import com.manageYourHotel.model.entity.Building;
import com.manageYourHotel.model.entity.Category;
import com.manageYourHotel.model.entity.Client;
import com.manageYourHotel.model.entity.Employee;
import com.manageYourHotel.model.entity.Floor;
import com.manageYourHotel.model.entity.Person;
import com.manageYourHotel.model.entity.Room;
import com.manageYourHotel.model.entity.RoomState;
import com.manageYourHotel.model.entity.Stay;
import com.manageYourHotel.repo.BuildingRepository;
import com.manageYourHotel.repo.CategoryRepository;
import com.manageYourHotel.repo.FloorRepository;
import com.manageYourHotel.repo.PersonRepository;
import com.manageYourHotel.repo.RoomRepository;
import com.manageYourHotel.repo.RoomStateRepository;
import com.manageYourHotel.service.BuildingService;
import com.manageYourHotel.service.RoomService;

@Component
public class DtoConverter {

	// Repository
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private BuildingRepository buildingRepo;
	
	@Autowired
	private FloorRepository floorRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private RoomStateRepository roomStateRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
	// Service
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private RoomService roomService;
	
	// From ClientDto to Client
	public Client fromClientDtoToClient(ClientDto dto) {
		Client client = new Client(dto.getName(), dto.getSurname(), dto.getDni(), dto.getEmail(), dto.getAddress(),
				dto.getPhone(), dto.getBirthDate());
		return client;
	}
	
	// From Client to ClientDto
	public ClientDto fromClientToClientDto(Client client)
	{
		ClientDto dto = new ClientDto();
		dto.setName(client.getName());
		dto.setSurname(client.getSurname());
		dto.setDni(client.getDni());
		dto.setEmail(client.getEmail());
		dto.setAddress(client.getAddress());
		dto.setPhone(client.getPhone());
		dto.setBirthDate(client.getBirthDate());
		return dto;
	}
	
	// From EmployeeDto to Employee
	public Employee fromEmployeeDtoToEmployee (EmployeeDto dto)
	{
		Category category = categoryRepo.findCategoryByName(dto.getCategory());
		Employee employee = new Employee(dto.getName(), dto.getSurname(), dto.getDni(), dto.getEmail(), dto.getAddress(),
				dto.getPhone(), dto.getBirthDate(), dto.getNss(), dto.getSchedule(), dto.getSalary(), category);
		return employee;
	}
	
	// From Employee to EmployeeDto
	public EmployeeDto fromEmployeeToEmployeeDto(Employee employee)
	{
		EmployeeDto dto = new EmployeeDto();
		dto.setName(employee.getName());
		dto.setSurname(employee.getSurname());
		dto.setDni(employee.getDni());
		dto.setEmail(employee.getEmail());
		dto.setAddress(employee.getAddress());
		dto.setPhone(employee.getPhone());
		dto.setBirthDate(employee.getBirthDate());
		dto.setNss(employee.getNss());
		dto.setSchedule(employee.getSchedule());
		dto.setSalary(employee.getSalary());
		dto.setCategory(employee.getCategory().getName());
		return dto;
	}
	
	// From BuildingDto to Building
	public Building fromBuildingDtoToBuilding(BuildingDto dto)
	{
		Building building = new Building(dto.getName(), dto.getAddress());
		return building;
	}
	
	// From Building to BuildingDto
	public BuildingDto fromBuildingToBuildingDto(Building building)
	{
		BuildingDto dto = new BuildingDto();
		dto.setName(building.getName());
		dto.setAddress(building.getAddress());
		return dto;
	}
	
	// From floorDto to floor
	public Floor fromFloorDtoToFloor(FloorDto dto)
	{
		Building building = buildingRepo.findBuildingByName(dto.getBuildingName());
		Floor floor = new Floor(building, dto.getNumber());
		// If we have to copy the rooms from another floor
		if (dto.getCopyFloor() != 0)
		{
			// Get all the floors with that number
			List<Floor> floors = floorRepo.findFloorByNumber(dto.getCopyFloor());
			for(Floor f : floors)
			{
				// Search for the floor that is in the same building
				if(f.getBuilding() == building)
				{
					floor.setRooms(f.getRooms());
				}
			}
		}
		return floor;
	}
	
	// From floor to floorDto
	public FloorDto fromFloorToFloorDto(Floor floor)
	{
		FloorDto dto = new FloorDto();
		dto.setNumber(floor.getNumber());
		dto.setBuildingName(floor.getBuilding().getName());
		return dto;
	}
	
	// From roomDto to room
	public Room fromRoomDtoToRoom(RoomDto dto) throws RoomStateException, FloorException, BuildingException, RoomException
	{
		Room room = null;
		// Get the room state
		RoomState state = roomStateRepo.findRoomStateByState(dto.getState());
		if(state == null)
		{
			throw new RoomStateException("State doesn't exist");
		}
		// Get the building 
		Building building = buildingRepo.findBuildingByName(dto.getBuildingName());
		if(building == null)
		{
			throw new BuildingException("There is no building with that name");
		}
		// Get all the floors with the same number
		List<Floor> floors = floorRepo.findFloorByNumber(dto.getFloorNumber());
		if(floors.size() == 0)
		{
			throw new FloorException("There is no floor with that number");
		}
		Room copyRoom = null;
		for(Floor f : floors)
		{
			// Search for the floor that is in the same building
			if(f.getBuilding() == building)
			{
				// Get the room to copy if necesary
				if(dto.getCopyRoom() != 0)
				{
					RoomDto roomDto =roomService.getRoom(dto.getBuildingName(), dto.getCopyRoom()); 
					copyRoom = fromRoomDtoToRoom(roomDto);
					room = new Room(dto.getNumber(), copyRoom.getType(), copyRoom.getPrice(), copyRoom.isSmoker(),
							copyRoom.isTv(), copyRoom.isAirConditioning(), copyRoom.isBreakfast());
				}
				if(room == null)
				{
					room = new Room(dto.getNumber(), dto.getType(), dto.getPrice(), dto.isSmoker(),
							dto.isTv(), dto.isAirConditioning(), dto.isBreakfast());
				}
				room.setFloor(f);
				room.setState(state);
			}
		}
		return room;
	}
	
	// From room to roomDto
	public RoomDto fromRoomToRoomDto(Room room)
	{
		RoomDto dto = new RoomDto();
		dto.setNumber(room.getNumber());
		dto.setType(room.getType());
		dto.setPrice(room.getPrice());
		dto.setSmoker(room.isSmoker());
		dto.setTv(room.isTv());
		dto.setAirConditioning(room.isAirConditioning());
		dto.setBreakfast(room.isBreakfast());
		dto.setFloorNumber(room.getFloor().getNumber());
		dto.setBuildingName(room.getFloor().getBuilding().getName());
		dto.setState(room.getState().getState());
		return dto;
	}
	
	// From stayDto to Stay
	public Stay fromStayDtoToStay(String buildingName, StayDto dto) throws BuildingException, RoomException, PersonException, ClientException
	{
		// Check that the building exists
		Building building = buildingRepo.findBuildingByName(buildingName);
		if(building == null)
		{
			throw new BuildingException("Building doesn't exist");
		}
		// Check that the room exist
		List<Room> rooms = roomRepo.findRoomByNumber(dto.getRoomNumber());
		Room room = null;
		for(Room r : rooms)
		{
			if(r.getFloor().getBuilding() == building)
			{
				room = r;
			}
		}
		if(room == null)
		{
			throw new RoomException("Room doesn't exist");
		}
		// Check that the person exists and is a client
		Person client = personRepo.findPersonByDni(dto.getClientDni());
		if(client == null)
		{
			throw new PersonException("There is no person with that dni");
		}
		if(!(client instanceof Client))
		{
			throw new ClientException("There is no client with that dni");
		}
		// Convert string dates to localDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime startDate = LocalDateTime.parse(dto.getStartDate(), formatter);
		LocalDateTime endDate = LocalDateTime.parse(dto.getEndDate(), formatter);
		// Create and return the stay
		Stay stay = new Stay(startDate, endDate, room, (Client)client);
		return stay;
	}
	
	// From stay to staydto
	public StayDto fromStayToStayDto (Stay stay)
	{
		// Get the room number and client dni
		int roomNumber = stay.getRoom().getNumber();
		String clientDni = stay.getClient().getDni();
		// Convert LocalDateTime dates to string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String startDate = stay.getStartDate().format(formatter);
		String endDate = stay.getEndDate().format(formatter);
		// Create and return stayDto
		StayDto dto = new StayDto();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setRate(stay.getRate());
		dto.setComment(stay.getComment());
		dto.setRoomNumber(roomNumber);
		dto.setClientDni(clientDni);
		return dto;
	}
}








