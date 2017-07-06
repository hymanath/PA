package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pr_district")
public class PrDistrict implements Serializable {
	private Long prDistrictId;
	private String districtName;
	private String districtCode;
	@Id
	@Column(name="pr_district_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPrDistrictId() {
		return prDistrictId;
	}
	public void setPrDistrictId(Long prDistrictId) {
		this.prDistrictId = prDistrictId;
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
