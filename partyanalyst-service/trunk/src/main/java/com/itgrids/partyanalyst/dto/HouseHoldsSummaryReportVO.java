package com.itgrids.partyanalyst.dto;

import java.util.List;


public class HouseHoldsSummaryReportVO implements java.io.Serializable{
	
	private Long constituencyId;
	private String constituency;
	private Long constiVotersCount;
	private Long constiNonVotersCount;
	private Long constiHouseHoldsCount;
	private Long constiLeadersCount;
	
	private Long panchayatId;
	private String panchayatName;
	private Long votersCount;
	private Long nonVotersCount;
	private Long leadersCount;
	private Long houseHoldsCount;
	
	private List<HouseHoldsSummaryReportVO> panchayatList;
	
	private List<HouseHoldsSummaryReportVO> panchayatNamesList;
	
	private String voterName;
	private String voterCardNo;
	private String houseNo;
	private String mobileNo;
	private Long voterId;
	
	private String task;
	
	private List<HouseHoldsSummaryReportVO> leadersOfPnchyt;
	private List<HouseHoldsSummaryReportVO> familyHeads;
	private List<HouseHoldsSummaryReportVO> familyHeadsUnderBook;
	private List<HouseHoldsSummaryReportVO> familyHeadsUnderOption;
	
	private Long leaderId;
	private Long houseHoldId;
	
	private String leaderName;
	
	private int activeLeadersCount;
	private Long bookId;
	
	private Long optionId;
	private String option;
	
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public List<HouseHoldsSummaryReportVO> getFamilyHeadsUnderOption() {
		return familyHeadsUnderOption;
	}
	public void setFamilyHeadsUnderOption(
			List<HouseHoldsSummaryReportVO> familyHeadsUnderOption) {
		this.familyHeadsUnderOption = familyHeadsUnderOption;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public List<HouseHoldsSummaryReportVO> getFamilyHeadsUnderBook() {
		return familyHeadsUnderBook;
	}
	public void setFamilyHeadsUnderBook(
			List<HouseHoldsSummaryReportVO> familyHeadsUnderBook) {
		this.familyHeadsUnderBook = familyHeadsUnderBook;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public int getActiveLeadersCount() {
		return activeLeadersCount;
	}
	public void setActiveLeadersCount(int activeLeadersCount) {
		this.activeLeadersCount = activeLeadersCount;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public Long getHouseHoldId() {
		return houseHoldId;
	}
	public void setHouseHoldId(Long houseHoldId) {
		this.houseHoldId = houseHoldId;
	}
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public Long getConstiVotersCount() {
		return constiVotersCount;
	}
	public void setConstiVotersCount(Long constiVotersCount) {
		this.constiVotersCount = constiVotersCount;
	}
	public Long getConstiNonVotersCount() {
		return constiNonVotersCount;
	}
	public void setConstiNonVotersCount(Long constiNonVotersCount) {
		this.constiNonVotersCount = constiNonVotersCount;
	}
	public Long getConstiHouseHoldsCount() {
		return constiHouseHoldsCount;
	}
	public void setConstiHouseHoldsCount(Long constiHouseHoldsCount) {
		this.constiHouseHoldsCount = constiHouseHoldsCount;
	}
	public Long getConstiLeadersCount() {
		return constiLeadersCount;
	}
	public void setConstiLeadersCount(Long constiLeadersCount) {
		this.constiLeadersCount = constiLeadersCount;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getVotersCount() {
		return votersCount;
	}
	public void setVotersCount(Long votersCount) {
		this.votersCount = votersCount;
	}
	public Long getNonVotersCount() {
		return nonVotersCount;
	}
	public void setNonVotersCount(Long nonVotersCount) {
		this.nonVotersCount = nonVotersCount;
	}
	public Long getLeadersCount() {
		return leadersCount;
	}
	public void setLeadersCount(Long leadersCount) {
		this.leadersCount = leadersCount;
	}
	public Long getHouseHoldsCount() {
		return houseHoldsCount;
	}
	public void setHouseHoldsCount(Long houseHoldsCount) {
		this.houseHoldsCount = houseHoldsCount;
	}
	public List<HouseHoldsSummaryReportVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<HouseHoldsSummaryReportVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public List<HouseHoldsSummaryReportVO> getPanchayatNamesList() {
		return panchayatNamesList;
	}
	public void setPanchayatNamesList(
			List<HouseHoldsSummaryReportVO> panchayatNamesList) {
		this.panchayatNamesList = panchayatNamesList;
	}
	public List<HouseHoldsSummaryReportVO> getLeadersOfPnchyt() {
		return leadersOfPnchyt;
	}
	public void setLeadersOfPnchyt(List<HouseHoldsSummaryReportVO> leadersOfPnchyt) {
		this.leadersOfPnchyt = leadersOfPnchyt;
	}
	public List<HouseHoldsSummaryReportVO> getFamilyHeads() {
		return familyHeads;
	}
	public void setFamilyHeads(List<HouseHoldsSummaryReportVO> familyHeads) {
		this.familyHeads = familyHeads;
	}
	
	 
	
	
	
}
