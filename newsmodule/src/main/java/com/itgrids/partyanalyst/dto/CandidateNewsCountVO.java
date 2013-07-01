package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

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
	
  
 
}
