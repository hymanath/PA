package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
	private Long candidatePartyFileId;
	private List<String> keywords;
	private List<CandidatePartyDestinationVO> destinationVOList;
	private List<CandidatePartyDestinationVO> sourceVOList;
	private List<CandidatePartyDestinationVO> fileSourceVOList;
	private List<CandidatePartyDestinationVO> fileDestinationVOList;
	private List<SelectOptionVO> canidateList;
	private List<SelectOptionVO> fileKeywordList;
	private List<SelectOptionVO> categoriesList;
		
	public List<CandidatePartyDestinationVO> getFileSourceVOList() {
		return fileSourceVOList;
	}
	public void setFileSourceVOList(
			List<CandidatePartyDestinationVO> fileSourceVOList) {
		this.fileSourceVOList = fileSourceVOList;
	}
	public List<CandidatePartyDestinationVO> getFileDestinationVOList() {
		return fileDestinationVOList;
	}
	public void setFileDestinationVOList(
			List<CandidatePartyDestinationVO> fileDestinationVOList) {
		this.fileDestinationVOList = fileDestinationVOList;
	}
	public Long getCandidatePartyFileId() {
		return candidatePartyFileId;
	}
	public void setCandidatePartyFileId(Long candidatePartyFileId) {
		this.candidatePartyFileId = candidatePartyFileId;
	}
	public List<SelectOptionVO> getFileKeywordList() {
		return fileKeywordList;
	}
	public void setFileKeywordList(List<SelectOptionVO> fileKeywordList) {
		this.fileKeywordList = fileKeywordList;
	}
	public List<SelectOptionVO> getCanidateList() {
		return canidateList;
	}
	public void setCanidateList(List<SelectOptionVO> canidateList) {
		this.canidateList = canidateList;
	}
	public List<CandidatePartyDestinationVO> getDestinationVOList() {
		return destinationVOList;
	}
	public void setDestinationVOList(
			List<CandidatePartyDestinationVO> destinationVOList) {
		this.destinationVOList = destinationVOList;
	}
	public List<CandidatePartyDestinationVO> getSourceVOList() {
		return sourceVOList;
	}
	public void setSourceVOList(List<CandidatePartyDestinationVO> sourceVOList) {
		this.sourceVOList = sourceVOList;
	}
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
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public List<SelectOptionVO> getCategoriesList() {
		return categoriesList;
	}
	public void setCategoriesList(List<SelectOptionVO> categoriesList) {
		this.categoriesList = categoriesList;
	}
	
	

}
