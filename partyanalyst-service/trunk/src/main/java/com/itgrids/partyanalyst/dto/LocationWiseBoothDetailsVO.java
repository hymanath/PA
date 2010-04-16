package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationWiseBoothDetailsVO {

	private Long locationId;
	private String locationName;
	private Set<SelectOptionVO> booths;
	private Set<SelectOptionVO> subLocations;
	private Set<SelectOptionVO> hamletsOfTownship;
	private Long population;
	private Long votesPolled;
	private String electionYear;
	
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

	public Set<SelectOptionVO> getHamletsOfTownship() {
		return hamletsOfTownship;
	}

	public void setHamletsOfTownship(Set<SelectOptionVO> hamletsOfTownship) {
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
			return new EqualsBuilder().append(locationName, vo.getLocationName()).isEquals();
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 37).append(locationName).toHashCode();
	}

	
	
}
