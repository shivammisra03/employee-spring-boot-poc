package com.poc.employeejpa.model.resp;

public class EmployeeErrorResp {

	private int code;
	private String errorMsg;

	public EmployeeErrorResp(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}

	public EmployeeErrorResp() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
