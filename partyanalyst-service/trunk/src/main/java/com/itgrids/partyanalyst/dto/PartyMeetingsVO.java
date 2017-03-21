package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
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

	private String name;
	private String meetingName;
	private String districtName;
	private String meetingType;
	private String conductedDate;
	private String remarks;
	
	private Long plannedCount = 0l;
	private Long conductedCount = 0l;
	private Long notConductedCount = 0l;
	
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
	
	private Integer year;
	private String month;
	private String updatedTime;
	private Long meetingCount=0l;
	private Long commentCount=0l;
	private Long updationCount = 0l;

	private Long invitedCount=0L;
	private Long nonInviteesCount=0L;
	private Long absentCount =0L;
	
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
	
}
