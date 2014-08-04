package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyReportVO {
	
	private Long userid;
	private String userName;
	private Long count = 0L;
	private List<SurveyReportVO> subList = new ArrayList<SurveyReportVO>();
	private String surveyDate;
	private String partNo;
	private Long boothId;
	private Long voterId;
	

	private SurveyReportVO dataCollector;
	private SurveyReportVO verifier;
	private SurveyReportVO thirdParty;
	private SurveyReportVO panchayatDetails;
	
	private String voterName;
	private String cadre;
	private String influencePeople;
   
	private String caste;
    private String localArea;
    private Long hamletId;
    private String hamletName;
    private String userType;
    private Long userTypeId;
    private Long casteId;
    private Long totalVoters;
    
    private boolean cadreMatched;
    private boolean influencePeopleMatched;
    private boolean localAreaMatched;
    private boolean hamletMatched;
    private boolean casteMatched;
    private Long surveyDetailsInfoId;
    private String voterIDCardNo;
    private String verified;
	private String surveyDate1;
	
	private Long matchedCount;
	private Long unmatchedCount;
	private String mobileNo;
	private Long id;
	private String name;
	
	
	private Long casteMatchedCount = 0l;
	private Long casteNotMatchedCount =0l;
	private Long mobileMatchedCount =0l;
	private Long mobileNotMatchedCount =0l;
	
	private Long boothCount = 0l;
	
	private Long hamletCount = 0l;
	private Long casteCount = 0l;
	private String mandalName;
	private String panchayatName;
	private String villageCovered;

	private Long mobileNoCount =0l;
	private List<GenericVO> genericVOList = new ArrayList<GenericVO>();
	private List<GenericVO> genericVOList1 = new ArrayList<GenericVO>();
	private String percent;
	
	private Long panchayatCount =0l;
	private Long panchayatNotCompleteCount = 0l;
	
	private List<Long> completeIds = new ArrayList<Long>();
	private List<Long> notCompleteIds = new ArrayList<Long>();
	
	private Long casteCollectedCount = 0L;
	private Long hamletCollectedCount = 0L;
	private Long mobileNumberCollectedCount = 0L;
	
	private String startTime;
	private String endTime;
	
	private Long totalCollectedCount;
	private String totalCollectedPercent;
	
	private Long processingCount = 0L;
	private Long startedCount;
	private Long completedCount = 0L;
	
	private Long wmCompletedCount = 0L;
	private Long dvProcessingCount = 0L;
	private Long dvCompletedCount = 0L;
	private Long notStartedCount = 0L;
	private Long actualProcessingCount = 0L;
	private Long activeUsersCount = 0L;
	private Long inActiveUsersCount = 0L;
	
	private String casteErrorPercent;
	
	private String dcCaste;
	private String wmCaste;
	private String status;
	private String MobileNumber; 
	
	private Long thirdPartyProcessing = 0L;
	private Long thirdPartyCompleted = 0L;
	private Long thirdpartyReady = 0L;
	private Long houseNoCount;
	
	public Long getThirdPartyProcessing() {
		return thirdPartyProcessing;
	}
	public void setThirdPartyProcessing(Long thirdPartyProcessing) {
		this.thirdPartyProcessing = thirdPartyProcessing;
	}
	public Long getThirdPartyCompleted() {
		return thirdPartyCompleted;
	}
	public void setThirdPartyCompleted(Long thirdPartyCompleted) {
		this.thirdPartyCompleted = thirdPartyCompleted;
	}
	public Long getThirdpartyReady() {
		return thirdpartyReady;
	}
	public void setThirdpartyReady(Long thirdpartyReady) {
		this.thirdpartyReady = thirdpartyReady;
	}
	
	
	public List<GenericVO> getGenericVOList1() {
		return genericVOList1;
	}
	public void setGenericVOList1(List<GenericVO> genericVOList1) {
		this.genericVOList1 = genericVOList1;
	}
	public Long getActiveUsersCount() {
		return activeUsersCount;
	}
	public void setActiveUsersCount(Long activeUsersCount) {
		this.activeUsersCount = activeUsersCount;
	}
	public Long getInActiveUsersCount() {
		return inActiveUsersCount;
	}
	public void setInActiveUsersCount(Long inActiveUsersCount) {
		this.inActiveUsersCount = inActiveUsersCount;
	}
	
	
	
	public String getCasteErrorPercent() {
		return casteErrorPercent;
	}
	public void setCasteErrorPercent(String casteErrorPercent) {
		this.casteErrorPercent = casteErrorPercent;
	}
	public Long getActualProcessingCount() {
		return actualProcessingCount;
	}
	public void setActualProcessingCount(Long actualProcessingCount) {
		this.actualProcessingCount = actualProcessingCount;
	}
	public Long getNotStartedCount() {
		return notStartedCount;
	}
	public void setNotStartedCount(Long notStartedCount) {
		this.notStartedCount = notStartedCount;
	}
	public Long getWmCompletedCount() {
		return wmCompletedCount;
	}
	public void setWmCompletedCount(Long wmCompletedCount) {
		this.wmCompletedCount = wmCompletedCount;
	}
	public Long getDvProcessingCount() {
		return dvProcessingCount;
	}
	public void setDvProcessingCount(Long dvProcessingCount) {
		this.dvProcessingCount = dvProcessingCount;
	}
	public Long getDvCompletedCount() {
		return dvCompletedCount;
	}
	public void setDvCompletedCount(Long dvCompletedCount) {
		this.dvCompletedCount = dvCompletedCount;
	}
	public Long getProcessingCount() {
		return processingCount;
	}
	public void setProcessingCount(Long processingCount) {
		this.processingCount = processingCount;
	}
	
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	private String leaderName;
	
	
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getTotalCollectedPercent() {
		return totalCollectedPercent;
	}
	public void setTotalCollectedPercent(String totalCollectedPercent) {
		this.totalCollectedPercent = totalCollectedPercent;
	}
	public Long getTotalCollectedCount() {
		return totalCollectedCount;
	}
	public void setTotalCollectedCount(Long totalCollectedCount) {
		this.totalCollectedCount = totalCollectedCount;
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
	public Long getMobileNumberCollectedCount() {
		return mobileNumberCollectedCount;
	}
	public void setMobileNumberCollectedCount(Long mobileNumberCollectedCount) {
		this.mobileNumberCollectedCount = mobileNumberCollectedCount;
	}
	public Long getCasteCollectedCount() {
		return casteCollectedCount;
	}
	public void setCasteCollectedCount(Long casteCollectedCount) {
		this.casteCollectedCount = casteCollectedCount;
	}
	public Long getHamletCollectedCount() {
		return hamletCollectedCount;
	}
	public void setHamletCollectedCount(Long hamletCollectedCount) {
		this.hamletCollectedCount = hamletCollectedCount;
	}
	public List<Long> getCompleteIds() {
		return completeIds;
	}
	public void setCompleteIds(List<Long> completeIds) {
		this.completeIds = completeIds;
	}
	public List<Long> getNotCompleteIds() {
		return notCompleteIds;
	}
	public void setNotCompleteIds(List<Long> notCompleteIds) {
		this.notCompleteIds = notCompleteIds;
	}
	public Long getPanchayatCount() {
		return panchayatCount;
	}
	public void setPanchayatCount(Long panchayatCount) {
		this.panchayatCount = panchayatCount;
	}
	public Long getPanchayatNotCompleteCount() {
		return panchayatNotCompleteCount;
	}
	public void setPanchayatNotCompleteCount(Long panchayatNotCompleteCount) {
		this.panchayatNotCompleteCount = panchayatNotCompleteCount;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	private Long total = 0L;
	private Long serailNo ;
	
	public Long getSerailNo() {
		return serailNo;
	}
	public void setSerailNo(Long serailNo) {
		this.serailNo = serailNo;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
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
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public String getVillageCovered() {
		return villageCovered;
	}
	public void setVillageCovered(String villageCovered) {
		this.villageCovered = villageCovered;
	}
	
	public Long getMobileNoCount() {
		return mobileNoCount;
	}
	public void setMobileNoCount(Long mobileNoCount) {
		this.mobileNoCount = mobileNoCount;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	
	public Long getBoothCount() {
		return boothCount;
	}
	public void setBoothCount(Long boothCount) {
		this.boothCount = boothCount;
	}
	public Long getHamletCount() {
		return hamletCount;
	}
	public void setHamletCount(Long hamletCount) {
		this.hamletCount = hamletCount;
	}
	public Long getCasteCount() {
		return casteCount;
	}
	public void setCasteCount(Long casteCount) {
		this.casteCount = casteCount;
	}
	public Long getCasteMatchedCount() {
		return casteMatchedCount;
	}
	public void setCasteMatchedCount(Long casteMatchedCount) {
		this.casteMatchedCount = casteMatchedCount;
	}
	public Long getCasteNotMatchedCount() {
		return casteNotMatchedCount;
	}
	public void setCasteNotMatchedCount(Long casteNotMatchedCount) {
		this.casteNotMatchedCount = casteNotMatchedCount;
	}
	public Long getMobileMatchedCount() {
		return mobileMatchedCount;
	}
	public void setMobileMatchedCount(Long mobileMatchedCount) {
		this.mobileMatchedCount = mobileMatchedCount;
	}
	
	
	public Long getMobileNotMatchedCount() {
		return mobileNotMatchedCount;
	}
	public void setMobileNotMatchedCount(Long mobileNotMatchedCount) {
		this.mobileNotMatchedCount = mobileNotMatchedCount;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getMatchedCount() {
		return matchedCount;
	}
	public void setMatchedCount(Long matchedCount) {
		this.matchedCount = matchedCount;
	}
	public Long getUnmatchedCount() {
		return unmatchedCount;
	}
	public void setUnmatchedCount(Long unmatchedCount) {
		this.unmatchedCount = unmatchedCount;
	}
	public String getSurveyDate1() {
		return surveyDate1;
	}
	public void setSurveyDate1(String surveyDate1) {
		this.surveyDate1 = surveyDate1;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}
	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}
	public Long getSurveyDetailsInfoId() {
		return surveyDetailsInfoId;
	}
	public void setSurveyDetailsInfoId(Long surveyDetailsInfoId) {
		this.surveyDetailsInfoId = surveyDetailsInfoId;
	}
	public boolean isCasteMatched() {
		return casteMatched;
	}
	public void setCasteMatched(boolean casteMatched) {
		this.casteMatched = casteMatched;
	}
	public boolean isCadreMatched() {
		return cadreMatched;
	}
	public void setCadreMatched(boolean cadreMatched) {
		this.cadreMatched = cadreMatched;
	}
	public boolean isInfluencePeopleMatched() {
		return influencePeopleMatched;
	}
	public void setInfluencePeopleMatched(boolean influencePeopleMatched) {
		this.influencePeopleMatched = influencePeopleMatched;
	}
	public boolean isLocalAreaMatched() {
		return localAreaMatched;
	}
	public void setLocalAreaMatched(boolean localAreaMatched) {
		this.localAreaMatched = localAreaMatched;
	}
	public boolean isHamletMatched() {
		return hamletMatched;
	}
	public void setHamletMatched(boolean hamletMatched) {
		this.hamletMatched = hamletMatched;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	
	
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getLocalArea() {
		return localArea;
	}
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public SurveyReportVO getDataCollector() {
		return dataCollector;
	}
	public void setDataCollector(SurveyReportVO dataCollector) {
		this.dataCollector = dataCollector;
	}
	public SurveyReportVO getVerifier() {
		return verifier;
	}
	public void setVerifier(SurveyReportVO verifier) {
		this.verifier = verifier;
	}
	public SurveyReportVO getThirdParty() {
		return thirdParty;
	}
	public void setThirdParty(SurveyReportVO thirdParty) {
		this.thirdParty = thirdParty;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	public List<SurveyReportVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SurveyReportVO> subList) {
		this.subList = subList;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	 public String getCadre() {
			return cadre;
	}
	public void setCadre(String cadre) {
		this.cadre = cadre;
	}
	public String getInfluencePeople() {
		return influencePeople;
	}
	public void setInfluencePeople(String influencePeople) {
		this.influencePeople = influencePeople;
	}
	

	public SurveyReportVO getPanchayatDetails() {
		return panchayatDetails;
	}
	public void setPanchayatDetails(SurveyReportVO panchayatDetails) {
		this.panchayatDetails = panchayatDetails;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getDcCaste() {
		return dcCaste;
	}
	public void setDcCaste(String dcCaste) {
		this.dcCaste = dcCaste;
	}
	public String getWmCaste() {
		return wmCaste;
	}
	public void setWmCaste(String wmCaste) {
		this.wmCaste = wmCaste;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public Long getHouseNoCount() {
		return houseNoCount;
	}
	public void setHouseNoCount(Long houseNoCount) {
		this.houseNoCount = houseNoCount;
	}
    
	
}
