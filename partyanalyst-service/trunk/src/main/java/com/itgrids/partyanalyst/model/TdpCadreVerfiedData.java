package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "tdp_cadre_verfied_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreVerfiedData implements Serializable{

	private Long 		tdpCadreVerfiedDataId;
	private String 		requestData;
	private String 		responceData;
	private String 		userId;
	private Date 		insertedTime;
	private Date 		updatedTime;
	private Long		reqSize;
	private Long 		resSize;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_verfied_data_id", unique = true, nullable = false)
	public Long getTdpCadreVerfiedDataId() {
		return tdpCadreVerfiedDataId;
	}
	public void setTdpCadreVerfiedDataId(Long tdpCadreVerfiedDataId) {
		this.tdpCadreVerfiedDataId = tdpCadreVerfiedDataId;
	}
	
	@Column(name="request_data")
	public String getRequestData() {
		return requestData;
	}
	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
	
	@Column(name="responce_data")
	public String getResponceData() {
		return responceData;
	}
	public void setResponceData(String responceData) {
		this.responceData = responceData;
	}
	
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="requested_size")
	public Long getReqSize() {
		return reqSize;
	}
	public void setReqSize(Long reqSize) {
		this.reqSize = reqSize;
	}
	
	@Column(name="responce_size")
	public Long getResSize() {
		return resSize;
	}
	public void setResSize(Long resSize) {
		this.resSize = resSize;
	}
	
	
	
	
}
