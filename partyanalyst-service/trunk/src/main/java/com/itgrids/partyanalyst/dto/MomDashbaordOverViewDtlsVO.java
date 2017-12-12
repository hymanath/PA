package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MomDashbaordOverViewDtlsVO {
	
	private Long id;
	private String name;
	private Long count = 0l;
	private Long totalMomInYourLocation = 0l;
	private Long momCreatedByYourLocation = 0l; 
	private Long momAtYourLocationOnly = 0l; 
	private Long assignedToOther =0l ;
	private Long assignedToYourLocation =0l;
	
	private List<MomDashbaordOverViewDtlsVO> subList1;
	private List<MomDashbaordOverViewDtlsVO> subList2;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<MomDashbaordOverViewDtlsVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<MomDashbaordOverViewDtlsVO> subList1) {
		this.subList1 = subList1;
	}
	public List<MomDashbaordOverViewDtlsVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<MomDashbaordOverViewDtlsVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotalMomInYourLocation() {
		return totalMomInYourLocation;
	}
	public void setTotalMomInYourLocation(Long totalMomInYourLocation) {
		this.totalMomInYourLocation = totalMomInYourLocation;
	}
	public Long getMomCreatedByYourLocation() {
		return momCreatedByYourLocation;
	}
	public void setMomCreatedByYourLocation(Long momCreatedByYourLocation) {
		this.momCreatedByYourLocation = momCreatedByYourLocation;
	}
	public Long getMomAtYourLocationOnly() {
		return momAtYourLocationOnly;
	}
	public void setMomAtYourLocationOnly(Long momAtYourLocationOnly) {
		this.momAtYourLocationOnly = momAtYourLocationOnly;
	}
	public Long getAssignedToOther() {
		return assignedToOther;
	}
	public void setAssignedToOther(Long assignedToOther) {
		this.assignedToOther = assignedToOther;
	}
	public Long getAssignedToYourLocation() {
		return assignedToYourLocation;
	}
	public void setAssignedToYourLocation(Long assignedToYourLocation) {
		this.assignedToYourLocation = assignedToYourLocation;
	}
	
}
