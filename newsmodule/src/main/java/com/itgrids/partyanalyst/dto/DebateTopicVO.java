package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DebateTopicVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String party = "-";
	
	private Long candidateId;
	private String candidate= "-";
	
	private Long subjectId;
	private String subject= "-";
	
	private Double countScale;
	
	
	private List<DebateTopicVO> subList;

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

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<DebateTopicVO> getSubList() {
		return subList;
	}

	public void setSubList(List<DebateTopicVO> subList) {
		this.subList = subList;
	}

	public Double getCountScale() {
		return countScale;
	}

	public void setCountScale(Double countScale) {
		this.countScale = countScale;
	}

	
	
	
	
}
