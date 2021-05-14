package com.manageYourHotel.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Integer>
{
	public List<Floor> findFloorByNumber(int number);
}
