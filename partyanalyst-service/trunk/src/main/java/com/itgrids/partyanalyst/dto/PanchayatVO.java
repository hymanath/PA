package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PanchayatVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1934532470630303835L;
	/**
	 * 
	 */
	
	private Long panchayatId;
	private String panchayatName;
	private int totalVoters;
	private int maleVoters;
	private int femaleVoters;
	private String ageRange;
	private List<PanchayatVO> panchayatList,boothsList;
	private String percentage;
	private int totalPanchayatVoters;
	private List<CastVO> topCastes;
	private List<String> muncipalities;
	private Map<String,List<PanchayatVO>> municipalitesBoothsMap;
	private List<CastVO> selectedCastes;
	private List<CastVO> allSelectedCastes;
	private Long casteStateId;
	private String casteName;
	private String gender;
	private String muncipalityName;
	private int othrExpctdVotes;
	private String othrExpctdPrcntg;
	private int otherVotes;
	private String areaType;
	private List<BasicVO> muncipalityCasteList;
	private Long muncipaltotalVoters;
	private Long totalTargetCount;
	private Double targetPerc;
	private Double voterPoints;
	//private Map<Long,PanchayatVO> casteMap;
	private Map<Long,Long> casteMap;
	private Double partyPerc;
	private Long count;
	private Double differencePerc;
	private Double opportunity;
	
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
	public int getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}
	public int getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(int maleVoters) {
		this.maleVoters = maleVoters;
	}
	public int getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(int femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public List<PanchayatVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<PanchayatVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public int getTotalPanchayatVoters() {
		return totalPanchayatVoters;
	}
	public void setTotalPanchayatVoters(int totalPanchayatVoters) {
		this.totalPanchayatVoters = totalPanchayatVoters;
	}
	public List<CastVO> getTopCastes() {
		return topCastes;
	}
	public void setTopCastes(List<CastVO> topCastes) {
		this.topCastes = topCastes;
	}
	public List<PanchayatVO> getBoothsList() {
		return boothsList;
	}
	public void setBoothsList(List<PanchayatVO> boothsList) {
		this.boothsList = boothsList;
	}
	public List<String> getMuncipalities() {
		return muncipalities;
	}
	public void setMuncipalities(List<String> muncipalities) {
		this.muncipalities = muncipalities;
	}
	public Map<String, List<PanchayatVO>> getMunicipalitesBoothsMap() {
		return municipalitesBoothsMap;
	}
	public void setMunicipalitesBoothsMap(
			Map<String, List<PanchayatVO>> municipalitesBoothsMap) {
		this.municipalitesBoothsMap = municipalitesBoothsMap;
	}
	public List<CastVO> getSelectedCastes() {
		return selectedCastes;
	}
	public void setSelectedCastes(List<CastVO> selectedCastes) {
		this.selectedCastes = selectedCastes;
	}
	public List<CastVO> getAllSelectedCastes() {
		return allSelectedCastes;
	}
	public void setAllSelectedCastes(List<CastVO> allSelectedCastes) {
		this.allSelectedCastes = allSelectedCastes;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	public int getOthrExpctdVotes() {
		return othrExpctdVotes;
	}
	public void setOthrExpctdVotes(int othrExpctdVotes) {
		this.othrExpctdVotes = othrExpctdVotes;
	}
	public String getOthrExpctdPrcntg() {
		return othrExpctdPrcntg;
	}
	public void setOthrExpctdPrcntg(String othrExpctdPrcntg) {
		this.othrExpctdPrcntg = othrExpctdPrcntg;
	}
	public int getOtherVotes() {
		return otherVotes;
	}
	public void setOtherVotes(int otherVotes) {
		this.otherVotes = otherVotes;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public List<BasicVO> getMuncipalityCasteList() {
		return muncipalityCasteList;
	}
	public void setMuncipalityCasteList(List<BasicVO> muncipalityCasteList) {
		this.muncipalityCasteList = muncipalityCasteList;
	}
	public Long getMuncipaltotalVoters() {
		return muncipaltotalVoters;
	}
	public void setMuncipaltotalVoters(Long muncipaltotalVoters) {
		this.muncipaltotalVoters = muncipaltotalVoters;
	}
	public Long getTotalTargetCount() {
		return totalTargetCount;
	}
	public void setTotalTargetCount(Long totalTargetCount) {
		this.totalTargetCount = totalTargetCount;
	}
	public Double getTargetPerc() {
		return targetPerc;
	}
	public void setTargetPerc(Double targetPerc) {
		this.targetPerc = targetPerc;
	}
	public Double getVoterPoints() {
		return voterPoints;
	}
	public void setVoterPoints(Double voterPoints) {
		this.voterPoints = voterPoints;
	}
	/*public Map<Long, PanchayatVO> getCasteMap() {
		return casteMap;
	}
	public void setCasteMap(Map<Long, PanchayatVO> casteMap) {
		this.casteMap = casteMap;
	}*/
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getPartyPerc() {
		return partyPerc;
	}
	public void setPartyPerc(Double partyPerc) {
		this.partyPerc = partyPerc;
	}
	public Double getDifferencePerc() {
		return differencePerc;
	}
	public void setDifferencePerc(Double differencePerc) {
		this.differencePerc = differencePerc;
	}
	public Map<Long, Long> getCasteMap() {
		return casteMap;
	}
	public void setCasteMap(Map<Long, Long> casteMap) {
		this.casteMap = casteMap;
	}
	public Double getOpportunity() {
		return opportunity;
	}
	public void setOpportunity(Double opportunity) {
		this.opportunity = opportunity;
	}
	
		
}
