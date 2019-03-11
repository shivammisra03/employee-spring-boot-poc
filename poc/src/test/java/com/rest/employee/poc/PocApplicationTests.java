package com.rest.employee.poc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.employee.poc.controller.EmployeeController;
import com.rest.employee.poc.model.Employee;
import com.rest.employee.poc.model.ResponseMsg;
import com.rest.employee.poc.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PocApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	private ObjectMapper objMapper;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		objMapper = new ObjectMapper();
	}

	@Test
	public void testGetAll() throws Exception {

		when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(new Employee(1, "test", 101010)));
		mockMvc.perform(get("/employee/getAll").accept(MediaType.APPLICATION_XML)).andDo(print())
				.andExpect(status().is(200));
	}

	@Test
	public void testGetByQueryParam() throws Exception {
		when(employeeService.getEmpById(1)).thenReturn(new Employee(1, "Test Using Query param", 112233));
		mockMvc.perform(get("/employee/getByQueryParam").param("id", "1").accept(MediaType.APPLICATION_XML))
				.andDo(print()).andExpect(status().is(200));

	}

	@Test
	public void testGetEmployeeUsingPathId() throws Exception {
		when(employeeService.getEmpById(1)).thenReturn(new Employee(1, "Test Using Path param and headers", 90987));
		mockMvc.perform(get("/employee/getByPathParamId/{id}", "1").header("headers", "Test header")
				.accept(MediaType.APPLICATION_XML)).andDo(print()).andExpect(status().is(200));

	}

	@Test
	public void testAddEmployee() throws Exception {
		Employee emp = objMapper.readValue(new File("src/test/resources/request/employee.json"), Employee.class);
		String empReq = objMapper.writeValueAsString(emp);
		// Instead of specifying a particular object to add, we use any() which acts
		// as a matcher
		when(employeeService.addEmployee(Mockito.any(Employee.class))).thenReturn(true);

		mockMvc.perform(post("/employee/addEmployee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(empReq)).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testUpdateEmployeeWith200() throws Exception {
		Employee emp = objMapper.readValue(new File("src/test/resources/request/employee.json"), Employee.class);
		String empReq = objMapper.writeValueAsString(emp);
		// Instead of specifying a particular object to update, we use any() which acts
		// as a matcher
		when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(true);
		mockMvc.perform(put("/employee/updateEmployee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(empReq)).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testUpdateEmployeeWith400() throws Exception {
		Employee emp = objMapper.readValue(new File("src/test/resources/request/employee.json"), Employee.class);
		String empReq = objMapper.writeValueAsString(emp);
		// Instead of specifying a particular object to update, we use any() which acts
		// as a matcher
		when(employeeService.updateEmployee(Mockito.any(Employee.class))).thenReturn(false);
		mockMvc.perform(put("/employee/updateEmployee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(empReq)).andDo(print()).andExpect(status().is(400))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testDeleteEmployeeWith200() throws Exception {
		Employee emp = objMapper.readValue(new File("src/test/resources/request/employee.json"), Employee.class);
		String empReq = objMapper.writeValueAsString(emp);
		// Instead of specifying a particular object to delete, we use any() which acts
		// as a matcher
		when(employeeService.deleteEmployee(Mockito.any(Employee.class))).thenReturn(true);
		mockMvc.perform(delete("/employee/deleteEmployee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(empReq)).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testDeleteEmployeeWith400() throws Exception {
		Employee emp = objMapper.readValue(new File("src/test/resources/request/employee.json"), Employee.class);
		String empReq = objMapper.writeValueAsString(emp);
		// Instead of specifying a particular object to delete, we use any() which acts
		// as a matcher
		when(employeeService.deleteEmployee(Mockito.any(Employee.class))).thenReturn(false);
		MvcResult result = mockMvc
				.perform(delete("/employee/deleteEmployee").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(empReq))
				.andDo(print()).andExpect(status().is(400))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();

		ResponseMsg responseMsg = objMapper.readValue(new File("src/test/resources/response/employeeErrorResp.json"),
				ResponseMsg.class);
		String respMsg = objMapper.writeValueAsString(responseMsg);
		assertEquals(result.getResponse().getContentAsString(), respMsg);

	}

}
