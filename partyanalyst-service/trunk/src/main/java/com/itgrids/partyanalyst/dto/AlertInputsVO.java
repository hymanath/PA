package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AlertInputsVO {
	   private Long userAccessLevelId;
	   private Set<Long> userAccessLevelValues;
	   private Long stateId;
	   private List<Long> impactLevelIds;
	   private Date fromDate;
	   private Date toDate;
	   private String locationLevel;
	   private Long alertTypeId; 
	   private List<Long> editionList;
	   private List<Long> alertStatusIds;
	   private Long districtId;
	   private Long constituencyId;
	   private List<Long> constituencyIds;
	   private Long parliamentId;
	   private Long activityMemerId;
	   private String type;
	   
		public Long getUserAccessLevelId() {
			return userAccessLevelId;
		}
		public void setUserAccessLevelId(Long userAccessLevelId) {
			this.userAccessLevelId = userAccessLevelId;
		}
		public Set<Long> getUserAccessLevelValues() {
			return userAccessLevelValues;
		}
		public void setUserAccessLevelValues(Set<Long> userAccessLevelValues) {
			this.userAccessLevelValues = userAccessLevelValues;
		}
		public Long getStateId() {
			return stateId;
		}
		public void setStateId(Long stateId) {
			this.stateId = stateId;
		}
		public List<Long> getImpactLevelIds() {
			return impactLevelIds;
		}
		public void setImpactLevelIds(List<Long> impactLevelIds) {
			this.impactLevelIds = impactLevelIds;
		}
		public Date getFromDate() {
			return fromDate;
		}
		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}
		public Date getToDate() {
			return toDate;
		}
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
		public String getLocationLevel() {
			return locationLevel;
		}
		public void setLocationLevel(String locationLevel) {
			this.locationLevel = locationLevel;
		}
		public List<Long> getEditionList() {
			return editionList;
		}
		public void setEditionList(List<Long> editionList) {
			this.editionList = editionList;
		}
		public List<Long> getAlertStatusIds() {
			return alertStatusIds;
		}
		public void setAlertStatusIds(List<Long> alertStatusIds) {
			this.alertStatusIds = alertStatusIds;
		}
		public Long getAlertTypeId() {
			return alertTypeId;
		}
		public void setAlertTypeId(Long alertTypeId) {
			this.alertTypeId = alertTypeId;
		}
		public Long getDistrictId() {
			return districtId;
		}
		public void setDistrictId(Long districtId) {
			this.districtId = districtId;
		}
		public Long getConstituencyId() {
			return constituencyId;
		}
		public void setConstituencyId(Long constituencyId) {
			this.constituencyId = constituencyId;
		}
		public Long getActivityMemerId() {
			return activityMemerId;
		}
		public void setActivityMemerId(Long activityMemerId) {
			this.activityMemerId = activityMemerId;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Long getParliamentId() {
			return parliamentId;
		}
		public void setParliamentId(Long parliamentId) {
			this.parliamentId = parliamentId;
		}
		public List<Long> getConstituencyIds() {
			return constituencyIds;
		}
		public void setConstituencyIds(List<Long> constituencyIds) {
			this.constituencyIds = constituencyIds;
		}
		
        		 
}
