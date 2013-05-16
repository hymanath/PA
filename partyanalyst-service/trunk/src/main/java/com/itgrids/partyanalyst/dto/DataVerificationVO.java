package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DataVerificationVO implements Serializable{

	private static final long serialVersionUID = 9030114037442163796L;
	
	private List<Long> boothIdsList;
	private List<Long> mandalIdsList;
	private List<Long> panchayatIdsList;
	private List<Long> wardIdsList;
	private List<Long> muncipalityIdsList;
	private List<Long> constituencyIdsList;
	
	private String areaType;
	private Long constituencyId;
	private Long publicationId;
	private Long totalmandals = 0L;
	private Long totalPanchayats = 0L;
	private Long totalBooths = 0L;
	private Long totalMuncipalities = 0L;
	private Long totalWards = 0L;
	private Long totalConstituencies = 0L;
	
	private DataVerificationInfoVO voterInfo = new DataVerificationInfoVO();
	private DataVerificationInfoVO familyInfo = new DataVerificationInfoVO();
	private DataVerificationInfoVO ageInfo = new DataVerificationInfoVO();
	private DataVerificationInfoVO modificationInfo = new DataVerificationInfoVO();
	
	public DataVerificationInfoVO getVoterInfo() {
		return voterInfo;
	}
	public void setVoterInfo(DataVerificationInfoVO voterInfo) {
		this.voterInfo = voterInfo;
	}
	public DataVerificationInfoVO getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(DataVerificationInfoVO familyInfo) {
		this.familyInfo = familyInfo;
	}
	public DataVerificationInfoVO getAgeInfo() {
		return ageInfo;
	}
	public void setAgeInfo(DataVerificationInfoVO ageInfo) {
		this.ageInfo = ageInfo;
	}
	public List<Long> getBoothIdsList() {
		return boothIdsList;
	}
	public void setBoothIdsList(List<Long> boothIdsList) {
		this.boothIdsList = boothIdsList;
	}
	public List<Long> getMandalIdsList() {
		return mandalIdsList;
	}
	public void setMandalIdsList(List<Long> mandalIdsList) {
		this.mandalIdsList = mandalIdsList;
	}
	public List<Long> getPanchayatIdsList() {
		return panchayatIdsList;
	}
	public void setPanchayatIdsList(List<Long> panchayatIdsList) {
		this.panchayatIdsList = panchayatIdsList;
	}
	public List<Long> getWardIdsList() {
		return wardIdsList;
	}
	public void setWardIdsList(List<Long> wardIdsList) {
		this.wardIdsList = wardIdsList;
	}
	public List<Long> getMuncipalityIdsList() {
		return muncipalityIdsList;
	}
	public void setMuncipalityIdsList(List<Long> muncipalityIdsList) {
		this.muncipalityIdsList = muncipalityIdsList;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}
	public Long getTotalmandals() {
		return totalmandals;
	}
	public void setTotalmandals(Long totalmandals) {
		this.totalmandals = totalmandals;
	}
	public Long getTotalPanchayats() {
		return totalPanchayats;
	}
	public void setTotalPanchayats(Long totalPanchayats) {
		this.totalPanchayats = totalPanchayats;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Long getTotalMuncipalities() {
		return totalMuncipalities;
	}
	public void setTotalMuncipalities(Long totalMuncipalities) {
		this.totalMuncipalities = totalMuncipalities;
	}
	public Long getTotalWards() {
		return totalWards;
	}
	public void setTotalWards(Long totalWards) {
		this.totalWards = totalWards;
	}
	public DataVerificationInfoVO getModificationInfo() {
		return modificationInfo;
	}
	public void setModificationInfo(DataVerificationInfoVO modificationInfo) {
		this.modificationInfo = modificationInfo;
	}
	public List<Long> getConstituencyIdsList() {
		return constituencyIdsList;
	}
	public void setConstituencyIdsList(List<Long> constituencyIdsList) {
		this.constituencyIdsList = constituencyIdsList;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	
	

}
