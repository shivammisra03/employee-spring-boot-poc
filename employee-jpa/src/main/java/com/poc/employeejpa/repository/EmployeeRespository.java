package com.poc.employeejpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.employeejpa.entity.Employee;

public interface EmployeeRespository extends CrudRepository<Employee, Integer>, EmployeeCustomRepository {

}
