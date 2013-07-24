package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyHierarchyInfoVO implements Serializable{

	private Long constituencyId;
	private Long userId;
	private Long publicationDateId;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long totalMandals = 0L;
	private Long totalPanchayats = 0L;
	private Long totalHamlets = 0L;
	private Long totalBooths = 0L;
	private Long totalWards = 0L;
	private Long totalMuncipalities = 0L;
	private String constituencyType;
	
	private List<Long> boothIdsList;
	private List<Long> mandalIdsList;
	private List<Long> panchayatIdsList;
	private List<Long> wardIdsList;
	private List<Long> muncipalityIdsList;
	private List<Long> constituencyIdsList;
	private List<Long> hamletIdsList;
	private String type;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public Long getReportLevelId() {
		return reportLevelId;
	}
	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	public Long getTotalMandals() {
		return totalMandals;
	}
	public void setTotalMandals(Long totalMandals) {
		this.totalMandals = totalMandals;
	}
	public Long getTotalPanchayats() {
		return totalPanchayats;
	}
	public void setTotalPanchayats(Long totalPanchayats) {
		this.totalPanchayats = totalPanchayats;
	}
	public Long getTotalHamlets() {
		return totalHamlets;
	}
	public void setTotalHamlets(Long totalHamlets) {
		this.totalHamlets = totalHamlets;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public Long getTotalWards() {
		return totalWards;
	}
	public void setTotalWards(Long totalWards) {
		this.totalWards = totalWards;
	}
	public Long getTotalMuncipalities() {
		return totalMuncipalities;
	}
	public void setTotalMuncipalities(Long totalMuncipalities) {
		this.totalMuncipalities = totalMuncipalities;
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
	public List<Long> getConstituencyIdsList() {
		return constituencyIdsList;
	}
	public void setConstituencyIdsList(List<Long> constituencyIdsList) {
		this.constituencyIdsList = constituencyIdsList;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public List<Long> getHamletIdsList() {
		return hamletIdsList;
	}
	public void setHamletIdsList(List<Long> hamletIdsList) {
		this.hamletIdsList = hamletIdsList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
