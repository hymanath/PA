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
@Table(name="rws_constituency")
public class RwsConstituency implements Serializable {

	/**
	 * sanjeev
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long rwsConstituencyId;
	private String constituencyName;
	private String constituencyCode;
	private Long rwsDistrictId;
	
	private RwsDistrict rwsDistrict;
	
	@Id
	@Column(name="rws_constituency_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRwsConstituencyId() {
		return rwsConstituencyId;
	}
	public void setRwsConstituencyId(Long rwsConstituencyId) {
		this.rwsConstituencyId = rwsConstituencyId;
	}
	@Column(name="constituency_name")
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	@Column(name="constituency_code")
	public String getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(String constituencyCode) {
		this.constituencyCode = constituencyCode;
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
