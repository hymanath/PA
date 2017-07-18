package com.itgrids.model;

import java.io.Serializable;

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
@Table(name="rws_tehsil")
public class RwsTehsil implements Serializable {

	/**
	 * sanjeev
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long rwsTehsilId;
	private String tehsilName;
	private String tehsilCode;
	private Long rwsDistrictId;
	
	private RwsDistrict rwsDistrict;
	
	@Id
	@Column(name="rws_tehsil_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRwsTehsilId() {
		return rwsTehsilId;
	}
	public void setRwsTehsilId(Long rwsTehsilId) {
		this.rwsTehsilId = rwsTehsilId;
	}
	@Column(name="tehsil_name")
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	@Column(name="tehsil_code")
	public String getTehsilCode() {
		return tehsilCode;
	}
	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
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
	
	

}
