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
@Table(name = "pr_tehsil")
public class PrTehsil {

	private static final long serialVersionUID = -2853930539938433902L;
	private Long prTehsilId;
	private String uniqueCode;
	private String tehsilName;
	private Long prDistrictId;
	
	private PrDistrict prDistrict;

	@Id
	@Column(name="pr_tehsil_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPrTehsilId() {
		return prTehsilId;
	}

	public void setPrTehsilId(Long prTehsilId) {
		this.prTehsilId = prTehsilId;
	}
	@Column(name="unique_code")
	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	@Column(name="tehsil_name")
	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
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
	
	
	
}
