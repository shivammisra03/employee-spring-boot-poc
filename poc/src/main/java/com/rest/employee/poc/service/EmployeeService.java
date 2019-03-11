package com.rest.employee.poc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.employee.poc.model.Employee;

@Service
public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Employee getEmpById(int id);

	public boolean addEmployee(Employee emp);

	public boolean updateEmployee(Employee emp);
	
	public boolean deleteEmployee(Employee e);

}
