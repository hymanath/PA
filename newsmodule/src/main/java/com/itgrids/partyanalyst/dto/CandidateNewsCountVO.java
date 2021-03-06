package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CandidateNewsCountVO implements Serializable{
   
   private String name;
   private Long id;
   private Long stateNewsCount = 0L;
   private Long constituencyNewsCount = 0L;
   private Long districtNewsCount = 0L;
   private Long mandalNewsCount = 0L;
   private Long villageNewsCount = 0L;
   private Long localEleBodyNewsCount = 0L;
   private Long wardNewsCount = 0L;
   private Long boothNewsCount = 0L;
   private List<String> candidateNames;
   private List<Long> stateCounts;
   private List<Long> districtCounts;
   private List<Long> constituencyCounts;
   private List<Long> mandalCounts;
   private Long totalNewsCount = 0L;
   private Long responseNewsCount = 0L;
   private Long notResponseNewsCount = 0L;
   private String partyName;
   
	public List<Long> getConstituencyCounts() {
	return constituencyCounts;
	}
	public void setConstituencyCounts(List<Long> constituencyCounts) {
		this.constituencyCounts = constituencyCounts;
	}
	public List<Long> getMandalCounts() {
		return mandalCounts;
	}
	public void setMandalCounts(List<Long> mandalCounts) {
		this.mandalCounts = mandalCounts;
	}
	public List<Long> getStateCounts() {
	return stateCounts;
	}
	public void setStateCounts(List<Long> stateCounts) {
		this.stateCounts = stateCounts;
	}
	public List<Long> getDistrictCounts() {
		return districtCounts;
	}
	public void setDistrictCounts(List<Long> districtCounts) {
		this.districtCounts = districtCounts;
	}
	public List<String> getCandidateNames() {
	return candidateNames;
	}
	public void setCandidateNames(List<String> candidateNames) {
		this.candidateNames = candidateNames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStateNewsCount() {
		return stateNewsCount;
	}
	public void setStateNewsCount(Long stateNewsCount) {
		this.stateNewsCount = stateNewsCount;
	}
	public Long getConstituencyNewsCount() {
		return constituencyNewsCount;
	}
	public void setConstituencyNewsCount(Long constituencyNewsCount) {
		this.constituencyNewsCount = constituencyNewsCount;
	}
	public Long getDistrictNewsCount() {
		return districtNewsCount;
	}
	public void setDistrictNewsCount(Long districtNewsCount) {
		this.districtNewsCount = districtNewsCount;
	}
	public Long getMandalNewsCount() {
		return mandalNewsCount;
	}
	public void setMandalNewsCount(Long mandalNewsCount) {
		this.mandalNewsCount = mandalNewsCount;
	}
	public Long getVillageNewsCount() {
		return villageNewsCount;
	}
	public void setVillageNewsCount(Long villageNewsCount) {
		this.villageNewsCount = villageNewsCount;
	}
	public Long getLocalEleBodyNewsCount() {
		return localEleBodyNewsCount;
	}
	public void setLocalEleBodyNewsCount(Long localEleBodyNewsCount) {
		this.localEleBodyNewsCount = localEleBodyNewsCount;
	}
	public Long getWardNewsCount() {
		return wardNewsCount;
	}
	public void setWardNewsCount(Long wardNewsCount) {
		this.wardNewsCount = wardNewsCount;
	}
	public Long getBoothNewsCount() {
		return boothNewsCount;
	}
	public void setBoothNewsCount(Long boothNewsCount) {
		this.boothNewsCount = boothNewsCount;
	}
	public Long getTotalNewsCount() {
		return totalNewsCount;
	}
	public void setTotalNewsCount(Long totalNewsCount) {
		this.totalNewsCount = totalNewsCount;
	}
	public Long getResponseNewsCount() {
		return responseNewsCount;
	}
	public void setResponseNewsCount(Long responseNewsCount) {
		this.responseNewsCount = responseNewsCount;
	}
	public Long getNotResponseNewsCount() {
		return notResponseNewsCount;
	}
	public void setNotResponseNewsCount(Long notResponseNewsCount) {
		this.notResponseNewsCount = notResponseNewsCount;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
  
 
}
