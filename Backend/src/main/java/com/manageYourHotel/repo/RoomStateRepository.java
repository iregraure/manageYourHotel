package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.RoomState;

@Repository
public interface RoomStateRepository extends CrudRepository<RoomState, Integer> 
{
	public RoomState findRoomStateByState(String state);
}
