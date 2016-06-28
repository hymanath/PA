package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DebateAnalysisVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 690325422494821536L;
	
	private Long debateId;
	
	private Long candidateId;
	private String candidate;
	
	private Long partyId;
	private String partyName;
	
	private Long totalDebates;
	
	private Double performanceCount;
	private Double presentation;
	private Double counterAttack;
	private Double bodyLanguage;
	private Double subject;
	
	private Double count;	
	
	private String perc;
	
	
	public Long getDebateId() {
		return debateId;
	}
	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getTotalDebates() {
		return totalDebates;
	}
	public void setTotalDebates(Long totalDebates) {
		this.totalDebates = totalDebates;
	}
	public Double getPerformanceCount() {
		return performanceCount;
	}
	public void setPerformanceCount(Double performanceCount) {
		this.performanceCount = performanceCount;
	}
	public Double getPresentation() {
		return presentation;
	}
	public void setPresentation(Double presentation) {
		this.presentation = presentation;
	}
	public Double getCounterAttack() {
		return counterAttack;
	}
	public void setCounterAttack(Double counterAttack) {
		this.counterAttack = counterAttack;
	}
	public Double getBodyLanguage() {
		return bodyLanguage;
	}
	public void setBodyLanguage(Double bodyLanguage) {
		this.bodyLanguage = bodyLanguage;
	}
	public Double getSubject() {
		return subject;
	}
	public void setSubject(Double subject) {
		this.subject = subject;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidate() {
		return candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	
	
	
	
	

}
