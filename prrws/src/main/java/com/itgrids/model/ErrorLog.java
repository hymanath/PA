package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "error_log")
public class ErrorLog implements Serializable{

	private static final long serialVersionUID = -2232630009626803171L;
	
	private Long errorLogId;
	private String inputUrl;
	private String inputJson;
	private Integer statusCode;
	private String response;
	private Date insertedTime;
	private String exceptionMsg;
	private String exceptionStack;

	public ErrorLog(){}
	
	@Id
	@Column(name="error_log_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getErrorLogId() {
		return errorLogId;
	}

	public void setErrorLogId(Long errorLogId) {
		this.errorLogId = errorLogId;
	}

	@Column(name="input_url")
	public String getInputUrl() {
		return inputUrl;
	}

	public void setInputUrl(String inputUrl) {
		this.inputUrl = inputUrl;
	}

	@Column(name="input_json")
	public String getInputJson() {
		return inputJson;
	}

	public void setInputJson(String inputJson) {
		this.inputJson = inputJson;
	}

	@Column(name="status_code")
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Column(name="response")
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="exception_msg")
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Column(name="exception_stack")
	public String getExceptionStack() {
		return exceptionStack;
	}

	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}
	
}
