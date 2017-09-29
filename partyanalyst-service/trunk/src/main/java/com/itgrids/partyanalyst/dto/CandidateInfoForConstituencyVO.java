package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CandidateInfoForConstituencyVO {
	
	private Long constituencyId;
	private String constituencyName;
	private Long candidateId;
	private String candidateName;
	private Long partyId;
	private String party;
	private String partyFlag;
	private String constituencyType;
	private String deformDate;	
	private String latestElecYear;
	private String gender;
	private String education;
	private BigDecimal assets;
	private BigDecimal liabilities;
	private Long parliamentId;
	private String parliamnerName;
	private List<CandidateInfoForConstituencyVO> list = new ArrayList<CandidateInfoForConstituencyVO>();
	private Long tdpCadreId;
	private String migrateCandidate;
	private String candDesignation;
	private Long designationId;
		
	private CandidateElectionVO candidateElectionVO;
	private List<CandidateDetailsForConstituencyTypesVO> subList;
	
	
	public List<CandidateInfoForConstituencyVO> getList() {
		return list;
	}
	public void setList(List<CandidateInfoForConstituencyVO> list) {
		this.list = list;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamnerName() {
		return parliamnerName;
	}
	public void setParliamnerName(String parliamnerName) {
		this.parliamnerName = parliamnerName;
	}
	public String getLatestElecYear() {
		return latestElecYear;
	}
	public void setLatestElecYear(String latestElecYear) {
		this.latestElecYear = latestElecYear;
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
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public String getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(String deformDate) {
		this.deformDate = deformDate;
	}
	public CandidateElectionVO getCandidateElectionVO() {
		return candidateElectionVO;
	}
	public void setCandidateElectionVO(CandidateElectionVO candidateElectionVO) {
		this.candidateElectionVO = candidateElectionVO;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public BigDecimal getAssets() {
		return assets;
	}
	public void setAssets(BigDecimal assets) {
		this.assets = assets;
	}
	public BigDecimal getLiabilities() {
		return liabilities;
	}
	public void setLiabilities(BigDecimal liabilities) {
		this.liabilities = liabilities;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMigrateCandidate() {
		return migrateCandidate;
	}
	public void setMigrateCandidate(String migrateCandidate) {
		this.migrateCandidate = migrateCandidate;
	}
	public String getCandDesignation() {
		return candDesignation;
	}
	public void setCandDesignation(String candDesignation) {
		this.candDesignation = candDesignation;
	}
	public List<CandidateDetailsForConstituencyTypesVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CandidateDetailsForConstituencyTypesVO> subList) {
		this.subList = subList;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	
}
