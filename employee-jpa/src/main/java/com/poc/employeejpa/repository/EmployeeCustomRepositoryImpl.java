package com.poc.employeejpa.repository;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.poc.employeejpa.entity.Employee;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * call a stored procedure with no input param
	 */
	@Override
	public List<Employee> getAllEmployees() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getAllEmployees");
		return query.getResultList();

	}

	/**
	 * call a stored procedure with one input param
	 */
	@Override
	public List<Employee> getEmpById(int id) {
		StoredProcedureQuery result = em.createNamedStoredProcedureQuery("getEmpById").setParameter("eid", id);
		return result.getResultList();
	}

	/**
	 * call a stored procedure with two input param
	 */
	@Override
	public List<Employee> getEmpByIdAndSal(int id, int salary) {
		StoredProcedureQuery result = em.createNamedStoredProcedureQuery("getEmpByIdAndSal").setParameter("eid", id)
				.setParameter("esalary", salary);
		return result.getResultList();
	}

	/**
	 * call a stored procedure with one input param and one output param
	 */
	@Override
	public String getNameByEmpId(int id) {

		StoredProcedureQuery result = em.createNamedStoredProcedureQuery("getNameById").setParameter("eid", id);
		Object ename = result.getOutputParameterValue("ename");
		if (Objects.nonNull(ename)) {
			return ename.toString();

		}
		return null;
	}

}
