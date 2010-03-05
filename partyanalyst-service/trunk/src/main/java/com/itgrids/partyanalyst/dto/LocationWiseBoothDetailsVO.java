package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class LocationWiseBoothDetailsVO {

	private Long locationId;
	private String locationName;
	private Set<SelectOptionVO> booths;
	private Set<SelectOptionVO> subLocations;
	private List<SelectOptionVO> hamletsOfTownship;
	private String loggedInUserParty;
	private List<Long> userPartyVotes;
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

	public String getLoggedInUserParty() {
		return loggedInUserParty;
	}

	public void setLoggedInUserParty(String loggedInUserParty) {
		this.loggedInUserParty = loggedInUserParty;
	}

	public List<Long> getUserPartyVotes() {
		return userPartyVotes;
	}

	public void setUserPartyVotes(List<Long> userPartyVotes) {
		this.userPartyVotes = userPartyVotes;
	}

	
	
	
}
