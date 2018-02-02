package com.itgrids.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "govt_work_type")
public class GovtWorkType {
	private Long govtWorkTypeId;
	private String workType;
	
	@Column(name="govt_work_type_id")
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}
	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}
	
	@Column(name="work_type")
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	
}
