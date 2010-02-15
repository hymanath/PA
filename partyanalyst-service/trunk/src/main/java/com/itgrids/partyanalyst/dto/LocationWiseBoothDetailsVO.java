package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class LocationWiseBoothDetailsVO {

	private Long locationId;
	private String locationName;
	private Set<String> booths;
	private Set<String> subLocations;
	private List<String> hamletsOfTownship;
	private Long population;
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
	
	public Set<String> getBooths() {
		return booths;
	}

	public void setBooths(Set<String> booths) {
		this.booths = booths;
	}

	public void setSubLocations(Set<String> subLocations) {
		this.subLocations = subLocations;
	}

	public Set<String> getSubLocations() {
		return subLocations;
	}

	public Long getPopulation() {
		return population;
	}
	
	public void setPopulation(Long population) {
		this.population = population;
	}

	public List<String> getHamletsOfTownship() {
		return hamletsOfTownship;
	}

	public void setHamletsOfTownship(List<String> hamletsOfTownship) {
		this.hamletsOfTownship = hamletsOfTownship;
	}
	
	
	
}
