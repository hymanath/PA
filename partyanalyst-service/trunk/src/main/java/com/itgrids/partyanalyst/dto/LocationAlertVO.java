package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjeev
 *
 */
public class LocationAlertVO {

	private Long locationId;
	private String locationName;
	private Long count=0l;
	private Long alertTypeId;
	private String alertType;
	private List<LocationAlertVO> subList = new ArrayList<LocationAlertVO>(0);
	private List<LocationAlertVO> impactScopeList;
	private List<LocationAlertVO> alertTypeList;
	private Long alertCount=0l;
	private Long id;
	private String status;
	private String colour;
	private Long totalAlertCount=0l;
	private Long involveMemberCount=0l;
	private Long assignedMemberCount =0l;
	private Double percentage;
	
	
	public List<LocationAlertVO> getAlertTypeList() {
		return alertTypeList;
	}
	public void setAlertTypeList(List<LocationAlertVO> alertTypeList) {
		this.alertTypeList = alertTypeList;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAlertCount() {
		return alertCount;
	}
	public void setAlertCount(Long alertCount) {
		this.alertCount = alertCount;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public List<LocationAlertVO> getSubList() {
		return subList;
	}
	public void setSubList(List<LocationAlertVO> subList) {
		this.subList = subList;
	}
	public Long getTotalAlertCount() {
		return totalAlertCount;
	}
	public void setTotalAlertCount(Long totalAlertCount) {
		this.totalAlertCount = totalAlertCount;
	}
	public Long getInvolveMemberCount() {
		return involveMemberCount;
	}
	public void setInvolveMemberCount(Long involveMemberCount) {
		this.involveMemberCount = involveMemberCount;
	}
	public Long getAssignedMemberCount() {
		return assignedMemberCount;
	}
	public void setAssignedMemberCount(Long assignedMemberCount) {
		this.assignedMemberCount = assignedMemberCount;
	}
	public List<LocationAlertVO> getImpactScopeList() {
		return impactScopeList;
	}
	public void setImpactScopeList(List<LocationAlertVO> impactScopeList) {
		this.impactScopeList = impactScopeList;
	}
   	
  	
}
