package com.poc.employeejpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.employeejpa.entity.Employee;
import com.poc.employeejpa.model.resp.EmployeeErrorResp;
import com.poc.employeejpa.model.resp.EmployeeSuccessResp;
import com.poc.employeejpa.repository.EmployeeRespository;

@RestController
@RequestMapping("/employeejpa")
public class EmployeeJpaController {

	@Autowired
	private EmployeeRespository employeeRespository;

	@GetMapping(path = "/getAll")
	public List<Employee> getAll() {
		return employeeRespository.getAllEmployees();
	}

	@GetMapping(path = "/getEmpById")
	public List<Employee> getByEmpId(@RequestParam int id) {
		return employeeRespository.getEmpById(id);
	}

	@GetMapping(path = "/getEmpByIdAndSal")
	public ResponseEntity getByEmpIdAndSal(@RequestParam int id, @RequestParam int salary) {
		List<Employee> e = employeeRespository.getEmpByIdAndSal(id, salary);
		if (e.size() > 0) {
			return ResponseEntity.ok().body(e);
		} else {
			return ResponseEntity.status(400).body(new EmployeeErrorResp(400, "Sorry!! No employee Found"));
		}

	}

	@GetMapping(path = "/getEmpNameById")
	public ResponseEntity getEmpNameById(@RequestParam int id) {
		String name = employeeRespository.getNameByEmpId(id);
		if (name != null) {
			return ResponseEntity.ok(new EmployeeSuccessResp(200, name, "Employee Found"));
		} else {
			return ResponseEntity.status(400).body(new EmployeeErrorResp(400, "Sorry!! No employee Found"));
		}

	}

}
