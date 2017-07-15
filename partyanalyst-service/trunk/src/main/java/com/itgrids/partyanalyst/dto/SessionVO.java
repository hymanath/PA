package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public class SessionVO implements Serializable{
	
	private Long id;
	private String name;
	private Long plannedCount =0l;
	private Long conductedCount=0l;
	private Long notConductedCount = 0l;
	private Long invitedCount =0l;
	private Long lateCount =0l;
	private Long absentCount = 0l;
	private Long inviteeAttendedCount =0l;
	private Long nonInviteeCount =0l;
	private Long imagesCovered =0l;
	private Long imagesCount=0l;
	private String lateTime;
	private String attendedTime;
	private Set<Long> inviteeAttendedCadreIdsList = new HashSet<Long>();
	private Set<Long> nonInviteeAttendedCadreIdsList = new HashSet<Long>();
	private String isLate;
	private String uploadedTime;
	private String upLoadedDate;
	private String imagePath;
	
	private String meetingType;
	private Long meetingTypeId;
	private String meetingName;
	private Long districtId;
	private String districtName;
	private String conductedDate;
	private String remarks;
	private Long constituencyId;
    private String constituencyName;
    private Long mandalTwnDivisionId;
    private String mandalTwnDivision;
    private Long sessionId;
    private String sessionName;
    private Long partyMeetingId;
    private Set<Long> inviteedCadreIdsList = new HashSet<Long>();
    private Set<Long> attendedCadreIdsList = new HashSet<Long>();
    private Set<Long> absentCadreIdsList = new HashSet<Long>();
    private List<SessionVO> subList = new ArrayList<SessionVO>();
    private List<SessionVO> imagesList = new ArrayList<SessionVO>();
    
    private List<KeyValueVO> invitedDesgnList = new ArrayList<KeyValueVO>();
    private List<KeyValueVO> attendedDesgnList = new ArrayList<KeyValueVO>();
    private List<KeyValueVO> inviteeAttndDesgnList = new ArrayList<KeyValueVO>();
    private List<KeyValueVO> absentDesgnList = new ArrayList<KeyValueVO>();
    private List<KeyValueVO> nonInviteeDesgnList = new ArrayList<KeyValueVO>();
    
    
    private Long partyMeetingSessionId;
    private Long sessionTypeId;
    private String startTime;
    private String endTime;
    
    
	public List<KeyValueVO> getInvitedDesgnList() {
		return invitedDesgnList;
	}
	public void setInvitedDesgnList(List<KeyValueVO> invitedDesgnList) {
		this.invitedDesgnList = invitedDesgnList;
	}
	public List<KeyValueVO> getAttendedDesgnList() {
		return attendedDesgnList;
	}
	public void setAttendedDesgnList(List<KeyValueVO> attendedDesgnList) {
		this.attendedDesgnList = attendedDesgnList;
	}
	public List<KeyValueVO> getInviteeAttndDesgnList() {
		return inviteeAttndDesgnList;
	}
	public void setInviteeAttndDesgnList(List<KeyValueVO> inviteeAttndDesgnList) {
		this.inviteeAttndDesgnList = inviteeAttndDesgnList;
	}
	public List<KeyValueVO> getAbsentDesgnList() {
		return absentDesgnList;
	}
	public void setAbsentDesgnList(List<KeyValueVO> absentDesgnList) {
		this.absentDesgnList = absentDesgnList;
	}
	public List<KeyValueVO> getNonInviteeDesgnList() {
		return nonInviteeDesgnList;
	}
	public void setNonInviteeDesgnList(List<KeyValueVO> nonInviteeDesgnList) {
		this.nonInviteeDesgnList = nonInviteeDesgnList;
	}
	public List<SessionVO> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<SessionVO> imagesList) {
		this.imagesList = imagesList;
	}
	public SessionVO(){}
	public SessionVO(Long sessionId, String sessionName){
		this.sessionId=sessionId;
		this.sessionName = sessionName;
	}
	public List<SessionVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SessionVO> subList) {
		this.subList = subList;
	}
	public Set<Long> getAbsentCadreIdsList() {
		return absentCadreIdsList;
	}
	public void setAbsentCadreIdsList(Set<Long> absentCadreIdsList) {
		this.absentCadreIdsList = absentCadreIdsList;
	}
	public Set<Long> getAttendedCadreIdsList() {
		return attendedCadreIdsList;
	}
	public void setAttendedCadreIdsList(Set<Long> attendedCadreIdsList) {
		this.attendedCadreIdsList = attendedCadreIdsList;
	}
	public Set<Long> getInviteedCadreIdsList() {
		return inviteedCadreIdsList;
	}
	public void setInviteedCadreIdsList(Set<Long> inviteedCadreIdsList) {
		this.inviteedCadreIdsList = inviteedCadreIdsList;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	public Long getInviteeAttendedCount() {
		return inviteeAttendedCount;
	}
	public void setInviteeAttendedCount(Long inviteeAttendedCount) {
		this.inviteeAttendedCount = inviteeAttendedCount;
	}
	public Set<Long> getInviteeAttendedCadreIdsList() {
		return inviteeAttendedCadreIdsList;
	}
	public void setInviteeAttendedCadreIdsList(Set<Long> inviteeAttendedCadreIdsList) {
		this.inviteeAttendedCadreIdsList = inviteeAttendedCadreIdsList;
	}
	public Set<Long> getNonInviteeAttendedCadreIdsList() {
		return nonInviteeAttendedCadreIdsList;
	}
	public void setNonInviteeAttendedCadreIdsList(
			Set<Long> nonInviteeAttendedCadreIdsList) {
		this.nonInviteeAttendedCadreIdsList = nonInviteeAttendedCadreIdsList;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
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
	public Long getInvitedCount() {
		return invitedCount;
	}
	public void setInvitedCount(Long invitedCount) {
		this.invitedCount = invitedCount;
	}
	public Long getLateCount() {
		return lateCount;
	}
	public void setLateCount(Long lateCount) {
		this.lateCount = lateCount;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}
	public Long getNonInviteeCount() {
		return nonInviteeCount;
	}
	public void setNonInviteeCount(Long nonInviteeCount) {
		this.nonInviteeCount = nonInviteeCount;
	}
	public Long getImagesCovered() {
		return imagesCovered;
	}
	public void setImagesCovered(Long imagesCovered) {
		this.imagesCovered = imagesCovered;
	}
	public Long getImagesCount() {
		return imagesCount;
	}
	public void setImagesCount(Long imagesCount) {
		this.imagesCount = imagesCount;
	}
	public String getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(String uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	public String getUpLoadedDate() {
		return upLoadedDate;
	}
	public void setUpLoadedDate(String upLoadedDate) {
		this.upLoadedDate = upLoadedDate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public Long getPartyMeetingSessionId() {
		return partyMeetingSessionId;
	}
	public void setPartyMeetingSessionId(Long partyMeetingSessionId) {
		this.partyMeetingSessionId = partyMeetingSessionId;
	}
	public Long getSessionTypeId() {
		return sessionTypeId;
	}
	public void setSessionTypeId(Long sessionTypeId) {
		this.sessionTypeId = sessionTypeId;
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
	
			
	
}
