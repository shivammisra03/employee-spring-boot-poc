package com.poc.employeejpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "getAllEmployees", procedureName = "get_all_employees", resultClasses = Employee.class),
		@NamedStoredProcedureQuery(name = "getEmpById", procedureName = "get_emp_by_id", resultClasses = Employee.class, parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "getEmpByIdAndSal", procedureName = "get_emp_using_id_salary", resultClasses = Employee.class, parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "esalary", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "getNameById", procedureName = "get_name_by_id", resultClasses = Employee.class, parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "ename", type = String.class) }) })
public class Employee {

	@Id
	private int id;
	private String name;
	private int salary;

	public Employee() {
	}

	public Employee(int id, String name, int salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
