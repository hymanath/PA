package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;


public class InfectedBoothsVO implements Serializable{
	private List<Long> totalPanchaytIds;
	private String name;
	private Long panchayatId;
	private List<InfectedBoothsVO> panchayatsList;
	private List<Long> overAllPanchayatIds;
	private List<InfectedBoothsVO> levelVOs;
	
	private Long partyId;
	private String party;
	private String year;
	
	
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<InfectedBoothsVO> getLevelVOs() {
		return levelVOs;
	}
	public void setLevelVOs(List<InfectedBoothsVO> levelVOs) {
		this.levelVOs = levelVOs;
	}
	public List<Long> getTotalPanchaytIds() {
		return totalPanchaytIds;
	}
	public void setTotalPanchaytIds(List<Long> totalPanchaytIds) {
		this.totalPanchaytIds = totalPanchaytIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public List<InfectedBoothsVO> getPanchayatsList() {
		return panchayatsList;
	}
	public void setPanchayatsList(List<InfectedBoothsVO> panchayatsList) {
		this.panchayatsList = panchayatsList;
	}
	public List<Long> getOverAllPanchayatIds() {
		return overAllPanchayatIds;
	}
	public void setOverAllPanchayatIds(List<Long> overAllPanchayatIds) {
		this.overAllPanchayatIds = overAllPanchayatIds;
	}
	
	
	
}
