package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "webservice_call_details")
public class WebserviceCallDetails implements Serializable{

	private static final long serialVersionUID = -3040322832367684984L;
	
	private Long webserviceCallDetailsId;
	private Webservice webservice;
	private Long statusCode;
	private Date callTime;
	private Long timeTaken;
	private String status;
	private String url;
	private String inputData;

	private Long webserviceId;

	@Id
	@Column(name="webservice_call_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWebserviceCallDetailsId() {
		return webserviceCallDetailsId;
	}

	public void setWebserviceCallDetailsId(Long webserviceCallDetailsId) {
		this.webserviceCallDetailsId = webserviceCallDetailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "webservice_id", insertable = false, updatable = false)
	public Webservice getWebservice() {
		return webservice;
	}

	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}

	@Column(name="status_code")
	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	@Column(name="call_time")
	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	@Column(name="time_taken")
	public Long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(Long timeTaken) {
		this.timeTaken = timeTaken;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="webservice_id")
	public Long getWebserviceId() {
		return webserviceId;
	}

	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="input_data")
	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	
}
