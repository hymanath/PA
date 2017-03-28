package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.Set;

public class CadreInsuranceInputVO {
	
	   private Long userAccessLevelId;
	   private Set<Long> userAccessLevelValues;
	   private Long stateId;
	   private Date fromDate;
	   private Date toDate;
	   private Long enrollmentYearId;
	   private Long activityMemerId;
	   
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
	    public Long getEnrollmentYearId() {
			return enrollmentYearId;
		}
		public void setEnrollmentYearId(Long enrollmentYearId) {
			this.enrollmentYearId = enrollmentYearId;
		}
		public Long getActivityMemerId() {
			return activityMemerId;
		}
		public void setActivityMemerId(Long activityMemerId) {
			this.activityMemerId = activityMemerId;
		}
	   
}
