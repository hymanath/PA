package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CandidateMinistriesVO implements Serializable {

	private static final long serialVersionUID = -2426782207984592621L;
	
	private Long candidateId;
	private String candidateName;
	private Long partyId;
	private String partyName;
	private List<ElectionGoverningBodyVO> ministries;
	private Date fromDate;
	private Date toDate;
	private int noOfMinistries;
	
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getNoOfMinistries() {
		return noOfMinistries;
	}
	public void setNoOfMinistries(int noOfMinistries) {
		this.noOfMinistries = noOfMinistries;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
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
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<ElectionGoverningBodyVO> getMinistries() {
		return ministries;
	}
	public void setMinistries(List<ElectionGoverningBodyVO> ministries) {
		this.ministries = ministries;
	}
	
		
}
