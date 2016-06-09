package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class LocationWiseBoothDetailsVO1 implements Serializable{

	private static final long serialVersionUID = -7491089535741848196L;
	
	private Long locationId;
	private String locationName;
	private Set<SelectOptionVO> booths;
	private Set<SelectOptionVO> subLocations;
	private List<SelectOptionVO> hamletsOfTownship;
	private Long population;
	private Long votesPolled;
	private Long total = 0l;;
	private String electionYear;
	private Long count;
	
	private Long id;
	private String name;
	private String mobileNo;
	private String planedDate;
	private String conductedDate;
	private String isAlreadyImageUpload;
	private List<LocationWiseBoothDetailsVO> result = new ArrayList<LocationWiseBoothDetailsVO>(0);
	private List<IdNameVO> idNameVolist;
	private List<String> datesList;
	

	
	public List<String> getDatesList() {
		return datesList;
	}
	public void setDatesList(List<String> datesList) {
		this.datesList = datesList;
	}
	public List<IdNameVO> getIdNameVolist() {
		return idNameVolist;
	}
	public void setIdNameVolist(List<IdNameVO> idNameVolist) {
		this.idNameVolist = idNameVolist;
	}
	public List<LocationWiseBoothDetailsVO> getResult() {
		return result;
	}
	public void setResult(List<LocationWiseBoothDetailsVO> result) {
		this.result = result;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Set<SelectOptionVO> getBooths() {
		return booths;
	}
	public void setBooths(Set<SelectOptionVO> booths) {
		this.booths = booths;
	}
	public Set<SelectOptionVO> getSubLocations() {
		return subLocations;
	}
	public void setSubLocations(Set<SelectOptionVO> subLocations) {
		this.subLocations = subLocations;
	}
	public List<SelectOptionVO> getHamletsOfTownship() {
		return hamletsOfTownship;
	}
	public void setHamletsOfTownship(List<SelectOptionVO> hamletsOfTownship) {
		this.hamletsOfTownship = hamletsOfTownship;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public Long getVotesPolled() {
		return votesPolled;
	}
	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPlanedDate() {
		return planedDate;
	}
	public void setPlanedDate(String planedDate) {
		this.planedDate = planedDate;
	}
	public String getConductedDate() {
		return conductedDate;
	}
	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}
	public String getIsAlreadyImageUpload() {
		return isAlreadyImageUpload;
	}
	public void setIsAlreadyImageUpload(String isAlreadyImageUpload) {
		this.isAlreadyImageUpload = isAlreadyImageUpload;
	}
}
