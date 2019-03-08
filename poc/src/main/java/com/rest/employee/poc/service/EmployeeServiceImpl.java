package com.rest.employee.poc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.employee.poc.model.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	private static List<Employee> employees = new ArrayList<Employee>();

	static {
		employees.add(new Employee(1, "Shivam", 10000));
		employees.add(new Employee(2, "Virat", 300000));
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employees;
	}

	@Override
	public Employee getEmpById(int id) {
		Optional<Employee> e = employees.stream().filter(emp -> emp.getId() == id).findFirst();
		if (e.isPresent()) {
			return e.get();
		} else {
			return null;
		}
	}

	@Override
	public void addEmployee(Employee emp) {
		employees.add(emp);

	}

	@Override
	public boolean updateEmployee(Employee employee) {
		if (employees.stream().noneMatch(emp -> emp.getId() == employee.getId())) {
			return false;
		}
		employees.forEach(emp -> {
			if (emp.getId() == employee.getId()) {
				emp.setName(employee.getName());
				emp.setSalary(employee.getSalary());
			}

		});
		return true;

	}
	
	@Override
	public boolean deleteEmployee(Employee e) {
		if (employees.stream().noneMatch(emp -> emp.getId() == e.getId())) {
			return false;
		} else {

			Iterator<Employee> itr = employees.iterator();
			while (itr.hasNext()) {
				Employee emp = itr.next();
				if (e.getId() == emp.getId()) {
					itr.remove();
				}
			}
			return true;
		}

	}

}
