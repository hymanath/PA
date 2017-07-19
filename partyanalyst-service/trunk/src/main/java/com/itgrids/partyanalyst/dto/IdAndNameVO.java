package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Swadhin
 *
 */
public class IdAndNameVO {
	private Long id;
	private String name;
	private Long inviteeCount = 0l;
	private Long attenteeCount = 0l;
	private Long inviteeAttendeeCnt = 0l;
	private String imagePathStr;
	private String mobileNumber;
	private List<IdAndNameVO> issueTypes ;
	private String actualMobNumber;
	private List<IdAndNameVO> distList ;
	private List<Long> distIdList;
	private List<Long> constIdList;
	private Long apTotal = 0l;
	private Long tsTotal = 0l;
	private Long apNow = 0l;
	private Long tsNow = 0l;
	private String startTime;
	private String endTime;
	private Double per2016 = 0.00;
	private String isCsd;
	private Long tdpcadreId;
	private Long partyId;
	private Long sessionNo;
	private String partyName;
	private String question;
	private Long countOfActivityLocationInfo;
	private Long locationId;
	private String locationName;
	private String perc;
	private String remainingPerc;
	private Long imagesCovered=0l;
	private Long totalImages=0l;
	
	private Long yesCount=0L;
	private Long noCount =0L;
	private Long mayBecount =0L;
	private String yesPerc;
	private String noPerc;
	private String mayPerc;
	private String flag;
	private String membershipNo;
	
	private List<String> enrollmentYears = new ArrayList<String>();
	private List<IdAndNameVO>NotAttendanceList=new ArrayList<IdAndNameVO>();
	private List<IdAndNameVO>AttendanceList=new ArrayList<IdAndNameVO>();
	private List<IdAndNameVO> nonInviteeAttendancList=new ArrayList<IdAndNameVO>();
	
	
	
	
	
	public List<IdAndNameVO> getNotAttendanceList() {
		return NotAttendanceList;
	}
	public void setNotAttendanceList(List<IdAndNameVO> notAttendanceList) {
		NotAttendanceList = notAttendanceList;
	}
	public List<IdAndNameVO> getAttendanceList() {
		return AttendanceList;
	}
	public void setAttendanceList(List<IdAndNameVO> attendanceList) {
		AttendanceList = attendanceList;
	}
	public List<String> getEnrollmentYears() {
		return enrollmentYears;
	}
	public void setEnrollmentYears(List<String> enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
	}
	public String getYesPerc() {
		return yesPerc;
	}
	public void setYesPerc(String yesPerc) {
		this.yesPerc = yesPerc;
	}
	public String getNoPerc() {
		return noPerc;
	}
	public void setNoPerc(String noPerc) {
		this.noPerc = noPerc;
	}
	public String getMayPerc() {
		return mayPerc;
	}
	public void setMayPerc(String mayPerc) {
		this.mayPerc = mayPerc;
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
	public Long getMayBecount() {
		return mayBecount;
	}
	public void setMayBecount(Long mayBecount) {
		this.mayBecount = mayBecount;
	}
	public Long getImagesCovered() {
		return imagesCovered;
	}
	public void setImagesCovered(Long imagesCovered) {
		this.imagesCovered = imagesCovered;
	}
	public Long getTotalImages() {
		return totalImages;
	}
	public void setTotalImages(Long totalImages) {
		this.totalImages = totalImages;
	}
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getPerc() {
		return perc;
	}

	public void setPerc(String perc) {
		this.perc = perc;
	}

	public IdAndNameVO(){}
	
	public Long getSessionNo() {
		return sessionNo;
	}

	public void setSessionNo(Long sessionNo) {
		this.sessionNo = sessionNo;
	}

	public IdAndNameVO(Long id,String name){
		this.id=id;
		this.name =name;
	}
	public List<IdAndNameVO> getDistList() {
		return distList;
	}
	public void setDistList(List<IdAndNameVO> distList) {
		this.distList = distList;
	}
	public List<IdAndNameVO> getIssueTypes() {
		return issueTypes;
	}
	public void setIssueTypes(List<IdAndNameVO> issueTypes) {
		this.issueTypes = issueTypes;
	}
	
	
	public String getImagePathStr() {
		return imagePathStr;
	}
	public void setImagePathStr(String imagePathStr) {
		this.imagePathStr = imagePathStr;
	}
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getAttenteeCount() {
		return attenteeCount;
	}
	public void setAttenteeCount(Long attenteeCount) {
		this.attenteeCount = attenteeCount;
	}
	public Long getInviteeAttendeeCnt() {
		return inviteeAttendeeCnt;
	}
	public void setInviteeAttendeeCnt(Long inviteeAttendeeCnt) {
		this.inviteeAttendeeCnt = inviteeAttendeeCnt;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getActualMobNumber() {
		return actualMobNumber;
	}
	public void setActualMobNumber(String actualMobNumber) {
		this.actualMobNumber = actualMobNumber;
	}
	public Long getApTotal() {
		return apTotal;
	}
	public void setApTotal(Long apTotal) {
		this.apTotal = apTotal;
	}
	public Long getTsTotal() {
		return tsTotal;
	}
	public void setTsTotal(Long tsTotal) {
		this.tsTotal = tsTotal;
	}
	public Long getApNow() {
		return apNow;
	}
	public void setApNow(Long apNow) {
		this.apNow = apNow;
	}
	public Long getTsNow() {
		return tsNow;
	}
	public void setTsNow(Long tsNow) {
		this.tsNow = tsNow;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Double getPer2016() {
		return per2016;
	}
	public void setPer2016(Double per2016) {
		this.per2016 = per2016;
	}
	public String getIsCsd() {
		return isCsd;
	}
	public void setIsCsd(String isCsd) {
		this.isCsd = isCsd;
	}
	public List<Long> getDistIdList() {
		return distIdList;
	}
	public void setDistIdList(List<Long> distIdList) {
		this.distIdList = distIdList;
	}
	public List<Long> getConstIdList() {
		return constIdList;
	}
	public void setConstIdList(List<Long> constIdList) {
		this.constIdList = constIdList;
	}
	public Long getTdpcadreId() {
		return tdpcadreId;
	}
	public void setTdpcadreId(Long tdpcadreId) {
		this.tdpcadreId = tdpcadreId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getCountOfActivityLocationInfo() {
		return countOfActivityLocationInfo;
	}

	public void setCountOfActivityLocationInfo(Long countOfActivityLocationInfo) {
		this.countOfActivityLocationInfo = countOfActivityLocationInfo;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getRemainingPerc() {
		return remainingPerc;
	}

	public void setRemainingPerc(String remainingPerc) {
		this.remainingPerc = remainingPerc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public List<IdAndNameVO> getNonInviteeAttendancList() {
		return nonInviteeAttendancList;
	}
	public void setNonInviteeAttendancList(List<IdAndNameVO> nonInviteeAttendancList) {
		this.nonInviteeAttendancList = nonInviteeAttendancList;
	}	
	
}
