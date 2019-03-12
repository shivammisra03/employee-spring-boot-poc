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
		@NamedStoredProcedureQuery(name = "getAllEmployees", procedureName = "get_all_employees"),
		@NamedStoredProcedureQuery(name = "getEmpById", procedureName = "get_emp_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "getEmpByIdAndSal", procedureName = "get_emp_using_id_salary", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "esalary", type = Integer.class) }),
		@NamedStoredProcedureQuery(name = "getNameById", procedureName = "get_name_by_id", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "eid", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "ename", type = String.class) }) })
public class Employee {

	@Id
	private int id;
	private String name;
	private int salary;

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
