package com.poc.employeejpa.repository;

import java.util.List;

import com.poc.employeejpa.entity.Employee;

public interface EmployeeCustomRepository {

	public List<Employee> getAllEmployees();

	public List<Employee> getEmpById(int id);

	public List<Employee> getEmpByIdAndSal(int id, int salary);

	public String getNameByEmpId(int id);

}
