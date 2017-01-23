package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class ActivityVO implements Comparable<ActivityVO>{
	
	private Long activityTypeId;
	private Long activityLevelId;
	private Long activityNameId;
	private String plannedDate;
	private String ConductedDate;
	private Long locationLevel;
	private Long locationValue;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long localElectionBodyId;
	private Long villageId;
	private Long wardId;
	private Long attendedCount=0l;
	private String remarks;
	private String startDate;
	private String endDate;
	private List<ActivityVO> activityVoList = new ArrayList<ActivityVO>(0);
	private List<ActivityAttendanceInfoVO> activityAttendanceInfoVOList = new ArrayList<ActivityAttendanceInfoVO>(0);
	private Map<String,ActivityVO>  activityMap = new LinkedHashMap<String,ActivityVO>(0);
	private Long id;
	private String name;
	
	private Long totalCount;
	private Long plannedCount;
	private Long notPlannedCount;
	
	private Long ivrcovered;
	private String ivrcoveredPerc;
	private Long ivrNotPlanned;
	private Long ivrTotal;
	private String ivrTotalPerc;
	
	private Long infoCellcovered;
	private String infoCellcoveredPerc;
	private Long infoCellNotPlanned;
	private Long infoCellTotal;
	private String infoCellTotalPerc;
	
	private Long whatsAppCovered;
	private String whatsAppCoveredPerc;
	private Long imagesCount;
	
	private Long conductedCount;
	private Long nonConductedCount;
	private String percentage;
	private String notPlannedPerc;
	private List<ActivityVO> questionList = new ArrayList<ActivityVO>(0);
	private Long questionId;
	private String question;
	private Long optionId;
	private String option;
	private Long optionTypeId;
	private String optionType;
	private List<ActivityVO> optionsList = new ArrayList<ActivityVO>(0);
	private List<Long> locationIds = new ArrayList<Long>(0);
	
	private String others;
	private Long count;
	
	private Long userId;
	private Long activityScopeId;
	private Long scopeValue;
	private Long activityLocationInfoId;
	private String ivrStatus;
	private String isLocation;
	private String isAttended;
	private String attendendLocation;
	private Long imagesCnt =0l;
	private Long orderNo;
	private String status;
	private Long invitteeCnt = 0l;
	private Long attendeeCnt = 0l;
	private Long abscentCnt = 0l;
	private Long day=0l;
	private String dateStr;
	private Long attributeId;
	private Long hasAttendenceCount =0L;
	private Long hasConductedCount =0L;
	private String activityLevelName;
	private Long activityQuenaryId;
	private String remainingPerc;
	
	public Long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getHasConductedCount() {
		return hasConductedCount;
	}
	public void setHasConductedCount(Long hasConductedCount) {
		this.hasConductedCount = hasConductedCount;
	}
	public Long getHasAttendenceCount() {
		return hasAttendenceCount;
	}
	public void setHasAttendenceCount(Long hasAttendenceCount) {
		this.hasAttendenceCount = hasAttendenceCount;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public Long getInvitteeCnt() {
		return invitteeCnt;
	}
	public void setInvitteeCnt(Long invitteeCnt) {
		this.invitteeCnt = invitteeCnt;
	}
	public Long getAttendeeCnt() {
		return attendeeCnt;
	}
	public void setAttendeeCnt(Long attendeeCnt) {
		this.attendeeCnt = attendeeCnt;
	}
	public Long getAbscentCnt() {
		return abscentCnt;
	}
	public void setAbscentCnt(Long abscentCnt) {
		this.abscentCnt = abscentCnt;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getImagesCnt() {
		return imagesCnt;
	}
	public void setImagesCnt(Long imagesCnt) {
		this.imagesCnt = imagesCnt;
	}
	public String getAttendendLocation() {
		return attendendLocation;
	}
	public void setAttendendLocation(String attendendLocation) {
		this.attendendLocation = attendendLocation;
	}
	public String getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(String isLocation) {
		this.isLocation = isLocation;
	}
	public String getIsAttended() {
		return isAttended;
	}
	public void setIsAttended(String isAttended) {
		this.isAttended = isAttended;
	}
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	public String getIvrStatus() {
		return ivrStatus;
	}
	public void setIvrStatus(String ivrStatus) {
		this.ivrStatus = ivrStatus;
	}
	public List<ActivityAttendanceInfoVO> getActivityAttendanceInfoVOList() {
		return activityAttendanceInfoVOList;
	}
	public void setActivityAttendanceInfoVOList(
			List<ActivityAttendanceInfoVO> activityAttendanceInfoVOList) {
		this.activityAttendanceInfoVOList = activityAttendanceInfoVOList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<ActivityVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<ActivityVO> optionsList) {
		this.optionsList = optionsList;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public List<ActivityVO> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<ActivityVO> questionList) {
		this.questionList = questionList;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getIvrTotalPerc() {
		return ivrTotalPerc;
	}
	public void setIvrTotalPerc(String ivrTotalPerc) {
		this.ivrTotalPerc = ivrTotalPerc;
	}
	public String getInfoCellTotalPerc() {
		return infoCellTotalPerc;
	}
	public void setInfoCellTotalPerc(String infoCellTotalPerc) {
		this.infoCellTotalPerc = infoCellTotalPerc;
	}
	public Long getIvrcovered() {
		return ivrcovered;
	}
	public void setIvrcovered(Long ivrcovered) {
		this.ivrcovered = ivrcovered;
	}
	public String getIvrcoveredPerc() {
		return ivrcoveredPerc;
	}
	public void setIvrcoveredPerc(String ivrcoveredPerc) {
		this.ivrcoveredPerc = ivrcoveredPerc;
	}
	public Long getIvrNotPlanned() {
		return ivrNotPlanned;
	}
	public void setIvrNotPlanned(Long ivrNotPlanned) {
		this.ivrNotPlanned = ivrNotPlanned;
	}
	public Long getIvrTotal() {
		return ivrTotal;
	}
	public void setIvrTotal(Long ivrTotal) {
		this.ivrTotal = ivrTotal;
	}
	public Long getInfoCellcovered() {
		return infoCellcovered;
	}
	public void setInfoCellcovered(Long infoCellcovered) {
		this.infoCellcovered = infoCellcovered;
	}
	public String getInfoCellcoveredPerc() {
		return infoCellcoveredPerc;
	}
	public void setInfoCellcoveredPerc(String infoCellcoveredPerc) {
		this.infoCellcoveredPerc = infoCellcoveredPerc;
	}
	public Long getInfoCellNotPlanned() {
		return infoCellNotPlanned;
	}
	public void setInfoCellNotPlanned(Long infoCellNotPlanned) {
		this.infoCellNotPlanned = infoCellNotPlanned;
	}
	public Long getInfoCellTotal() {
		return infoCellTotal;
	}
	public void setInfoCellTotal(Long infoCellTotal) {
		this.infoCellTotal = infoCellTotal;
	}
	public Long getWhatsAppCovered() {
		return whatsAppCovered;
	}
	public void setWhatsAppCovered(Long whatsAppCovered) {
		this.whatsAppCovered = whatsAppCovered;
	}
	public String getWhatsAppCoveredPerc() {
		return whatsAppCoveredPerc;
	}
	public void setWhatsAppCoveredPerc(String whatsAppCoveredPerc) {
		this.whatsAppCoveredPerc = whatsAppCoveredPerc;
	}
	public Long getImagesCount() {
		return imagesCount;
	}
	public void setImagesCount(Long imagesCount) {
		this.imagesCount = imagesCount;
	}
	public String getNotPlannedPerc() {
		return notPlannedPerc;
	}
	public void setNotPlannedPerc(String notPlannedPerc) {
		this.notPlannedPerc = notPlannedPerc;
	}
	public Map<String, ActivityVO> getActivityMap() {
		return activityMap;
	}
	public void setActivityMap(Map<String, ActivityVO> activityMap) {
		this.activityMap = activityMap;
	}
	
	public Long getNotPlannedCount() {
		return notPlannedCount;
	}
	public void setNotPlannedCount(Long notPlannedCount) {
		this.notPlannedCount = notPlannedCount;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<ActivityVO> getActivityVoList() {
		return activityVoList;
	}
	public void setActivityVoList(List<ActivityVO> activityVoList) {
		this.activityVoList = activityVoList;
	}
	public Long getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Long activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public Long getActivityLevelId() {
		return activityLevelId;
	}
	public void setActivityLevelId(Long activityLevelId) {
		this.activityLevelId = activityLevelId;
	}
	public Long getActivityNameId() {
		return activityNameId;
	}
	public void setActivityNameId(Long activityNameId) {
		this.activityNameId = activityNameId;
	}
	public String getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(String plannedDate) {
		this.plannedDate = plannedDate;
	}
	public String getConductedDate() {
		return ConductedDate;
	}
	public void setConductedDate(String conductedDate) {
		ConductedDate = conductedDate;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPlannedCount() {
		return plannedCount;
	}
	public void setPlannedCount(Long plannedCount) {
		this.plannedCount = plannedCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Long getNonConductedCount() {
		return nonConductedCount;
	}
	public void setNonConductedCount(Long nonConductedCount) {
		this.nonConductedCount = nonConductedCount;
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

	public int compareTo(ActivityVO obj) {
		if(obj instanceof ActivityVO){
			ActivityVO vo = (ActivityVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getActivityLevelName() {
		return activityLevelName;
	}
	public void setActivityLevelName(String activityLevelName) {
		this.activityLevelName = activityLevelName;
	}
	public Long getActivityQuenaryId() {
		return activityQuenaryId;
	}
	public void setActivityQuenaryId(Long activityQuenaryId) {
		this.activityQuenaryId = activityQuenaryId;
	}
	public String getRemainingPerc() {
		return remainingPerc;
	}
	public void setRemainingPerc(String remainingPerc) {
		this.remainingPerc = remainingPerc;
	}
	
}
