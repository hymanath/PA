package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class EmailDetailsVO implements Serializable{

	private static final long serialVersionUID = 2598121949467285613L;
	
	private String subject;
	private String toAddress;
	private String fromAddress;
	private String content;
	private String host;
	private String welcomeName;
	private String senderName;
	
	private String electionType;
	private String constituencyName;
	private String partyStrength;
	private String candidateName;
	
	private String recepientEmail;
	
	
	
	public String getRecepientEmail() {
		return recepientEmail;
	}
	public void setRecepientEmail(String recepientEmail) {
		this.recepientEmail = recepientEmail;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getPartyStrength() {
		return partyStrength;
	}
	public void setPartyStrength(String partyStrength) {
		this.partyStrength = partyStrength;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWelcomeName() {
		return welcomeName;
	}
	public void setWelcomeName(String welcomeName) {
		this.welcomeName = welcomeName;
	}
	
}
