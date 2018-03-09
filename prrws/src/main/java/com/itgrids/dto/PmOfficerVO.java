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
