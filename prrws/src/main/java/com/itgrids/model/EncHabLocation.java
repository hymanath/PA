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
@Table(name="enc_hab_location")
public class EncHabLocation {
	
	private Long encHabLocationid;
	private Long encWorksId;
	private String habitationCode;
	private Long districtCode;
	private Long mandalCode;
	private Long constituencyCode;
	
	private EncWorks encWorks;
	
	@Id
	@Column(name="enc_hab_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEncHabLocationid() {
		return encHabLocationid;
	}
	public void setEncHabLocationid(Long encHabLocationid) {
		this.encHabLocationid = encHabLocationid;
	}
	@Column(name="enc_works_id")
	public Long getEncWorksId() {
		return encWorksId;
	}
	public void setEncWorksId(Long encWorksId) {
		this.encWorksId = encWorksId;
	}
	@Column(name="habitation_code")
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	@Column(name="district_code")
	public Long getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="mandal_code")
	public Long getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(Long mandalCode) {
		this.mandalCode = mandalCode;
	}
	@Column(name="constituency_code")
	public Long getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(Long constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "enc_works_id", insertable = false, updatable = false)
	public EncWorks getEncWorks() {
		return encWorks;
	}
	public void setEncWorks(EncWorks encWorks) {
		this.encWorks = encWorks;
	}
	
	

}
