package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationWiseBoothDetailsVO {

	private Long locationId;
	private String locationName;
	private Set<SelectOptionVO> booths;
	private Set<SelectOptionVO> subLocations;
	private List<SelectOptionVO> hamletsOfTownship;
	private List<LocationWiseBoothDetailsVO> result = new ArrayList<LocationWiseBoothDetailsVO>(0);
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
	
	
	
	public String getIsAlreadyImageUpload() {
		return isAlreadyImageUpload;
	}
	public void setIsAlreadyImageUpload(String isAlreadyImageUpload) {
		this.isAlreadyImageUpload = isAlreadyImageUpload;
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

	public LocationWiseBoothDetailsVO(){}
	
	public LocationWiseBoothDetailsVO(Long locationId,String locationName)
	{
		this.locationId=locationId;
		this.locationName = locationName;
	}
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
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
	
	public Long getPopulation() {
		return population;
	}
	
	public void setPopulation(Long population) {
		this.population = population;
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

	public Long getVotesPolled() {
		return votesPolled;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof LocationWiseBoothDetailsVO){
			LocationWiseBoothDetailsVO vo = (LocationWiseBoothDetailsVO) obj;
			return new EqualsBuilder().append(locationId, vo.getLocationId()).isEquals();
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 37).append(locationId).toHashCode();
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<LocationWiseBoothDetailsVO> getResult() {
		return result;
	}

	public void setResult(List<LocationWiseBoothDetailsVO> result) {
		this.result = result;
	}
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
