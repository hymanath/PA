package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "candidate_details" )
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateDetails extends BaseModel implements Serializable
{
	
 private static final long serialVersionUID = 1L;
 private Long candidateDetailsId;
 private CasteState casteState;
 private EducationalQualifications educationalQualifications;
 private String mobileno;
 private Double howLongWorkingInParty;
 private Candidate candidate;
 
 public CandidateDetails()
 {
		super();
 }

public CandidateDetails(Long candidateDetailsId,String mobileno,Double howLongWorkingInParty)
{
		
		this.candidateDetailsId = candidateDetailsId;
		this.mobileno = mobileno;
		this.howLongWorkingInParty=howLongWorkingInParty;
}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "candidate_details_id")
public Long getCandidateDetailsId() {
	return candidateDetailsId;
}

public void setCandidateDetailsId(Long candidateDetailsId) {
	this.candidateDetailsId = candidateDetailsId;
}

@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name="caste_state_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public CasteState getCasteState() {
	return casteState;
}

public void setCasteState(CasteState casteState) {
	this.casteState = casteState;
}
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name="educational_qualification_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public EducationalQualifications getEducationalQualifications() {
	return educationalQualifications;
}

public void setEducationalQualifications(
		EducationalQualifications educationalQualifications) {
	this.educationalQualifications = educationalQualifications;
}
@Column(name="mobile_no")
public String getMobileno() {
	return mobileno;
}

public void setMobileno(String mobileno) {
	this.mobileno = mobileno;
}
@Column(name="how_long_working_in_party")
public Double getHowLongWorkingInParty() {
	return howLongWorkingInParty;
}

public void setHowLongWorkingInParty(Double howLongWorkingInParty) {
	this.howLongWorkingInParty = howLongWorkingInParty;
}
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name="candidate_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Candidate getCandidate() {
	return candidate;
}

public void setCandidate(Candidate candidate) {
	this.candidate = candidate;
}
 
}
