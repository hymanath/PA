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
	
	private String voterName;
	private String cadre;
	private String influencePeople;
   
	private String caste;
    private String localArea;
    private Long hamletId;
    private String hamletName;
    private String userType;
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

}
