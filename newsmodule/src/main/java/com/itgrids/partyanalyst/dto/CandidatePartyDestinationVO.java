package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CandidatePartyDestinationVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1025786242837720491L;
	private Long partyId;
	private Long candidateId;
	private Long benefitId;
	private List<Long> categoryIds;
	private String keywordsList;
	private String categoryIdsStr;
	private Long mediaId;
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}
	
	public List<Long> getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	public String getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(String keywordsList) {
		this.keywordsList = keywordsList;
	}
	public String getCategoryIdsStr() {
		return categoryIdsStr;
	}
	public void setCategoryIdsStr(String categoryIdsStr) {
		this.categoryIdsStr = categoryIdsStr;
	}
	public Long getMediaId() {
		return mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	
	

}
