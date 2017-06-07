package com.itgrids.model;

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
@Table(name = "district")
public class District{
	
	private static final long serialVersionUID = -2853930539938433902L;
	
	private Long districtId;
	private String districtName;
	private Long stateId;
	private String nameLocal;

	@Id
	@Column(name="district_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getDistrictId() {
		return districtId;
	}
	
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name="name_local")
	public String getNameLocal() {
		return nameLocal;
	}

	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	
	
}
