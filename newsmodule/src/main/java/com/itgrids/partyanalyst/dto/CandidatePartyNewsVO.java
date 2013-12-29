package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CandidatePartyNewsVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1228060069667052752L;
	private Long mediaId;
	private Long sourceCandidateId;
	private Long sourcePartyId;
	private Long benefitId;
	private List<CandidatePartyDestinationVO> sourceVOList;
	private List<CandidatePartyDestinationVO> destinationVOList;
	private List<Long> deletedFileSourceList;
	private List<Long> deltedFileDestinationList;	
	
	
	public List<Long> getDeletedFileSourceList() {
		return deletedFileSourceList;
	}

	public void setDeletedFileSourceList(List<Long> deletedFileSourceList) {
		this.deletedFileSourceList = deletedFileSourceList;
	}

	public List<Long> getDeltedFileDestinationLis() {
		return deltedFileDestinationList;
	}

	public void setDeltedFileDestinationLis(List<Long> deltedFileDestinationList) {
		this.deltedFileDestinationList = deltedFileDestinationList;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Long getSourceCandidateId() {
		return sourceCandidateId;
	}

	public void setSourceCandidateId(Long sourceCandidateId) {
		this.sourceCandidateId = sourceCandidateId;
	}

	public Long getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Long sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}


	public List<CandidatePartyDestinationVO> getDestinationVOList() {
		return destinationVOList;
	}

	public void setDestinationVOList(
			List<CandidatePartyDestinationVO> destinationVOList) {
		this.destinationVOList = destinationVOList;
	}

	public Long getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}

	public List<CandidatePartyDestinationVO> getSourceVOList() {
		return sourceVOList;
	}

	public void setSourceVOList(List<CandidatePartyDestinationVO> sourceVOList) {
		this.sourceVOList = sourceVOList;
	}
	
	

}
