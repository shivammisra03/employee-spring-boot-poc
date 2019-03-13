package com.employee.jpa.poc.employeejpa;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.employeejpa.EmployeeJpaApplication;
import com.poc.employeejpa.controller.EmployeeJpaController;
import com.poc.employeejpa.entity.Employee;
import com.poc.employeejpa.model.resp.EmployeeErrorResp;
import com.poc.employeejpa.repository.EmployeeRespository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeJpaApplication.class)
public class EmployeeJpaApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeJpaController empJpaController;

	@Mock
	private EmployeeRespository empRepository;

	private ObjectMapper objMapper;

	@Before
	public void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(empJpaController).build();
		objMapper = new ObjectMapper();
	}

	@Test
	public void testGetAll() throws Exception {

		when(empRepository.getAllEmployees())
				.thenReturn(Arrays.asList(new Employee(1, "testName 1", 10000), new Employee(2, "testname 2", 2000)));
		mockMvc.perform(get("/employeejpa/getAll")).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testGetByEmpId() throws Exception {

		when(empRepository.getEmpById(1)).thenReturn(Arrays.asList(new Employee(1, "testName 1", 10000)));
		mockMvc.perform(get("/employeejpa/getEmpById").param("id", "1")).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testGetByEmpIdAndSal() throws Exception {

		when(empRepository.getEmpByIdAndSal(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Arrays.asList(new Employee(1, "testName 1", 10000)));
		mockMvc.perform(get("/employeejpa/getEmpByIdAndSal").param("id", "1").param("salary", "10000")).andDo(print())
				.andExpect(status().is(200)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testGetEmpNameById() throws Exception {

		when(empRepository.getNameByEmpId(Mockito.anyInt())).thenReturn("Shivam");
		mockMvc.perform(get("/employeejpa/getEmpNameById").param("id", "1")).andDo(print()).andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testGetEmpNameByIdWith400Status() throws Exception {

		when(empRepository.getNameByEmpId(Mockito.anyInt())).thenReturn(null);
		MvcResult result = mockMvc.perform(get("/employeejpa/getEmpNameById").param("id", "1")).andDo(print())
				.andExpect(status().is(400)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		EmployeeErrorResp employeeErrorResp = objMapper.readValue(new File("src/test/resources/errResp.json"),
				EmployeeErrorResp.class);
		String expResult = objMapper.writeValueAsString(employeeErrorResp);
		JSONAssert.assertEquals(expResult, result.getResponse().getContentAsString(), true);

	}

}
