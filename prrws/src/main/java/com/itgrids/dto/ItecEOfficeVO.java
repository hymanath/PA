package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class ItecEOfficeVO {

	private Long id;
	private String name;
	
	private Long departmentId;
	private String departmentName;
	private String employeeName;
	private String postName;
	private String designation;
	private String isPostDetailActive;
	private Long created = 0L;
	private Long zeroToSeven = 0L;
	private Long eightToFifteen = 0L;
	private Long sixteenToThirty = 0L;
	private Long thirtyoneToSixty = 0L;
	private Long aboveSixty = 0L;
	private Long totalCount = 0L;
	private String percentage;
	private List<ItecEOfficeVO> subList = new ArrayList<ItecEOfficeVO>(0);
	
	
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
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIsPostDetailActive() {
		return isPostDetailActive;
	}
	public void setIsPostDetailActive(String isPostDetailActive) {
		this.isPostDetailActive = isPostDetailActive;
	}
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public Long getZeroToSeven() {
		return zeroToSeven;
	}
	public void setZeroToSeven(Long zeroToSeven) {
		this.zeroToSeven = zeroToSeven;
	}
	public Long getEightToFifteen() {
		return eightToFifteen;
	}
	public void setEightToFifteen(Long eightToFifteen) {
		this.eightToFifteen = eightToFifteen;
	}
	public Long getSixteenToThirty() {
		return sixteenToThirty;
	}
	public void setSixteenToThirty(Long sixteenToThirty) {
		this.sixteenToThirty = sixteenToThirty;
	}
	public Long getThirtyoneToSixty() {
		return thirtyoneToSixty;
	}
	public void setThirtyoneToSixty(Long thirtyoneToSixty) {
		this.thirtyoneToSixty = thirtyoneToSixty;
	}
	public Long getAboveSixty() {
		return aboveSixty;
	}
	public void setAboveSixty(Long aboveSixty) {
		this.aboveSixty = aboveSixty;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<ItecEOfficeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ItecEOfficeVO> subList) {
		this.subList = subList;
	}
}
