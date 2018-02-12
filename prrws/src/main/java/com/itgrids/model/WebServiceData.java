package com.itgrids.model;

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
@Table(name = "webservice_data")
public class WebServiceData {

	private Long       webServiceDataId;
	private Long       webserviceId;
	private String     inputData;
	private String     responceData;
	private Date       dataDate;
	private Date       insertedTime;
	private String     isDeleted;
	private Webservice webservice;
	
	@Id
	@Column(name="webservice_data_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWebServiceDataId() {
		return webServiceDataId;
	}
	public void setWebServiceDataId(Long webServiceDataId) {
		this.webServiceDataId = webServiceDataId;
	}
	@Column(name="webservice_id")
	public Long getWebserviceId() {
		return webserviceId;
	}
	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}
	@Column(name="input_data")
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	@Column(name="responce_data")
	public String getResponceData() {
		return responceData;
	}
	public void setResponceData(String responceData) {
		this.responceData = responceData;
	}
	@Column(name="data_date")
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "webservice_id", insertable = false, updatable = false)
	public Webservice getWebservice() {
		return webservice;
	}
	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}
}
