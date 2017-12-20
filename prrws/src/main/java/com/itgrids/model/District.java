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
	private Long prDistrictId;
	private Long rwsDistrictId;
	private State state;
	private PrDistrict prDistrict;
	private RwsDistrict rwsDistrict;
	private Long encDistrictId;
	
	

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable = false, updatable = false)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	@Column(name="pr_district_id")
	public Long getPrDistrictId() {
		return prDistrictId;
	}

	public void setPrDistrictId(Long prDistrictId) {
		this.prDistrictId = prDistrictId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pr_district_id", insertable = false, updatable = false)
	public PrDistrict getPrDistrict() {
		return prDistrict;
	}

	public void setPrDistrict(PrDistrict prDistrict) {
		this.prDistrict = prDistrict;
	}

	@Column(name="rws_district_id")
	public Long getRwsDistrictId() {
		return rwsDistrictId;
	}

	public void setRwsDistrictId(Long rwsDistrictId) {
		this.rwsDistrictId = rwsDistrictId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_district_id", insertable = false, updatable = false)
	public RwsDistrict getRwsDistrict() {
		return rwsDistrict;
	}

	public void setRwsDistrict(RwsDistrict rwsDistrict) {
		this.rwsDistrict = rwsDistrict;
	}

	@Column(name = "enc_district_id")
	public Long getEncDistrictId() {
		return encDistrictId;
	}

	public void setEncDistrictId(Long encDistrictId) {
		this.encDistrictId = encDistrictId;
	}
	
	
	
}
