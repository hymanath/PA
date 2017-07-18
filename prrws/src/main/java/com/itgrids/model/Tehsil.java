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

/**
 * Description
 * @author <a href="mailto:shrinu.pittala@itgrids.com">Shrinu Pittala</a> 
 * @version 1.0/
 */

@Entity
@Table(name = "tehsil")
public class Tehsil {

	private static final long serialVersionUID = -2853930539938433902L;

	private Long tehsilId;
	private String tehsilName;
	private Long districtId;
	private Long rwsTehsilId;
	
	private District district;
	
	@Id
	@Column(name="tehsil_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	@Column(name="tehsil_name")
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	@Column(name="rws_tehsil_id")
	public Long getRwsTehsilId() {
		return rwsTehsilId;
	}
	
	public void setRwsTehsilId(Long rwsTehsilId) {
		this.rwsTehsilId = rwsTehsilId;
	}
	
}
