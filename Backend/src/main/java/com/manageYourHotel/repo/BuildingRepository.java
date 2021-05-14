package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Building;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Integer>
{
	public Building findBuildingByName(String name);
}
