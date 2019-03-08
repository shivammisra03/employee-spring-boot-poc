package com.rest.employee.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.employee.poc.model.Employee;
import com.rest.employee.poc.model.ResponseMsg;
import com.rest.employee.poc.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping(path = "/getAll")
	public List<Employee> getAll() {

		return empService.getAllEmployees();

	}

	@GetMapping(path = "/getByQueryParam")
	public ResponseEntity getByQueryParam(@RequestParam int id) {
		Employee e = empService.getEmpById(id);
		if (e == null) {
			ResponseMsg msg = new ResponseMsg(400, "No employee found for the given id");
			return ResponseEntity.status(400).body(msg);
		} else {
			return ResponseEntity.ok(e);
		}

	}

	@GetMapping(path = "/getByPathParamId/{id}")
	public ResponseEntity getEmployeeUsingPathId(@PathVariable int id, @RequestHeader String headers) {
		System.out.println("Header : " + headers);
		Employee e = empService.getEmpById(id);
		if (e == null) {
			ResponseMsg msg = new ResponseMsg(400, "No employee found for the given id");
			return ResponseEntity.status(400).body(msg);
		} else {
			return ResponseEntity.ok(e);
		}

	}

	@PostMapping(path = "/addEmployee")
	public ResponseEntity addEmployee(@RequestBody Employee emp) {
		empService.addEmployee(emp);
		return ResponseEntity.ok().body(new ResponseMsg(200, "Employee Added Successfully"));

	}

	@PutMapping(path = "/updateEmployee")
	public ResponseEntity updateEmployee(@RequestBody Employee emp) {
		if (empService.updateEmployee(emp)) {
			return ResponseEntity.ok().body(new ResponseMsg(200, "Employee updated Successfully"));
		} else {
			return ResponseEntity.status(400).body(new ResponseMsg(400, "No Employee Found"));
		}

	}

	@DeleteMapping(value = "/deleteEmployee")
	public ResponseEntity deletEmployee(@RequestBody Employee e) {

		if (empService.deleteEmployee(e)) {
			return ResponseEntity.ok().body(new ResponseMsg(200, "Employee Successfully Deleted"));
		} else {
			ResponseMsg msg = new ResponseMsg(400, "No employee found");
			return ResponseEntity.status(400).body(msg);
		}
	}

}
