package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rws_work_location")
public class RwsWorkLocation {
	
	
	private int rwsWorkLocationId;
	private int rwsWorkId; 
	private String districtCode;
	private String districtName;
	private String constituencyCode;
	private String constituencyName;
	private String mandalCode;
	private String mandalName;
	private String habitationCode;
	private String habitationName;
	
	@Id
	@Column(name="rws_work_location_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int getRwsWorkLocationId() {
		return rwsWorkLocationId;
	}
	public void setRwsWorkLocationId(int rwsWorkLocationId) {
		this.rwsWorkLocationId = rwsWorkLocationId;
	}
	
	@Column(name="rws_work_id")
	public int getRwsWorkId() {
		return rwsWorkId;
	}
	public void setRwsWorkId(int rwsWorkId) {
		this.rwsWorkId = rwsWorkId;
	}
	
	@Column(name="district_code")
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Column(name="constituency_code")
	public String getConstituencyCode() {
		return constituencyCode;
	}
	public void setConstituencyCode(String constituencyCode) {
		this.constituencyCode = constituencyCode;
	}
	
	@Column(name="constituency_name")
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	@Column(name="mandal_code")
	public String getMandalCode() {
		return mandalCode;
	}
	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}
	
	
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	@Column(name="habitation_code")
	public String getHabitationCode() {
		return habitationCode;
	}
	public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	}
	
	@Column(name="habitation_name")
	public String getHabitationName() {
		return habitationName;
	}
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}
	
	
	
	
	
}
