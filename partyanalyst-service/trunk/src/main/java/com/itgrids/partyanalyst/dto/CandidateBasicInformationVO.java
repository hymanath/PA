/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 04, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/*
 * @author Sai Krishna Basetti
 */
public class CandidateBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** ID of Candidate*/
	private Long candidateId;
	/** First Name of Candidate*/
	private String candidateFirstName;
	/** Candidate Last Name*/
	private String candidateLastName;
	/** Candidate Middle Name*/
	private String candidateMiddleName;
	/** Image path of Candidate*/
	private String candidateImage;
	/** Gender of Candidate*/
	private String candidateGender;
	/** Address of the Candidate*/
	private String candidateAddress;
	/**Candidate Phone Number */
	private String candidatePhone;
	
	/** Getters And Setters */
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateFirstName() {
		return candidateFirstName;
	}
	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}
	public String getCandidateLastName() {
		return candidateLastName;
	}
	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}
	public String getCandidateMiddleName() {
		return candidateMiddleName;
	}
	public void setCandidateMiddleName(String candidateMiddleName) {
		this.candidateMiddleName = candidateMiddleName;
	}
	public String getCandidateImage() {
		return candidateImage;
	}
	public void setCandidateImage(String candidateImage) {
		this.candidateImage = candidateImage;
	}
	public String getCandidateGender() {
		return candidateGender;
	}
	public void setCandidateGender(String candidateGender) {
		this.candidateGender = candidateGender;
	}
	public String getCandidateAddress() {
		return candidateAddress;
	}
	public void setCandidateAddress(String candidateAddress) {
		this.candidateAddress = candidateAddress;
	}
	public String getCandidatePhone() {
		return candidatePhone;
	}
	public void setCandidatePhone(String candidatePhone) {
		this.candidatePhone = candidatePhone;
	}

}
