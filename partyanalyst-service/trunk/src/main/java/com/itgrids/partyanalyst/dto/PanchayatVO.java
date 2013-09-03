package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.model.AddressType;

public class PanchayatVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
		
}
