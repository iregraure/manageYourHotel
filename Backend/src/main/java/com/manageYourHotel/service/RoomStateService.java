package com.manageYourHotel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageYourHotel.exception.RoomStateException;
import com.manageYourHotel.model.dto.DtoConverter;
import com.manageYourHotel.model.dto.RoomStateDto;
import com.manageYourHotel.model.entity.RoomState;
import com.manageYourHotel.repo.RoomStateRepository;

@Service
public class RoomStateService 
{
	// Repository
	@Autowired
	private RoomStateRepository stateRepo;
	
	// Converter
	@Autowired
	private DtoConverter converter;
	
	// Get all room states
	public List<RoomStateDto> getStates() throws RoomStateException
	{
		List<RoomState> states = (List<RoomState>) stateRepo.findAll();
		if(states.size() == 0)
		{
			throw new RoomStateException("There are no room states");
		}
		List<RoomStateDto> dtos = new ArrayList<RoomStateDto>();
		for(RoomState state : states)
		{
			RoomStateDto dto = converter.fromRoomStateToRoomStateDto(state);
			dtos.add(dto);
		}
		return dtos;
	}
	
	// Create a new room state
	public RoomStateDto newRoomState(RoomStateDto dto) throws RoomStateException
	{
		RoomState state = stateRepo.findRoomStateByState(dto.getState());
		if(state != null)
		{
			throw new RoomStateException("State already exists");
		}
		stateRepo.save(converter.fromRoomStateDtoToRoomState(dto));
		return dto;
	}
}








