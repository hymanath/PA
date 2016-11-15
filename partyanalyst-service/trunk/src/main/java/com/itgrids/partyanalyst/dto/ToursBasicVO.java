package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToursBasicVO {

	private Long id;
	private Long candDtlsId;
	private String name;
	private Long tdpCadreId;
	private Long activityMemberId;
	private String memberShipNo;
	private Long voaterId;
	private String mobileNo;
    private String type;
    private Long locationScopeId;
    private Long locationValue;
    private String image;
    private Long candidateCount = 0l;
    private Long selectedCandCount = 0l;
    private Long totalTour = 0l;
    private Long year = 0l;
    private String month ;
    private String comment ;
    private String filePath;
	private List<ToursBasicVO> subList = null;
	private List<ToursBasicVO> subList2 = null;
	
	private String designation;
	private Long designationId;
	private Long noOfLeaderCnt=0l;
	private Long submitedLeaderCnt=0l;
	private Long notSubmitedLeaserCnt=0l;
	private Long ownToursCnt;
	private Long inchargerToursCnt;
	private Long totalSubmittedToursCnt=0l;
	private Double averageTours=0.0d;
	private String locationScope;
	
	private Double submitedCandidateTourPer=0.0d;
	private Double notsubmitedCandidateTourPer=0.0d;
	private ToursBasicVO overAllDetailsVO;
	private List<Long> locationValueList = null;
	private Set<Long> locationSet = new HashSet<Long>(0);
	private List<String> remarkList = null;
	private List<String> filePathList = null;
	private Long noOfDistinctTours=0l;
	private String isTourSubmitted;
	public ToursBasicVO() {
		super();
	}
	public ToursBasicVO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getVoaterId() {
		return voaterId;
	}
	public void setVoaterId(Long voaterId) {
		this.voaterId = voaterId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCandidateCount() {
		return candidateCount;
	}
	public void setCandidateCount(Long candidateCount) {
		this.candidateCount = candidateCount;
	}
	public Long getSelectedCandCount() {
		return selectedCandCount;
	}
	public void setSelectedCandCount(Long selectedCandCount) {
		this.selectedCandCount = selectedCandCount;
	}
	public Long getTotalTour() {
		return totalTour;    
	}
	public void setTotalTour(Long totalTour) {
		this.totalTour = totalTour;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getNoOfLeaderCnt() {
		return noOfLeaderCnt;
	}
	public void setNoOfLeaderCnt(Long noOfLeaderCnt) {
		this.noOfLeaderCnt = noOfLeaderCnt;
	}
	public Long getSubmitedLeaderCnt() {
		return submitedLeaderCnt;
	}
	public void setSubmitedLeaderCnt(Long submitedLeaderCnt) {
		this.submitedLeaderCnt = submitedLeaderCnt;
	}
	public Long getNotSubmitedLeaserCnt() {
		return notSubmitedLeaserCnt;
	}
	public void setNotSubmitedLeaserCnt(Long notSubmitedLeaserCnt) {
		this.notSubmitedLeaserCnt = notSubmitedLeaserCnt;
	}
	public Long getOwnToursCnt() {
		return ownToursCnt;
	}
	public void setOwnToursCnt(Long ownToursCnt) {
		this.ownToursCnt = ownToursCnt;
	}
	public Long getInchargerToursCnt() {
		return inchargerToursCnt;
	}
	public void setInchargerToursCnt(Long inchargerToursCnt) {
		this.inchargerToursCnt = inchargerToursCnt;
	}
	public Long getTotalSubmittedToursCnt() {
		return totalSubmittedToursCnt;
	}
	public void setTotalSubmittedToursCnt(Long totalSubmittedToursCnt) {
		this.totalSubmittedToursCnt = totalSubmittedToursCnt;
	}
	public Double getAverageTours() {
		return averageTours;
	}
	public void setAverageTours(Double averageTours) {
		this.averageTours = averageTours;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	
	public Double getSubmitedCandidateTourPer() {
		return submitedCandidateTourPer;
	}
	public void setSubmitedCandidateTourPer(Double submitedCandidateTourPer) {
		this.submitedCandidateTourPer = submitedCandidateTourPer;
	}
	public Double getNotsubmitedCandidateTourPer() {
		return notsubmitedCandidateTourPer;
	}
	public void setNotsubmitedCandidateTourPer(Double notsubmitedCandidateTourPer) {
		this.notsubmitedCandidateTourPer = notsubmitedCandidateTourPer;
	}
	public ToursBasicVO getOverAllDetailsVO() {
		return overAllDetailsVO;
	}
	public void setOverAllDetailsVO(ToursBasicVO overAllDetailsVO) {
		this.overAllDetailsVO = overAllDetailsVO;
	}
	public List<Long> getLocationValueList() {
		if(locationValueList != null){
			return locationValueList;
		}else{
			locationValueList = new ArrayList<Long>();
			return locationValueList;    
		}
	}
	public List<ToursBasicVO> getSubList() {   
		if(subList != null){
			return subList;
		}else{
			subList = new ArrayList<ToursBasicVO>();
			return subList;   
		}
	}
	public List<ToursBasicVO> getSubList2() {   
		if(subList2 != null){
			return subList2;
		}else{
			subList2 = new ArrayList<ToursBasicVO>();
			return subList2;   
		}
	}
	public Set<Long> getLocationSet() {
		return locationSet;
	}
	public void setLocationSet(Set<Long> locationSet) {
		this.locationSet = locationSet;
	}
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	public List<String> getRemarkList() {
		if(remarkList != null){
			return remarkList;
		}else{
			remarkList = new ArrayList<String>();
			return remarkList;
		}
	}
	public List<String> getFilePathList() {
		if(filePathList != null){
			return filePathList;
		}else{
			filePathList = new ArrayList<String>();
			return filePathList;
		}
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getNoOfDistinctTours() {
		return noOfDistinctTours;
	}
	public void setNoOfDistinctTours(Long noOfDistinctTours) {
		this.noOfDistinctTours = noOfDistinctTours;
	}
	public Long getCandDtlsId() {
		return candDtlsId;
	}
	public void setCandDtlsId(Long candDtlsId) {
		this.candDtlsId = candDtlsId;
	}
	public String getIsTourSubmitted() {
		return isTourSubmitted;
	}
	public void setIsTourSubmitted(String isTourSubmitted) {
		this.isTourSubmitted = isTourSubmitted;
	}	
}
