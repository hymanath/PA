package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tdp_resolution_type")
public class TdpResolutionType {
	
	private Long tdpResolutionTypeId;
	private String resolutionName;
	private String day;
	private String resolutionDate;
	
	@Id
	@Column(name="tdp_resolution_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getTdpResolutionTypeId() {
		return tdpResolutionTypeId;
	}


	public void setTdpResolutionTypeId(Long tdpResolutionTypeId) {
		this.tdpResolutionTypeId = tdpResolutionTypeId;
	}

	@Column(name="resolution_name")
	public String getResolutionName() {
		return resolutionName;
	}


	public void setResolutionName(String resolutionName) {
		this.resolutionName = resolutionName;
	}

	@Column(name="day")
	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}

	@Column(name="resolution_date")
	public String getResolutionDate() {
		return resolutionDate;
	}


	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}


}
