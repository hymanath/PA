package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.excel.booth.VoterVO;

public class ConstituencyManagementVO {

	private ProblemManagementVO problemManagementVO;
	private List<VoterVO> voterDetails;
	private VoterCastInfoVO VoterCastInfodetails;
	private List<VoterHouseInfoVO> votersByHouseNos;
	private List<LocalLeadersVO> localLeaders;
	private List<PoliticalChangesVO> politicalChanges;
	private List<HamletProblemVO> hamletProblems;
	private TotalMPTCMandalLeaderVO totalMPTCMandalLeaderVO;
	private List<VoterCastInfoVO> castVosList;
	private List<SelectOptionVO> castPercent;
	private List<Long> votersCount;
	private List<Long> agewisevotersCount;
	private List<VoterVO> mandalAgewiseVotersList ;
	private List<VoterVO> boothVotersList;
	private List<VotersDetailsVO> mandalAgeAndGenderwiseVotersList;
	private List<VotersDetailsVO> votersDetailsVO;
	private List<VotersDetailsVO> mandalsVotersDetails;
	private List<VotersDetailsVO> panchayatVotersDetails;
	private List<VotersDetailsVO> boothVotersDetails;
	private ConstituencyManagementVO constituencyManagementVO;
	private List<String> locations;
	private List<String> castes;
	private List<CastLocationVO> locWiseCastePrcts;
	
			
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getCastes() {
		return castes;
	}

	public void setCastes(List<String> castes) {
		this.castes = castes;
	}

	public List<CastLocationVO> getLocWiseCastePrcts() {
		return locWiseCastePrcts;
	}

	public void setLocWiseCastePrcts(List<CastLocationVO> locWiseCastePrcts) {
		this.locWiseCastePrcts = locWiseCastePrcts;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public List<SelectOptionVO> getCastPercent() {
		return castPercent;
	}

	public void setCastPercent(List<SelectOptionVO> castPercent) {
		this.castPercent = castPercent;
	}
	
	public List<VotersDetailsVO> getVotersDetailsVO() {
		return votersDetailsVO;
	}

	public void setVotersDetailsVO(List<VotersDetailsVO> votersDetailsVO) {
		this.votersDetailsVO = votersDetailsVO;
	}

	public List<VotersDetailsVO> getMandalAgeAndGenderwiseVotersList() {
		return mandalAgeAndGenderwiseVotersList;
	}

	public void setMandalAgeAndGenderwiseVotersList(
			List<VotersDetailsVO> mandalAgeAndGenderwiseVotersList) {
		this.mandalAgeAndGenderwiseVotersList = mandalAgeAndGenderwiseVotersList;
	}

	public List<VoterVO> getMandalAgewiseVotersList() {
		return mandalAgewiseVotersList;
	}

	public void setMandalAgewiseVotersList(List<VoterVO> mandalAgewiseVotersList) {
		this.mandalAgewiseVotersList = mandalAgewiseVotersList;
	}

	public List<VoterVO> getBoothVotersList() {
		return boothVotersList;
	}

	public void setBoothVotersList(List<VoterVO> boothVotersList) {
		this.boothVotersList = boothVotersList;
	}

	public List<Long> getAgewisevotersCount() {
		return agewisevotersCount;
	}

	public void setAgewisevotersCount(List<Long> agewisevotersCount) {
		this.agewisevotersCount = agewisevotersCount;
	}

	public List<Long> getVotersCount() {
		return votersCount;
	}

	public void setVotersCount(List<Long> votersCount) {
		this.votersCount = votersCount;
	}

	private Long voterDetailsCount;

	public Long getVoterDetailsCount() {
		return voterDetailsCount;
	}

	public void setVoterDetailsCount(Long voterDetailsCount) {
		this.voterDetailsCount = voterDetailsCount;
	}

	public ProblemManagementVO getProblemManagementVO() {
		return problemManagementVO;
	}

	public void setProblemManagementVO(ProblemManagementVO problemManagementVO) {
		this.problemManagementVO = problemManagementVO;
	}
	
	public List<VoterVO> getVoterDetails() {
		return voterDetails;
	}

	public void setVoterDetails(List<VoterVO> voterDetails) {
		this.voterDetails = voterDetails;
	}

	public VoterCastInfoVO getVoterCastInfodetails() {
		return VoterCastInfodetails;
	}
	
	public void setVoterCastInfodetails(VoterCastInfoVO voterCastInfodetails) {
		VoterCastInfodetails = voterCastInfodetails;
	}

	public List<VoterHouseInfoVO> getVotersByHouseNos() {
		return votersByHouseNos;
	}

	public void setVotersByHouseNos(List<VoterHouseInfoVO> votersByHouseNos) {
		this.votersByHouseNos = votersByHouseNos;
	}

	public List<LocalLeadersVO> getLocalLeaders() {
		return localLeaders;
	}

	public void setLocalLeaders(List<LocalLeadersVO> localLeaders) {
		this.localLeaders = localLeaders;
	}

	public List<PoliticalChangesVO> getPoliticalChanges() {
		return politicalChanges;
	}

	public void setPoliticalChanges(List<PoliticalChangesVO> politicalChanges) {
		this.politicalChanges = politicalChanges;
	}

	public List<HamletProblemVO> getHamletProblems() {
		return hamletProblems;
	}

	public void setHamletProblems(List<HamletProblemVO> hamletProblems) {
		this.hamletProblems = hamletProblems;
	}

	public TotalMPTCMandalLeaderVO getTotalMPTCMandalLeaderVO() {
		return totalMPTCMandalLeaderVO;
	}

	public void setTotalMPTCMandalLeaderVO(
			TotalMPTCMandalLeaderVO totalMPTCMandalLeaderVO) {
		this.totalMPTCMandalLeaderVO = totalMPTCMandalLeaderVO;
	}
	
	public List<VoterCastInfoVO> getCastVosList() {
		return castVosList;
	}

	public void setCastVosList(List<VoterCastInfoVO> castVosList) {
		this.castVosList = castVosList;
	}

	public List<VotersDetailsVO> getMandalsVotersDetails() {
		return mandalsVotersDetails;
	}

	public void setMandalsVotersDetails(List<VotersDetailsVO> mandalsVotersDetails) {
		this.mandalsVotersDetails = mandalsVotersDetails;
	}

	public List<VotersDetailsVO> getPanchayatVotersDetails() {
		return panchayatVotersDetails;
	}

	public void setPanchayatVotersDetails(
			List<VotersDetailsVO> panchayatVotersDetails) {
		this.panchayatVotersDetails = panchayatVotersDetails;
	}

	public List<VotersDetailsVO> getBoothVotersDetails() {
		return boothVotersDetails;
	}

	public void setBoothVotersDetails(List<VotersDetailsVO> boothVotersDetails) {
		this.boothVotersDetails = boothVotersDetails;
	}

}
