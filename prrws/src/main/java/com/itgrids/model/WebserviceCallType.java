package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "webservice_call_type")
public class WebserviceCallType implements Serializable{

	private static final long serialVersionUID = 4899114317644028193L;
	
	private Long webserviceCallTypeId;
	private String callType;
	private String description;
	
	public WebserviceCallType(){}

	@Id
	@Column(name="webservice_call_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWebserviceCallTypeId() {
		return webserviceCallTypeId;
	}

	public void setWebserviceCallTypeId(Long webserviceCallTypeId) {
		this.webserviceCallTypeId = webserviceCallTypeId;
	}

	@Column(name="call_type")
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
