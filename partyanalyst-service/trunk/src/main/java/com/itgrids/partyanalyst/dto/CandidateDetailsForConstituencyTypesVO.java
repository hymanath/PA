package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CandidateDetailsForConstituencyTypesVO {
	
	private CandidateInfoForConstituencyVO parliamentCandidateInfo;
	private List<CandidateInfoForConstituencyVO> assemblyCandidateInfo;
	private String ispartial;
	private List<CandidateDetailsForConstituencyTypesVO> subList;
	private List<CandidateInfoForConstituencyVO> list = new ArrayList<CandidateInfoForConstituencyVO>();
	private List<CandidateInfoForConstituencyVO> subList1; 
	private String migrateCandidate; 
	private Long condidateId;
	private String designation;
	private String totalDesignation = " ";
	private Long partyId;
	private String party;
	private String partyFlag;
	private Long designationId;
	private String candidateName;
	private Long cadreId;
	private Long constituencyId;
	private String constituencyName;
	private Long representativeLevelId;
	private String representativeLevel;
	private String constituencyType;
	private String cadreImage;
	private Long scopeId;
	private Long condidateCount=0l;
	private String others;
	private Long othersCount=0l;
	private List<CandidateDetailsForConstituencyTypesVO> subList2;
	public List<CandidateInfoForConstituencyVO> getList() {
		return list;
	}
	public void setList(List<CandidateInfoForConstituencyVO> list) {
		this.list = list;
	}
	public CandidateInfoForConstituencyVO getParliamentCandidateInfo() {
		return parliamentCandidateInfo;
	}
	public void setParliamentCandidateInfo(
			CandidateInfoForConstituencyVO parliamentCandidateInfo) {
		this.parliamentCandidateInfo = parliamentCandidateInfo;
	}
	public List<CandidateInfoForConstituencyVO> getAssemblyCandidateInfo() {
		return assemblyCandidateInfo;
	}
	public void setAssemblyCandidateInfo(
			List<CandidateInfoForConstituencyVO> assemblyCandidateInfo) {
		this.assemblyCandidateInfo = assemblyCandidateInfo;
	}
	public String getIspartial() {
		return ispartial;
	}
	public void setIspartial(String ispartial) {
		this.ispartial = ispartial;
	}

	public List<CandidateDetailsForConstituencyTypesVO> getSubList() {
		return subList;
	}

	public void setSubList(List<CandidateDetailsForConstituencyTypesVO> subList) {
		this.subList = subList;
	}
	public List<CandidateInfoForConstituencyVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<CandidateInfoForConstituencyVO> subList1) {
		this.subList1 = subList1;
	}
	public String getMigrateCandidate() {
		return migrateCandidate;
	}
	public void setMigrateCandidate(String migrateCandidate) {
		this.migrateCandidate = migrateCandidate;
	}
	public Long getCondidateId() {
		return condidateId;
	}
	public void setCondidateId(Long condidateId) {
		this.condidateId = condidateId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getTotalDesignation() {
		return totalDesignation;
	}
	public void setTotalDesignation(String totalDesignation) {
		this.totalDesignation = totalDesignation;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getRepresentativeLevelId() {
		return representativeLevelId;
	}
	public void setRepresentativeLevelId(Long representativeLevelId) {
		this.representativeLevelId = representativeLevelId;
	}
	public String getRepresentativeLevel() {
		return representativeLevel;
	}
	public void setRepresentativeLevel(String representativeLevel) {
		this.representativeLevel = representativeLevel;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public String getCadreImage() {
		return cadreImage;
	}
	public void setCadreImage(String cadreImage) {
		this.cadreImage = cadreImage;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getCondidateCount() {
		return condidateCount;
	}
	public void setCondidateCount(Long condidateCount) {
		this.condidateCount = condidateCount;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public Long getOthersCount() {
		return othersCount;
	}
	public void setOthersCount(Long othersCount) {
		this.othersCount = othersCount;
	}
	public List<CandidateDetailsForConstituencyTypesVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<CandidateDetailsForConstituencyTypesVO> subList2) {
		this.subList2 = subList2;
	}
	
	
}
