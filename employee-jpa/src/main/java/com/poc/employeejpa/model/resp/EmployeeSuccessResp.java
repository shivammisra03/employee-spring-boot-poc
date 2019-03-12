package com.poc.employeejpa.model.resp;

public class EmployeeSuccessResp {
	private int code;
	private String empName;
	
	

	public EmployeeSuccessResp(int code, String empName, String msg) {
		this.code = code;
		this.empName = empName;
		this.msg = msg;
	}

	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
