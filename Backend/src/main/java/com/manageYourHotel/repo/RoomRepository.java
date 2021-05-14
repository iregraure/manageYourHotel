package com.manageYourHotel.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>
{
	public List<Room> findRoomByNumber(int number);
}
