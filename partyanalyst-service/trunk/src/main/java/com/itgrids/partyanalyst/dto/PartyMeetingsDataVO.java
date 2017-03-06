package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartyMeetingsDataVO implements Serializable{
	
	private Long   id;
	private String name;
	private String locationLevelName;
	private Long   totalCount = 0l;
	private Long   conductedCount=0l;
	
	private Double conductedPerc = 0.0;
	private String requiredName;
	
	private Long noOfMeetings=0l;
	private Long invitedCount=0l;
	private Long attendedCount=0l;
	private Long lateAttendedCount=0L;
	private Long invitteeAttendedCount=0l;
	private Long notAttendedCount=0l;
	
	private Double invitedPerc=0.0;
	private Double attendedPerc=0.0;
	private Double lateattendedPerc=0.0;
	private Double inviteeAttendedPerc=0.0;
	private Double notAttendedPerc=0.0;
	private List<PartyMeetingsDataVO> subList1 = new ArrayList<PartyMeetingsDataVO>(0);
	private List<PartyMeetingsDataVO> subList2 = new ArrayList<PartyMeetingsDataVO>(0);
	private List<PartyMeetingsDataVO> subList3 = new ArrayList<PartyMeetingsDataVO>(0);
	private PartyMeetingsDataVO subVO;
	
	private List<PartyMeetingsDataVO> districtList;
	
	private Long allSessionAttendedCnt=0l;
	private Long allSessionLateAttendedCnt=0l;
	private Long allSessionAbsentCnt=0l;
	private Double allSessionAttendedPer = 0.0d;
	private Double allSessionLateAttendedCntPer = 0.0d;
	private Double allSessionAbsentCntper = 0.0d;
	private Long lateAttendedCnt=0l;
	private Double lateAttendedCntPer = 0.0d;
	
	private String startTime;
	private String endTime;
	private String lateTime;
	private Long group;
	private Long partyMeetingId;
	
	private Long nonInviteeCount=0L;
	private Double nonInviteePerc = 0.0d;
	
	private Long allSessionNonInviteeAttendedCnt=0l;
	private Double allSessionNonInviteeAttendedPer=0.0d;
	
	private String isLate;
	private String attendedTime;
	private Long districtId;
	private Long count = 0l;
	private Long constituencyId;
	private Long mandalId;
	private Long panchayatId;
	private String path;
	private Long levelId;
	
	
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getIsLate() {
		return isLate;
	}
	public void setIsLate(String isLate) {
		this.isLate = isLate;
	}
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	public Long getNonInviteeCount() {
		return nonInviteeCount;
	}
	public void setNonInviteeCount(Long nonInviteeCount) {
		this.nonInviteeCount = nonInviteeCount;
	}
	public Double getNonInviteePerc() {
		return nonInviteePerc;
	}
	public void setNonInviteePerc(Double nonInviteePerc) {
		this.nonInviteePerc = nonInviteePerc;
	}
	public Double getLateattendedPerc() {
		return lateattendedPerc;
	}
	public void setLateattendedPerc(Double lateattendedPerc) {
		this.lateattendedPerc = lateattendedPerc;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}
	public Long getLateAttendedCount() {
		return lateAttendedCount;
	}
	public void setLateAttendedCount(Long lateAttendedCount) {
		this.lateAttendedCount = lateAttendedCount;
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
	public String getLocationLevelName() {
		return locationLevelName;
	}
	public void setLocationLevelName(String locationLevelName) {
		this.locationLevelName = locationLevelName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Double getConductedPerc() {
		return conductedPerc;
	}
	public void setConductedPerc(Double conductedPerc) {
		this.conductedPerc = conductedPerc;
	}
	public String getRequiredName() {
		return requiredName;
	}
	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}
	public Long getNoOfMeetings() {
		return noOfMeetings;
	}
	public void setNoOfMeetings(Long noOfMeetings) {
		this.noOfMeetings = noOfMeetings;
	}
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getInvitteeAttendedCount() {
		return invitteeAttendedCount;
	}
	public void setInvitteeAttendedCount(Long invitteeAttendedCount) {
		this.invitteeAttendedCount = invitteeAttendedCount;
	}
	public Long getNotAttendedCount() {
		return notAttendedCount;
	}
	public void setNotAttendedCount(Long notAttendedCount) {
		this.notAttendedCount = notAttendedCount;
	}
	public Double getInvitedPerc() {
		return invitedPerc;
	}
	public void setInvitedPerc(Double invitedPerc) {
		this.invitedPerc = invitedPerc;
	}
	public Double getAttendedPerc() {
		return attendedPerc;
	}
	public void setAttendedPerc(Double attendedPerc) {
		this.attendedPerc = attendedPerc;
	}
	public Double getInviteeAttendedPerc() {
		return inviteeAttendedPerc;
	}
	public void setInviteeAttendedPerc(Double inviteeAttendedPerc) {
		this.inviteeAttendedPerc = inviteeAttendedPerc;
	}
	public Double getNotAttendedPerc() {
		return notAttendedPerc;
	}
	public void setNotAttendedPerc(Double notAttendedPerc) {
		this.notAttendedPerc = notAttendedPerc;
	}
	public PartyMeetingsDataVO getSubVO() {
		return subVO;
	}
	public void setSubVO(PartyMeetingsDataVO subVO) {
		this.subVO = subVO;
	}
	public List<PartyMeetingsDataVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<PartyMeetingsDataVO> subList1) {
		this.subList1 = subList1;
	}
	public List<PartyMeetingsDataVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<PartyMeetingsDataVO> subList2) {
		this.subList2 = subList2;
	}
	public List<PartyMeetingsDataVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<PartyMeetingsDataVO> districtList) {
		this.districtList = districtList;
	}
	public Long getAllSessionAttendedCnt() {
		return allSessionAttendedCnt;
	}
	public void setAllSessionAttendedCnt(Long allSessionAttendedCnt) {
		this.allSessionAttendedCnt = allSessionAttendedCnt;
	}
	public Long getAllSessionLateAttendedCnt() {
		return allSessionLateAttendedCnt;
	}
	public void setAllSessionLateAttendedCnt(Long allSessionLateAttendedCnt) {
		this.allSessionLateAttendedCnt = allSessionLateAttendedCnt;
	}
	public Long getAllSessionAbsentCnt() {
		return allSessionAbsentCnt;
	}
	public void setAllSessionAbsentCnt(Long allSessionAbsentCnt) {
		this.allSessionAbsentCnt = allSessionAbsentCnt;
	}
	public Long getLateAttendedCnt() {
		return lateAttendedCnt;
	}
	public void setLateAttendedCnt(Long lateAttendedCnt) {
		this.lateAttendedCnt = lateAttendedCnt;
	}
	public Double getLateAttendedCntPer() {
		return lateAttendedCntPer;
	}
	public void setLateAttendedCntPer(Double lateAttendedCntPer) {
		this.lateAttendedCntPer = lateAttendedCntPer;
	}
	public Double getAllSessionAttendedPer() {
		return allSessionAttendedPer;
	}
	public void setAllSessionAttendedPer(Double allSessionAttendedPer) {
		this.allSessionAttendedPer = allSessionAttendedPer;
	}
	public Double getAllSessionLateAttendedCntPer() {
		return allSessionLateAttendedCntPer;
	}
	public void setAllSessionLateAttendedCntPer(Double allSessionLateAttendedCntPer) {
		this.allSessionLateAttendedCntPer = allSessionLateAttendedCntPer;
	}
	public Double getAllSessionAbsentCntper() {
		return allSessionAbsentCntper;
	}
	public void setAllSessionAbsentCntper(Double allSessionAbsentCntper) {
		this.allSessionAbsentCntper = allSessionAbsentCntper;
	}
	public Long getAllSessionNonInviteeAttendedCnt() {
		return allSessionNonInviteeAttendedCnt;
	}
	public void setAllSessionNonInviteeAttendedCnt(
			Long allSessionNonInviteeAttendedCnt) {
		this.allSessionNonInviteeAttendedCnt = allSessionNonInviteeAttendedCnt;
	}
	public Double getAllSessionNonInviteeAttendedPer() {
		return allSessionNonInviteeAttendedPer;
	}
	public void setAllSessionNonInviteeAttendedPer(
			Double allSessionNonInviteeAttendedPer) {
		this.allSessionNonInviteeAttendedPer = allSessionNonInviteeAttendedPer;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public Long getGroup() {
		return group;
	}
	public void setGroup(Long group) {
		this.group = group;
	}
	public List<PartyMeetingsDataVO> getSubList3() {
		return subList3;
	}
	public void setSubList3(List<PartyMeetingsDataVO> subList3) {
		this.subList3 = subList3;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	} 
	
}
