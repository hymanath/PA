package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PollManagementVO implements Serializable{
    
	private Long id;
	private String name;
	
	private Long divisions;
	private Long booths;
	private Long capturedVoters;
	private Long totalVoters;
	private Long totalCadre;
	private Long capturedCadre;
	private Long boothsCount;
	private Long inclinedVoters=0l;
	private Long unDecidedVoters=0l;
	private Long otherPartyVoters=0l;
	private Long nonOptedVoters=0l;
	private String partNo;
	private Long boothId;
	private List<PollManagementVO> cadreBoothsList = new ArrayList<PollManagementVO>();
	private List<PollManagementVO> inclinedBoothsList = new ArrayList<PollManagementVO>();
	private List<PollManagementVO> unDecidedBoothsList = new ArrayList<PollManagementVO>();
	private List<PollManagementVO> oherPartyBoothsList = new ArrayList<PollManagementVO>();
	private List<PollManagementVO> notCapturedBoothsList = new ArrayList<PollManagementVO>();
	private String voterCardNo;
	private String mobileNo;
	private String smsStatus;
	private String calledStatus;
	
	private Long polledVotes =0l;
	private Long yetToPollVotes=0l;
	private List<PollManagementSummaryVO> pollManagementSummaryVOList = new ArrayList<PollManagementSummaryVO>();
	

	public List<PollManagementSummaryVO> getPollManagementSummaryVOList() {
		return pollManagementSummaryVOList;
	}
	public void setPollManagementSummaryVOList(
			List<PollManagementSummaryVO> pollManagementSummaryVOList) {
		this.pollManagementSummaryVOList = pollManagementSummaryVOList;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public String getCalledStatus() {
		return calledStatus;
	}
	public void setCalledStatus(String calledStatus) {
		this.calledStatus = calledStatus;
	}
	
	public Long getPolledVotes() {
		return polledVotes;
	}
	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}
	public Long getYetToPollVotes() {
		return yetToPollVotes;
	}
	public void setYetToPollVotes(Long yetToPollVotes) {
		this.yetToPollVotes = yetToPollVotes;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getCapturedVoters() {
		return capturedVoters;
	}
	public void setCapturedVoters(Long capturedVoters) {
		this.capturedVoters = capturedVoters;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	public Long getCapturedCadre() {
		return capturedCadre;
	}
	public void setCapturedCadre(Long capturedCadre) {
		this.capturedCadre = capturedCadre;
	}
	public Long getBoothsCount() {
		return boothsCount;
	}
	public void setBoothsCount(Long boothsCount) {
		this.boothsCount = boothsCount;
	}
	public Long getInclinedVoters() {
		return inclinedVoters;
	}
	public void setInclinedVoters(Long inclinedVoters) {
		this.inclinedVoters = inclinedVoters;
	}
	public Long getUnDecidedVoters() {
		return unDecidedVoters;
	}
	public void setUnDecidedVoters(Long unDecidedVoters) {
		this.unDecidedVoters = unDecidedVoters;
	}
	public Long getOtherPartyVoters() {
		return otherPartyVoters;
	}
	public void setOtherPartyVoters(Long otherPartyVoters) {
		this.otherPartyVoters = otherPartyVoters;
	}
	public Long getNonOptedVoters() {
		return nonOptedVoters;
	}
	public void setNonOptedVoters(Long nonOptedVoters) {
		this.nonOptedVoters = nonOptedVoters;
	}
	public Long getDivisions() {
		return divisions;
	}
	public void setDivisions(Long divisions) {
		this.divisions = divisions;
	}
	public Long getBooths() {
		return booths;
	}
	public void setBooths(Long booths) {
		this.booths = booths;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public List<PollManagementVO> getCadreBoothsList() {
		return cadreBoothsList;
	}
	public void setCadreBoothsList(List<PollManagementVO> cadreBoothsList) {
		this.cadreBoothsList = cadreBoothsList;
	}
	public List<PollManagementVO> getInclinedBoothsList() {
		return inclinedBoothsList;
	}
	public void setInclinedBoothsList(List<PollManagementVO> inclinedBoothsList) {
		this.inclinedBoothsList = inclinedBoothsList;
	}
	public List<PollManagementVO> getUnDecidedBoothsList() {
		return unDecidedBoothsList;
	}
	public void setUnDecidedBoothsList(List<PollManagementVO> unDecidedBoothsList) {
		this.unDecidedBoothsList = unDecidedBoothsList;
	}
	public List<PollManagementVO> getOherPartyBoothsList() {
		return oherPartyBoothsList;
	}
	public void setOherPartyBoothsList(List<PollManagementVO> oherPartyBoothsList) {
		this.oherPartyBoothsList = oherPartyBoothsList;
	}
	public List<PollManagementVO> getNotCapturedBoothsList() {
		return notCapturedBoothsList;
	}
	public void setNotCapturedBoothsList(
			List<PollManagementVO> notCapturedBoothsList) {
		this.notCapturedBoothsList = notCapturedBoothsList;
	}
	
	
	
}
