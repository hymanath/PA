package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PmOfficerVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private Long id;
	private String name;
	private Set<Long> petitionIds = null;
	private Set<Long> subWorkIds = null;
	private List<PmOfficerVO>  subList = new ArrayList<PmOfficerVO>();
	private String estimationCost="0";
	private Long officerDesigId;
	private String officerDesig;
	private Long petitionCnt =0l ;
	private Long subWorkCnt=0l;
	private Long locationId;
	private String locationName;
	private Set<Long> withOutCostPetitionIds = null;
	private Set<Long> withOutCostSubWorkIds = null;
	
	
	public Set<Long> getWithOutCostPetitionIds() {
		return withOutCostPetitionIds;
	}
	public void setWithOutCostPetitionIds(Set<Long> withOutCostPetitionIds) {
		this.withOutCostPetitionIds = withOutCostPetitionIds;
	}
	public Set<Long> getWithOutCostSubWorkIds() {
		return withOutCostSubWorkIds;
	}
	public void setWithOutCostSubWorkIds(Set<Long> withOutCostSubWorkIds) {
		this.withOutCostSubWorkIds = withOutCostSubWorkIds;
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
	public Long getPetitionCnt() {
		return petitionCnt;
	}
	public void setPetitionCnt(Long petitionCnt) {
		this.petitionCnt = petitionCnt;
	}
	public Long getSubWorkCnt() {
		return subWorkCnt;
	}
	public void setSubWorkCnt(Long subWorkCnt) {
		this.subWorkCnt = subWorkCnt;
	}
	public Long getOfficerDesigId() {
		return officerDesigId;
	}
	public void setOfficerDesigId(Long officerDesigId) {
		this.officerDesigId = officerDesigId;
	}
	public String getOfficerDesig() {
		return officerDesig;
	}
	public void setOfficerDesig(String officerDesig) {
		this.officerDesig = officerDesig;
	}
	public String getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(String estimationCost) {
		this.estimationCost = estimationCost;
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
	public Set<Long> getPetitionIds() {
		return petitionIds;
	}
	public void setPetitionIds(Set<Long> petitionIds) {
		this.petitionIds = petitionIds;
	}
	public Set<Long> getSubWorkIds() {
		return subWorkIds;
	}
	public void setSubWorkIds(Set<Long> subWorkIds) {
		this.subWorkIds = subWorkIds;
	}
	public List<PmOfficerVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PmOfficerVO> subList) {
		this.subList = subList;
	}
	
}
