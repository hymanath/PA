package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rws_district")
public class RwsDistrict implements Serializable {
	
	/**
	 * sanjeev
	 */
	private static final long serialVersionUID = 1L;
	private Long rwsDistrictId;
	private String districtName;
	private String districtCode;
	
	@Id
	@Column(name="rws_district_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRwsDistrictId() {
		return rwsDistrictId;
	}
	public void setRwsDistrictId(Long rwsDistrictId) {
		this.rwsDistrictId = rwsDistrictId;
	}
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name="district_code")
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	

}
