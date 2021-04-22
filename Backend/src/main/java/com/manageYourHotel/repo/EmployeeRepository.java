package com.manageYourHotel.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manageYourHotel.model.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{

}
