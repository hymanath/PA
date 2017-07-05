package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pr_constituency")
public class PrConstituency implements Serializable {
	private Long prConstituencyId;
	private String constituencyName;
	private String constituencyCode;
	private Long prDistrict;
	@Id
	@Column(name="pr_constituency_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPrConstituencyId() {
		return prConstituencyId;
	}
	public void setPrConstituencyId(Long prConstituencyId) {
		this.prConstituencyId = prConstituencyId;
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
	@Column(name="pr_district_id")
	public Long getPrDistrict() {
		return prDistrict;
	}
	public void setPrDistrict(Long prDistrict) {
		this.prDistrict = prDistrict;
	}	
	
}
