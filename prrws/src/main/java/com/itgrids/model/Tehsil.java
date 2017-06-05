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
	
	@Id
	@Column(name="tehsil_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long tehsilId;
	
	@Column(name="tehsil_name")
	private String tehsilName;
	
	@Column(name="district_id")
	private Long districtId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "district_id", insertable = false, updatable = false)
	private District district;
	
	
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
}
