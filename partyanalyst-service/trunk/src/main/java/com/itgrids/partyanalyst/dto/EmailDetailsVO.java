package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class EmailDetailsVO implements Serializable{

	private static final long serialVersionUID = 2598121949467285613L;
	
	private String subject;
	private String toAddress;
	private String fromAddress;
	private String content;
	private String welcomeName;
	private String senderName;
	
	private String electionType;
	private String constituencyName;
	private String partyStrength;
	private String candidateName;
	
	private String recepientEmail;
	private String email;
	private String mobile;
	private Long id;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
