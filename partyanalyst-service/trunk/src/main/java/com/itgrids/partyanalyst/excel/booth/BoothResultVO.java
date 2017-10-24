package com.itgrids.partyanalyst.excel.booth;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;

public class BoothResultVO extends ResultStatus{
	
	private Long boothId;
	private String partNo;
	private String location;
	private String villagesCovered;
	private int votesEarned;
	private int totalVoters;
	private String percentage;
	private String mandal;
	private Boolean showLink;
	private Long oppPartyId;
	private String oppParty;
	private int oppPartyVotesEarned;
	private String oppPartyPercentage;
	private String pollingPercentage;
	private List<BoothResultVO> BoothResultVOList = new ArrayList<BoothResultVO>();
	private List<BoothResultVO> BoothResultVOList1 = new ArrayList<BoothResultVO>();
	private String wonParty;
	private int totalBoothVoters;
	private int parlaimentCount=0;
	private String parlaimentPerc;
	public BoothResultVO(){
		
	}
	
	public BoothResultVO(String partNo, String location,
			String villagesCovered, int votesEarned, int totalVoters,
			String percentage,String mandal, Boolean showLink,String pollingPercentage) {
		this.partNo = partNo;
		this.location = location;
		this.villagesCovered = villagesCovered;
		this.votesEarned = votesEarned;
		this.totalVoters = totalVoters;
		this.percentage = percentage;
		this.mandal = mandal;
		this.showLink = showLink;
		this.pollingPercentage = pollingPercentage;
	}
	
	
	public List<BoothResultVO> getBoothResultVOList1() {
		return BoothResultVOList1;
	}

	public void setBoothResultVOList1(List<BoothResultVO> boothResultVOList1) {
		BoothResultVOList1 = boothResultVOList1;
	}

	public String getWonParty() {
		return wonParty;
	}

	public void setWonParty(String wonParty) {
		this.wonParty = wonParty;
	}

	public List<BoothResultVO> getBoothResultVOList() {
		return BoothResultVOList;
	}

	public void setBoothResultVOList(List<BoothResultVO> boothResultVOList) {
		BoothResultVOList = boothResultVOList;
	}

	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public int getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(int votesEarned) {
		this.votesEarned = votesEarned;
	}
	public int getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public Long getOppPartyId() {
		return oppPartyId;
	}

	public void setOppPartyId(Long oppPartyId) {
		this.oppPartyId = oppPartyId;
	}

	public String getOppParty() {
		return oppParty;
	}

	public void setOppParty(String oppParty) {
		this.oppParty = oppParty;
	}

	public int getOppPartyVotesEarned() {
		return oppPartyVotesEarned;
	}

	public void setOppPartyVotesEarned(int oppPartyVotesEarned) {
		this.oppPartyVotesEarned = oppPartyVotesEarned;
	}

	public String getOppPartyPercentage() {
		return oppPartyPercentage;
	}

	public void setOppPartyPercentage(String oppPartyPercentage) {
		this.oppPartyPercentage = oppPartyPercentage;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public Boolean getShowLink() {
		return showLink;
	}

	public void setShowLink(Boolean showLink) {
		this.showLink = showLink;
	}

	public String getPollingPercentage() {
		return pollingPercentage;
	}

	public void setPollingPercentage(String pollingPercentage) {
		this.pollingPercentage = pollingPercentage;
	}

	public int getTotalBoothVoters() {
		return totalBoothVoters;
	}

	public void setTotalBoothVoters(int totalBoothVoters) {
		this.totalBoothVoters = totalBoothVoters;
	}

	public int getParlaimentCount() {
		return parlaimentCount;
	}

	public void setParlaimentCount(int parlaimentCount) {
		this.parlaimentCount = parlaimentCount;
	}

	public String getParlaimentPerc() {
		return parlaimentPerc;
	}

	public void setParlaimentPerc(String parlaimentPerc) {
		this.parlaimentPerc = parlaimentPerc;
	}

}
