package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PartyMeetingsVO implements Serializable{
	
	private Long id;
	private Long districtId;
	private Long meetingTypeId;
    private Long mandalTwnDivisionId;
    private String mandalTwnDivision;
    private Long constituencyId;
    private String constituencyName;
	private String teshilName;
	private Long teshilId;
	private Long panchayatId;
	private Long inviteeId;
	private Long meetingMainTypeId;
	private List<IdAndNameVO> invetteList= new ArrayList<IdAndNameVO>();

	private String name;
	private String meetingName;
	private String districtName;
	private String meetingType;
	private String conductedDate;
	private String remarks;
	private String tesilName;
	private String stateName;
	private String flage;
	private String isCondacted;
	
	
	private Long plannedCount = 0l;
	private Long conductedCount = 0l;
	private Long notConductedCount = 0l;
	private Long notUpdatedCount = 0L;
	private Date startDate;
	private Date endDate;
	/*For Core DashBoard  */
	private Long totalCount=0l;
	private Long mayBeCount=0l;
	private Double totalCountPer=0.0;
	private Double  conductedCountPer=0.0;
	private Double notConductedCountPer=0.0;
	private Double mayBeCountPer=0.0;
	private PartyMeetingsVO overAllVO;
	private List<PartyMeetingsVO> partyMettingsVOList;
	private Long totalMeetingCnt =0l;
	private Long totalNotUpdatedCnt=0l;
	private Double totalNotUpdatedCntPer=0.0;
	
	private Long totalCommentCnt=0l;
	private Long conductedCommentCnt=0l;
	private Long notConductedCommentCnt=0l;
	private Long mayBeCommentCnt=0l;
	private Long notUpdatedCommentCnt=0l;
	private Double totalCommentCntPer=0.0;
	private Double conductedCommentCntPer=0.0;
	private Double notConductedCommentCntPer=0.0;
	private Double mayBeCommentCntPer=0.0;
	private Double notUpdatedCommentCntPer=0.0;
	
	
	private List<PartyMeetingsVO> subList;
	private Map<Long,PartyMeetingsVO> subMap;
	private Map<String,PartyMeetingsVO> subMap1;
	private List<String> inviteeList;
	
	private Integer year;
	private String month;
	private String updatedTime;
	private Long meetingCount=0l;
	private Long commentCount=0l;
	private Long updationCount = 0l;

	private Long invitedCount=0L;
	private Long nonInviteesCount=0L;
	private Long absentCount =0L;
	private List<SessionVO> imagesList = new ArrayList<SessionVO>();
	private Long imagesCount = 0l;
	private Long yesCount =0l;
	private Long noCount =0l;
	private Long changedCount=0L;
	private Long villageId=0l;
	private Time startTime;
	private Time endTime;
	private Time lateTime;
	private String wardName;
	private Long inviteeAttendedCount = 0L;
	private Long attendedCount = 0L;
	private String meetingLevel;
	private String mobileNo;
	private Long userGroupId;
	private Long generalCount = 0L;
	private Long actionCount = 0L;
	private Long govtCount = 0L;
	private Long partyCount = 0L;
	private Long momDocumentCount = 0L;
	private Long totalMoms = 0L;
	private Double  generalCntPer=0.0;
	private Double actionCntPer=0.0;
	private Double  partyCntPer=0.0;
	private Double govtCntPer=0.0;
	private Long momCreatedCnt = 0L;
	private Long momInPrgCnt = 0L;
	private Long momCmpltedCnt = 0L;
	private Long momTotalStausCnt = 0L;
	private Double  momCreatedCntPer=0.0;
	private Double momInPrgCntPer=0.0;
	private Double  momCmpltedCntPer=0.0;
	private Long notUpdatedMomCount = 0L;
	private Double notUpdatedMomCntPer = 0.0;
	

	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Time getLateTime() {
		return lateTime;
	}
	public void setLateTime(Time lateTime) {
		this.lateTime = lateTime;
	}
	public Long getChangedCount() {
		return changedCount;
	}
	public void setChangedCount(Long changedCount) {
		this.changedCount = changedCount;
	}
	public Long getImagesCount() {
		return imagesCount;
	}
	public void setImagesCount(Long imagesCount) {
		this.imagesCount = imagesCount;
	}
	public List<SessionVO> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<SessionVO> imagesList) {
		this.imagesList = imagesList;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getNonInviteesCount() {
		return nonInviteesCount;
	}
	public void setNonInviteesCount(Long nonInviteesCount) {
		this.nonInviteesCount = nonInviteesCount;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
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
	public Long getNotConductedCount() {
		return notConductedCount;
	}
	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}
	public List<PartyMeetingsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PartyMeetingsVO> subList) {
		this.subList = subList;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Map<Long, PartyMeetingsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, PartyMeetingsVO> subMap) {
		this.subMap = subMap;
	}
	public Map<String, PartyMeetingsVO> getSubMap1() {
		return subMap1;
	}
	public void setSubMap1(Map<String, PartyMeetingsVO> subMap1) {
		this.subMap1 = subMap1;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMayBeCount() {
		return mayBeCount;
	}
	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}
	public Double getTotalCountPer() {
		return totalCountPer;
	}
	public void setTotalCountPer(Double totalCountPer) {
		this.totalCountPer = totalCountPer;
	}
	public Double getConductedCountPer() {
		return conductedCountPer;
	}
	public void setConductedCountPer(Double conductedCountPer) {
		this.conductedCountPer = conductedCountPer;
	}
	public Double getNotConductedCountPer() {
		return notConductedCountPer;
	}
	public void setNotConductedCountPer(Double notConductedCountPer) {
		this.notConductedCountPer = notConductedCountPer;
	}
	public Double getMayBeCountPer() {
		return mayBeCountPer;
	}
	public void setMayBeCountPer(Double mayBeCountPer) {
		this.mayBeCountPer = mayBeCountPer;
	}
	public PartyMeetingsVO getOverAllVO() {
		return overAllVO;
	}
	public void setOverAllVO(PartyMeetingsVO overAllVO) {
		this.overAllVO = overAllVO;
	}
	public List<PartyMeetingsVO> getPartyMettingsVOList() {
		if(partyMettingsVOList == null){
			partyMettingsVOList = new ArrayList<PartyMeetingsVO>();
		}
		return partyMettingsVOList;
	}
	public Long getTotalMeetingCnt() {
		return totalMeetingCnt;
	}
	public void setTotalMeetingCnt(Long totalMeetingCnt) {
		this.totalMeetingCnt = totalMeetingCnt;
	}
	public Long getTotalNotUpdatedCnt() {
		return totalNotUpdatedCnt;
	}
	public void setTotalNotUpdatedCnt(Long totalNotUpdatedCnt) {
		this.totalNotUpdatedCnt = totalNotUpdatedCnt;
	}
	public Double getTotalNotUpdatedCntPer() {
		return totalNotUpdatedCntPer;
	}
	public void setTotalNotUpdatedCntPer(Double totalNotUpdatedCntPer) {
		this.totalNotUpdatedCntPer = totalNotUpdatedCntPer;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Long getTotalCommentCnt() {
		return totalCommentCnt;
	}
	public void setTotalCommentCnt(Long totalCommentCnt) {
		this.totalCommentCnt = totalCommentCnt;
	}
	public Long getConductedCommentCnt() {
		return conductedCommentCnt;
	}
	public void setConductedCommentCnt(Long conductedCommentCnt) {
		this.conductedCommentCnt = conductedCommentCnt;
	}
	public Long getNotConductedCommentCnt() {
		return notConductedCommentCnt;
	}
	public void setNotConductedCommentCnt(Long notConductedCommentCnt) {
		this.notConductedCommentCnt = notConductedCommentCnt;
	}
	public Long getMayBeCommentCnt() {
		return mayBeCommentCnt;
	}
	public void setMayBeCommentCnt(Long mayBeCommentCnt) {
		this.mayBeCommentCnt = mayBeCommentCnt;
	}
	public Long getNotUpdatedCommentCnt() {
		return notUpdatedCommentCnt;
	}
	public void setNotUpdatedCommentCnt(Long notUpdatedCommentCnt) {
		this.notUpdatedCommentCnt = notUpdatedCommentCnt;
	}
	public Double getTotalCommentCntPer() {
		return totalCommentCntPer;
	}
	public void setTotalCommentCntPer(Double totalCommentCntPer) {
		this.totalCommentCntPer = totalCommentCntPer;
	}
	public Double getConductedCommentCntPer() {
		return conductedCommentCntPer;
	}
	public void setConductedCommentCntPer(Double conductedCommentCntPer) {
		this.conductedCommentCntPer = conductedCommentCntPer;
	}
	public Double getNotConductedCommentCntPer() {
		return notConductedCommentCntPer;
	}
	public void setNotConductedCommentCntPer(Double notConductedCommentCntPer) {
		this.notConductedCommentCntPer = notConductedCommentCntPer;
	}
	public Double getMayBeCommentCntPer() {
		return mayBeCommentCntPer;
	}
	public void setMayBeCommentCntPer(Double mayBeCommentCntPer) {
		this.mayBeCommentCntPer = mayBeCommentCntPer;
	}
	public Double getNotUpdatedCommentCntPer() {
		return notUpdatedCommentCntPer;
	}
	public void setNotUpdatedCommentCntPer(Double notUpdatedCommentCntPer) {
		this.notUpdatedCommentCntPer = notUpdatedCommentCntPer;
	}
	public void setPartyMettingsVOList(List<PartyMeetingsVO> partyMettingsVOList) {
		this.partyMettingsVOList = partyMettingsVOList;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getMeetingTypeId() {
		return meetingTypeId;
	}
	public void setMeetingTypeId(Long meetingTypeId) {
		this.meetingTypeId = meetingTypeId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getConductedDate() {
		return conductedDate;
	}
	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getMeetingCount() {
		return meetingCount;
	}
	public void setMeetingCount(Long meetingCount) {
		this.meetingCount = meetingCount;
	}
	public Long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}
	public Long getMandalTwnDivisionId() {
		return mandalTwnDivisionId;
	}
	public void setMandalTwnDivisionId(Long mandalTwnDivisionId) {
		this.mandalTwnDivisionId = mandalTwnDivisionId;
	}
	public String getMandalTwnDivision() {
		return mandalTwnDivision;
	}
	public void setMandalTwnDivision(String mandalTwnDivision) {
		this.mandalTwnDivision = mandalTwnDivision;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getUpdationCount() {
		return updationCount;
	}
	public void setUpdationCount(Long updationCount) {
		this.updationCount = updationCount;
	}
	public Long getYesCount() {
		return yesCount;
	}
	public void setYesCount(Long yesCount) {
		this.yesCount = yesCount;
	}
	public Long getNoCount() {
		return noCount;
	}
	public void setNoCount(Long noCount) {
		this.noCount = noCount;
	}

	public String getTeshilName() {
		return teshilName;
	}
	public void setTeshilName(String teshilName) {
		this.teshilName = teshilName;
	}
	public Long getTeshilId() {
		return teshilId;
	}
	public void setTeshilId(Long teshilId) {
		this.teshilId = teshilId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public List<String> getInviteeList() {
		return inviteeList;
	}
	public void setInviteeList(List<String> inviteeList) {
		this.inviteeList = inviteeList;
	}
	public Long getInviteeId() {
		return inviteeId;
	}
	public void setInviteeId(Long inviteeId) {
		this.inviteeId = inviteeId;
	}
	public Long getMeetingMainTypeId() {
		return meetingMainTypeId;
	}
	public void setMeetingMainTypeId(Long meetingMainTypeId) {
		this.meetingMainTypeId = meetingMainTypeId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTesilName() {
		return tesilName;
	}
	public void setTesilName(String tesilName) {
		this.tesilName = tesilName;
	}
	public String getFlage() {
		return flage;
	}
	public void setFlage(String flage) {
		this.flage = flage;
	}
	public String getIsCondacted() {
		return isCondacted;
	}
	public void setIsCondacted(String isCondacted) {
		this.isCondacted = isCondacted;
	}
	public List<IdAndNameVO> getInvetteList() {
		return invetteList;
	}
	public void setInvetteList(List<IdAndNameVO> invetteList) {
		this.invetteList = invetteList;
	}
	public Long getNotUpdatedCount() {
		return notUpdatedCount;
	}
	public void setNotUpdatedCount(Long notUpdatedCount) {
		this.notUpdatedCount = notUpdatedCount;
	}
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public String getMeetingLevel() {
		return meetingLevel;
	}
	public void setMeetingLevel(String meetingLevel) {
		this.meetingLevel = meetingLevel;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}
	public Long getGeneralCount() {
		return generalCount;
	}
	public void setGeneralCount(Long generalCount) {
		this.generalCount = generalCount;
	}
	public Long getActionCount() {
		return actionCount;
	}
	public void setActionCount(Long actionCount) {
		this.actionCount = actionCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getMomDocumentCount() {
		return momDocumentCount;
	}
	public void setMomDocumentCount(Long momDocumentCount) {
		this.momDocumentCount = momDocumentCount;
	}
	public Long getTotalMoms() {
		return totalMoms;
	}
	public void setTotalMoms(Long totalMoms) {
		this.totalMoms = totalMoms;
	}
	public Double getGeneralCntPer() {
		return generalCntPer;
	}
	public void setGeneralCntPer(Double generalCntPer) {
		this.generalCntPer = generalCntPer;
	}
	public Double getActionCntPer() {
		return actionCntPer;
	}
	public void setActionCntPer(Double actionCntPer) {
		this.actionCntPer = actionCntPer;
	}
	public Double getPartyCntPer() {
		return partyCntPer;
	}
	public void setPartyCntPer(Double partyCntPer) {
		this.partyCntPer = partyCntPer;
	}
	public Double getGovtCntPer() {
		return govtCntPer;
	}
	public void setGovtCntPer(Double govtCntPer) {
		this.govtCntPer = govtCntPer;
	}
	public Long getMomCreatedCnt() {
		return momCreatedCnt;
	}
	public void setMomCreatedCnt(Long momCreatedCnt) {
		this.momCreatedCnt = momCreatedCnt;
	}
	public Long getMomInPrgCnt() {
		return momInPrgCnt;
	}
	public void setMomInPrgCnt(Long momInPrgCnt) {
		this.momInPrgCnt = momInPrgCnt;
	}
	public Long getMomCmpltedCnt() {
		return momCmpltedCnt;
	}
	public void setMomCmpltedCnt(Long momCmpltedCnt) {
		this.momCmpltedCnt = momCmpltedCnt;
	}
	public Long getMomTotalStausCnt() {
		return momTotalStausCnt;
	}
	public void setMomTotalStausCnt(Long momTotalStausCnt) {
		this.momTotalStausCnt = momTotalStausCnt;
	}
	public Double getMomCreatedCntPer() {
		return momCreatedCntPer;
	}
	public void setMomCreatedCntPer(Double momCreatedCntPer) {
		this.momCreatedCntPer = momCreatedCntPer;
	}
	public Double getMomInPrgCntPer() {
		return momInPrgCntPer;
	}
	public void setMomInPrgCntPer(Double momInPrgCntPer) {
		this.momInPrgCntPer = momInPrgCntPer;
	}
	public Double getMomCmpltedCntPer() {
		return momCmpltedCntPer;
	}
	public void setMomCmpltedCntPer(Double momCmpltedCntPer) {
		this.momCmpltedCntPer = momCmpltedCntPer;
	}
	public Long getNotUpdatedMomCount() {
		return notUpdatedMomCount;
	}
	public void setNotUpdatedMomCount(Long notUpdatedMomCount) {
		this.notUpdatedMomCount = notUpdatedMomCount;
	}
	public Double getNotUpdatedMomCntPer() {
		return notUpdatedMomCntPer;
	}
	public void setNotUpdatedMomCntPer(Double notUpdatedMomCntPer) {
		this.notUpdatedMomCntPer = notUpdatedMomCntPer;
	}
	

	
}
