package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VerificationCompVO implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	private Long dcCasteId;
	private String dcCaste;
	private Long dvCasteId;
	private String dvCaste;
	private Long wmCasteId;
	private String wmCaste;
	private String dcName;
	private String dvName;
	private String wmName;
	
	private String voterCardNO;
	private Long voterId;
	private String houseNo;
	private String voterName;
	private String relativeName;
	private Integer totalCount = 0;
	private Integer matchedCount = 0;;
	private Integer unMatchedCount = 0;;
	private Integer notVerifiedCount = 0;;
	private Integer verifiedCount;
	
	private Integer collectedCount;
	private Integer updatedCount;
	private Integer verifieCount;
	private Integer notIdentifedCount;
	
	private String partNo;
	private Long surveyUserType;
	private String date;
	private String surveyUser;
	private String panchayatName;
	private String verifierStatus ;
	private List<VerificationCompVO> matchedList;
	private List<VerificationCompVO> unMatchedList;
	private List<VerificationCompVO> notVerifiedList;

	public Long getDcCasteId() {
		return dcCasteId;
	}

	public void setDcCasteId(Long dcCasteId) {
		this.dcCasteId = dcCasteId;
	}

	public String getDcCaste() {
		return dcCaste;
	}

	public void setDcCaste(String dcCaste) {
		this.dcCaste = dcCaste;
	}

	public Long getDvCasteId() {
		return dvCasteId;
	}

	public void setDvCasteId(Long dvCasteId) {
		this.dvCasteId = dvCasteId;
	}

	public String getDvCaste() {
		return dvCaste;
	}

	public void setDvCaste(String dvCaste) {
		this.dvCaste = dvCaste;
	}

	public Long getWmCasteId() {
		return wmCasteId;
	}

	public void setWmCasteId(Long wmCasteId) {
		this.wmCasteId = wmCasteId;
	}

	public String getWmCaste() {
		return wmCaste;
	}

	public void setWmCaste(String wmCaste) {
		this.wmCaste = wmCaste;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public String getDvName() {
		return dvName;
	}

	public void setDvName(String dvName) {
		this.dvName = dvName;
	}

	public String getWmName() {
		return wmName;
	}

	public void setWmName(String wmName) {
		this.wmName = wmName;
	}

	public String getVoterCardNO() {
		return voterCardNO;
	}

	public void setVoterCardNO(String voterCardNO) {
		this.voterCardNO = voterCardNO;
	}

	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public Long getSurveyUserType() {
		return surveyUserType;
	}

	public void setSurveyUserType(Long surveyUserType) {
		this.surveyUserType = surveyUserType;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getMatchedCount() {
		return matchedCount;
	}

	public void setMatchedCount(Integer matchedCount) {
		this.matchedCount = matchedCount;
	}

	public Integer getUnMatchedCount() {
		return unMatchedCount;
	}

	public void setUnMatchedCount(Integer unMatchedCount) {
		this.unMatchedCount = unMatchedCount;
	}

	public List<VerificationCompVO> getMatchedList() {
		return matchedList;
	}

	public void setMatchedList(List<VerificationCompVO> matchedList) {
		this.matchedList = matchedList;
	}

	public List<VerificationCompVO> getUnMatchedList() {
		return unMatchedList;
	}

	public void setUnMatchedList(List<VerificationCompVO> unMatchedList) {
		this.unMatchedList = unMatchedList;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(String surveyUser) {
		this.surveyUser = surveyUser;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public List<VerificationCompVO> getNotVerifiedList() {
		return notVerifiedList;
	}

	public void setNotVerifiedList(List<VerificationCompVO> notVerifiedList) {
		this.notVerifiedList = notVerifiedList;
	}

	public Integer getNotVerifiedCount() {
		return notVerifiedCount;
	}

	public void setNotVerifiedCount(Integer notVerifiedCount) {
		this.notVerifiedCount = notVerifiedCount;
	}

	public Integer getVerifiedCount() {
		return verifiedCount;
	}

	public void setVerifiedCount(Integer verifiedCount) {
		this.verifiedCount = verifiedCount;
	}

	public String getVerifierStatus() {
		return verifierStatus;
	}

	public void setVerifierStatus(String verifierStatus) {
		this.verifierStatus = verifierStatus;
	}

	public Integer getCollectedCount() {
		return collectedCount;
	}

	public void setCollectedCount(Integer collectedCount) {
		this.collectedCount = collectedCount;
	}

	public Integer getUpdatedCount() {
		return updatedCount;
	}

	public void setUpdatedCount(Integer updatedCount) {
		this.updatedCount = updatedCount;
	}

	public Integer getVerifieCount() {
		return verifieCount;
	}

	public void setVerifieCount(Integer verifieCount) {
		this.verifieCount = verifieCount;
	}

	public Integer getNotIdentifedCount() {
		return notIdentifedCount;
	}

	public void setNotIdentifedCount(Integer notIdentifedCount) {
		this.notIdentifedCount = notIdentifedCount;
	}
	
	
	
	
}
