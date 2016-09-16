package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserTypeVO implements Serializable{
	
	private Long   id;
	private String name;
	private String image;
	private String locationName;
	
	private Long   userId;
	private Long   tdpCadreId;
	private Long   parentActivityMemberId;
	private Long   activityMemberId;
	
	private Long   userTypeId;
	private String userType;
	private String shortName;
	
	private Long   locationLevelId;
	private String locationLevelName;
	private List<Long> locationValues;
	private Set<Long>  locationValuesSet;
	
	private Map<Long,UserTypeVO> subMap;
	private List<UserTypeVO> subList;
	
	private Long totalCount = 0l;
	private Long completedCount = 0l;
	private Long startedCount = 0l;
	private Long notStartedCount = 0l;
	private Double completedPerc = 0.0;
	private Double startedPerc = 0.0;
	private Double notStartedPerc = 0.0;
	
	//For Training
	private Long totalEligibleCount=0l;
	private Long totalAttenedCount=0l;
	private Long totalNotAttenedCount=0l;
	private Double totalAttenedCountPer=0.0;
	private Double totalNotAttenedCountPer=0.0;
	
	private Long positiveCount = 0l;
	private Long negativeCount = 0l;
	private Double positivePercentage = 0.0;
	private Double negativePercentage = 0.0;
	//For Meeting
	private Long totalMeetingCnt=0l;
	private Long conductedMeetingCnt=0l;
	private Long notConductedMeetingCnt=0l;
	private Long mayBeMeetingCnt=0l;
	
	private Double conductedAndMayBeMeetingPer=0.0;
	private Double conductedMeetingPerc=0.0;
	private Double notConductedMeetingPerc=0.0;
	private Double mayBeMeetingPerc=0.0;
	
	//for event
	private Long inviteeCnt=0l;
	private Long attendedCnt=0l;
	private Long inviteeAttendedCnt=0l;
	private Long nonInviteeAttendedCnt=0l;
	
	private Double attendedCntPer=0.0d;
	private Double inviteeAttendedCntPer=0.0d;
	private Double nonInviteeAttendedCntPer=0.0d;
	
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
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public String getLocationLevelName() {
		return locationLevelName;
	}
	public void setLocationLevelName(String locationLevelName) {
		this.locationLevelName = locationLevelName;
	}
	public List<Long> getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(List<Long> locationValues) {
		this.locationValues = locationValues;
	}
	
	public List<UserTypeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<UserTypeVO> subList) {
		this.subList = subList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public Map<Long, UserTypeVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, UserTypeVO> subMap) {
		this.subMap = subMap;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<Long> getLocationValuesSet() {
		return locationValuesSet;
	}
	public void setLocationValuesSet(Set<Long> locationValuesSet) {
		this.locationValuesSet = locationValuesSet;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Double getCompletedPerc() {
		return completedPerc;
	}
	public void setCompletedPerc(Double completedPerc) {
		this.completedPerc = completedPerc;
	}
	public Long getTotalEligibleCount() {
		return totalEligibleCount;
	}
	public void setTotalEligibleCount(Long totalEligibleCount) {
		this.totalEligibleCount = totalEligibleCount;
	}
	public Long getTotalAttenedCount() {
		return totalAttenedCount;
	}
	public void setTotalAttenedCount(Long totalAttenedCount) {
		this.totalAttenedCount = totalAttenedCount;
	}
	public Long getTotalNotAttenedCount() {
		return totalNotAttenedCount;
	}
	public void setTotalNotAttenedCount(Long totalNotAttenedCount) {
		this.totalNotAttenedCount = totalNotAttenedCount;
	}
	public Double getTotalAttenedCountPer() {
		return totalAttenedCountPer;
	}
	public void setTotalAttenedCountPer(Double totalAttenedCountPer) {
		this.totalAttenedCountPer = totalAttenedCountPer;
	}
	public Double getTotalNotAttenedCountPer() {
		return totalNotAttenedCountPer;
	}
	public void setTotalNotAttenedCountPer(Double totalNotAttenedCountPer) {
		this.totalNotAttenedCountPer = totalNotAttenedCountPer;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Double getStartedPerc() {
		return startedPerc;
	}
	public void setStartedPerc(Double startedPerc) {
		this.startedPerc = startedPerc;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public Double getNotStartedPerc() {
		return notStartedPerc;
	}
	public void setNotStartedPerc(Double notStartedPerc) {
		this.notStartedPerc = notStartedPerc;
	}
	public Long getParentActivityMemberId() {
		return parentActivityMemberId;
	}
	public void setParentActivityMemberId(Long parentActivityMemberId) {
		this.parentActivityMemberId = parentActivityMemberId;
	}
	public Long getPositiveCount() {
		return positiveCount;
	}
	public void setPositiveCount(Long positiveCount) {
		this.positiveCount = positiveCount;
	}
	public Long getNegativeCount() {
		return negativeCount;
	}
	public void setNegativeCount(Long negativeCount) {
		this.negativeCount = negativeCount;
	}
	public Double getPositivePercentage() {
		return positivePercentage;
	}
	public void setPositivePercentage(Double positivePercentage) {
		this.positivePercentage = positivePercentage;
	}
	public Double getNegativePercentage() {
		return negativePercentage;
	}
	public void setNegativePercentage(Double negativePercentage) {
		this.negativePercentage = negativePercentage;
	}
	public Long getTotalMeetingCnt() {
		return totalMeetingCnt;
	}
	public void setTotalMeetingCnt(Long totalMeetingCnt) {
		this.totalMeetingCnt = totalMeetingCnt;
	}
	public Long getConductedMeetingCnt() {
		return conductedMeetingCnt;
	}
	public void setConductedMeetingCnt(Long conductedMeetingCnt) {
		this.conductedMeetingCnt = conductedMeetingCnt;
	}
	public Long getNotConductedMeetingCnt() {
		return notConductedMeetingCnt;
	}
	public void setNotConductedMeetingCnt(Long notConductedMeetingCnt) {
		this.notConductedMeetingCnt = notConductedMeetingCnt;
	}
	public Long getMayBeMeetingCnt() {
		return mayBeMeetingCnt;
	}
	public void setMayBeMeetingCnt(Long mayBeMeetingCnt) {
		this.mayBeMeetingCnt = mayBeMeetingCnt;
	}
	public Double getConductedAndMayBeMeetingPer() {
		return conductedAndMayBeMeetingPer;
	}
	public void setConductedAndMayBeMeetingPer(Double conductedAndMayBeMeetingPer) {
		this.conductedAndMayBeMeetingPer = conductedAndMayBeMeetingPer;
	}
	public Double getConductedMeetingPerc() {
		return conductedMeetingPerc;
	}
	public void setConductedMeetingPerc(Double conductedMeetingPerc) {
		this.conductedMeetingPerc = conductedMeetingPerc;
	}
	public Double getNotConductedMeetingPerc() {
		return notConductedMeetingPerc;
	}
	public void setNotConductedMeetingPerc(Double notConductedMeetingPerc) {
		this.notConductedMeetingPerc = notConductedMeetingPerc;
	}
	public Double getMayBeMeetingPerc() {
		return mayBeMeetingPerc;
	}
	public void setMayBeMeetingPerc(Double mayBeMeetingPerc) {
		this.mayBeMeetingPerc = mayBeMeetingPerc;
	}
	public Long getInviteeCnt() {
		return inviteeCnt;
	}
	public void setInviteeCnt(Long inviteeCnt) {
		this.inviteeCnt = inviteeCnt;
	}
	public Long getAttendedCnt() {
		return attendedCnt;
	}
	public void setAttendedCnt(Long attendedCnt) {
		this.attendedCnt = attendedCnt;
	}
	public Long getInviteeAttendedCnt() {
		return inviteeAttendedCnt;
	}
	public void setInviteeAttendedCnt(Long inviteeAttendedCnt) {
		this.inviteeAttendedCnt = inviteeAttendedCnt;
	}
	public Long getNonInviteeAttendedCnt() {
		return nonInviteeAttendedCnt;
	}
	public void setNonInviteeAttendedCnt(Long nonInviteeAttendedCnt) {
		this.nonInviteeAttendedCnt = nonInviteeAttendedCnt;
	}
	public Double getAttendedCntPer() {
		return attendedCntPer;
	}
	public void setAttendedCntPer(Double attendedCntPer) {
		this.attendedCntPer = attendedCntPer;
	}
	public Double getInviteeAttendedCntPer() {
		return inviteeAttendedCntPer;
	}
	public void setInviteeAttendedCntPer(Double inviteeAttendedCntPer) {
		this.inviteeAttendedCntPer = inviteeAttendedCntPer;
	}
	public Double getNonInviteeAttendedCntPer() {
		return nonInviteeAttendedCntPer;
	}
	public void setNonInviteeAttendedCntPer(Double nonInviteeAttendedCntPer) {
		this.nonInviteeAttendedCntPer = nonInviteeAttendedCntPer;
	}
}
