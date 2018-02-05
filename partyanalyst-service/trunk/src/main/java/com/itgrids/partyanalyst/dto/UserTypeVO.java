package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
// Don't add new variables
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
	private List<IdNameVO> subLocationList = new ArrayList<IdNameVO>();
	
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
	private List<Long> attndMembersList = new ArrayList<Long>(0);
	private List<Long> invitedMembrsList = new ArrayList<Long>(0);
	private List<Long> invitAttndMbrsList = new ArrayList<Long>(0);
	
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
    // for cadre registration
	private Long totalTargetCount=0l;
	private Long totalCadreCount=0l;
	private Double totalCadreCountPer=0.0d;
	private Long totalCadreCountToday = 0l;
	private Long totalTargetCount2014 = 0l;
	private Long totalRenewalCadreCount=0l;
	private Long totalNewCadreCount=0l;
	private Double totalRenewalCadreCountPer=0.0d;
	private Double totalNewCadreCountPer=0.0d;
	private Long totalActvtiesCount = 0l;
	private Long condctedActiesCount = 0l;
	private Long notCondctedActiesCount = 0l;
	private Double conductedPerc = 0.0d;
	private Double notConductedPerc = 0.0d;
	private Long imgesCoveredCount = 0l;
	private Double imagesCvredCountPerc = 0.0d;
	private List<TrainingCampProgramVO> daysList = new ArrayList<TrainingCampProgramVO>(0);
	
	private Long installed = 0L;
	private Long pending = 0L;
	private Long notSmartPhone = 0L;
	private String installedPerc;
	private String pendingPerc;
	private String notSmartPhonePerc;
	private List<UserTypeVO> subList1 = new ArrayList<UserTypeVO>(0);
	private Long statePosts =0l;
	private Long districtPosts =0l;
	private Long constncyPosts =0l;
	private Long mandalPosts =0l;
	private Long centralPosts =0l;
	private Long villagePosts =0l;
	private Double statePostsPerc=0.0;
	private Double districtPostsPerc=0.0;
	private Double constncyPostsPerc=0.0;
	private Double mandalPostsPerc=0.0;
	private Double centralPostsPerc=0.0;
	private Double villagePostsPerc=0.0;
	private String totalPer;
	private Long overalTotal =0l;
	//private String 
	// Don't add new variables
	
	public String getNotSmartPhonePerc() {
		return notSmartPhonePerc;
	}
	public void setNotSmartPhonePerc(String notSmartPhonePerc) {
		this.notSmartPhonePerc = notSmartPhonePerc;
	}
	public List<UserTypeVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<UserTypeVO> subList1) {
		this.subList1 = subList1;
	}
	public Long getInstalled() {
		return installed;
	}
	public void setInstalled(Long installed) {
		this.installed = installed;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public Long getNotSmartPhone() {
		return notSmartPhone;
	}
	public void setNotSmartPhone(Long notSmartPhone) {
		this.notSmartPhone = notSmartPhone;
	}
	public String getInstalledPerc() {
		return installedPerc;
	}
	public void setInstalledPerc(String installedPerc) {
		this.installedPerc = installedPerc;
	}
	public String getPendingPerc() {
		return pendingPerc;
	}
	public void setPendingPerc(String pendingPerc) {
		this.pendingPerc = pendingPerc;
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
	public Long getTotalTargetCount() {
		return totalTargetCount;
	}
	public void setTotalTargetCount(Long totalTargetCount) {
		this.totalTargetCount = totalTargetCount;
	}
	public Long getTotalCadreCount() {
		return totalCadreCount;
	}
	public void setTotalCadreCount(Long totalCadreCount) {
		this.totalCadreCount = totalCadreCount;
	}
	public Double getTotalCadreCountPer() {
		return totalCadreCountPer;
	}
	public void setTotalCadreCountPer(Double totalCadreCountPer) {
		this.totalCadreCountPer = totalCadreCountPer;
	}
	public Long getTotalCadreCountToday() {
		return totalCadreCountToday;
	}
	public void setTotalCadreCountToday(Long totalCadreCountToday) {
		this.totalCadreCountToday = totalCadreCountToday;
	}
	public Long getTotalTargetCount2014() {
		return totalTargetCount2014;
	}
	public void setTotalTargetCount2014(Long totalTargetCount2014) {
		this.totalTargetCount2014 = totalTargetCount2014;
	}
	public List<IdNameVO> getSubLocationList() {
		return subLocationList;
	}
	public void setSubLocationList(List<IdNameVO> subLocationList) {
		this.subLocationList = subLocationList;
	}
	public Long getTotalRenewalCadreCount() {
		return totalRenewalCadreCount;
	}
	public void setTotalRenewalCadreCount(Long totalRenewalCadreCount) {
		this.totalRenewalCadreCount = totalRenewalCadreCount;
	}
	public Long getTotalNewCadreCount() {
		return totalNewCadreCount;
	}
	public void setTotalNewCadreCount(Long totalNewCadreCount) {
		this.totalNewCadreCount = totalNewCadreCount;
	}
	public Double getTotalRenewalCadreCountPer() {
		return totalRenewalCadreCountPer;
	}
	public void setTotalRenewalCadreCountPer(Double totalRenewalCadreCountPer) {
		this.totalRenewalCadreCountPer = totalRenewalCadreCountPer;
	}
	public Double getTotalNewCadreCountPer() {
		return totalNewCadreCountPer;
	}
	public void setTotalNewCadreCountPer(Double totalNewCadreCountPer) {
		this.totalNewCadreCountPer = totalNewCadreCountPer;
	}
	public Long getTotalActvtiesCount() {
		return totalActvtiesCount;
	}
	public void setTotalActvtiesCount(Long totalActvtiesCount) {
		this.totalActvtiesCount = totalActvtiesCount;
	}
	public Long getCondctedActiesCount() {
		return condctedActiesCount;
	}
	public void setCondctedActiesCount(Long condctedActiesCount) {
		this.condctedActiesCount = condctedActiesCount;
	}
	public Long getNotCondctedActiesCount() {
		return notCondctedActiesCount;
	}
	public void setNotCondctedActiesCount(Long notCondctedActiesCount) {
		this.notCondctedActiesCount = notCondctedActiesCount;
	}
	public Double getConductedPerc() {
		return conductedPerc;
	}
	public void setConductedPerc(Double conductedPerc) {
		this.conductedPerc = conductedPerc;
	}
	public Double getNotConductedPerc() {
		return notConductedPerc;
	}
	public void setNotConductedPerc(Double notConductedPerc) {
		this.notConductedPerc = notConductedPerc;
	}
	public Long getImgesCoveredCount() {
		return imgesCoveredCount;
	}
	public void setImgesCoveredCount(Long imgesCoveredCount) {
		this.imgesCoveredCount = imgesCoveredCount;
	}
	public Double getImagesCvredCountPerc() {
		return imagesCvredCountPerc;
	}
	public void setImagesCvredCountPerc(Double imagesCvredCountPerc) {
		this.imagesCvredCountPerc = imagesCvredCountPerc;
	}
	public List<Long> getAttndMembersList() {
		return attndMembersList;
	}
	public void setAttndMembersList(List<Long> attndMembersList) {
		this.attndMembersList = attndMembersList;
	}
	public List<Long> getInvitedMembrsList() {
		return invitedMembrsList;
	}
	public void setInvitedMembrsList(List<Long> invitedMembrsList) {
		this.invitedMembrsList = invitedMembrsList;
	}
	public List<Long> getInvitAttndMbrsList() {
		return invitAttndMbrsList;
	}
	public void setInvitAttndMbrsList(List<Long> invitAttndMbrsList) {
		this.invitAttndMbrsList = invitAttndMbrsList;
	}
	public List<TrainingCampProgramVO> getDaysList() {
		return daysList;
	}
	public void setDaysList(List<TrainingCampProgramVO> daysList) {
		this.daysList = daysList;
	}
	public Long getStatePosts() {
		return statePosts;
	}
	public void setStatePosts(Long statePosts) {
		this.statePosts = statePosts;
	}
	public Long getDistrictPosts() {
		return districtPosts;
	}
	public void setDistrictPosts(Long districtPosts) {
		this.districtPosts = districtPosts;
	}
	public Long getConstncyPosts() {
		return constncyPosts;
	}
	public void setConstncyPosts(Long constncyPosts) {
		this.constncyPosts = constncyPosts;
	}
	public Long getMandalPosts() {
		return mandalPosts;
	}
	public void setMandalPosts(Long mandalPosts) {
		this.mandalPosts = mandalPosts;
	}
	public Long getCentralPosts() {
		return centralPosts;
	}
	public void setCentralPosts(Long centralPosts) {
		this.centralPosts = centralPosts;
	}
	public Long getVillagePosts() {
		return villagePosts;
	}
	public void setVillagePosts(Long villagePosts) {
		this.villagePosts = villagePosts;
	}
	public Double getStatePostsPerc() {
		return statePostsPerc;
	}
	public void setStatePostsPerc(Double statePostsPerc) {
		this.statePostsPerc = statePostsPerc;
	}
	public Double getDistrictPostsPerc() {
		return districtPostsPerc;
	}
	public void setDistrictPostsPerc(Double districtPostsPerc) {
		this.districtPostsPerc = districtPostsPerc;
	}
	public Double getConstncyPostsPerc() {
		return constncyPostsPerc;
	}
	public void setConstncyPostsPerc(Double constncyPostsPerc) {
		this.constncyPostsPerc = constncyPostsPerc;
	}
	public Double getMandalPostsPerc() {
		return mandalPostsPerc;
	}
	public void setMandalPostsPerc(Double mandalPostsPerc) {
		this.mandalPostsPerc = mandalPostsPerc;
	}
	public Double getCentralPostsPerc() {
		return centralPostsPerc;
	}
	public void setCentralPostsPerc(Double centralPostsPerc) {
		this.centralPostsPerc = centralPostsPerc;
	}
	public Double getVillagePostsPerc() {
		return villagePostsPerc;
	}
	public void setVillagePostsPerc(Double villagePostsPerc) {
		this.villagePostsPerc = villagePostsPerc;
	}
	public String getTotalPer() {
		return totalPer;
	}
	public void setTotalPer(String totalPer) {
		this.totalPer = totalPer;
	}
	public Long getOveralTotal() {
		return overalTotal;
	}
	public void setOveralTotal(Long overalTotal) {
		this.overalTotal = overalTotal;
	}
	
}
