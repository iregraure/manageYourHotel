package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>
{
	public Category findCategoryByName(String name);
}
