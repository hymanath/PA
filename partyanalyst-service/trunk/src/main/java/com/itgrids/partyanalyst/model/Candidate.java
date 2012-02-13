/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

/**
 * Candidate entity. 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */
@Entity
@Table(name = "candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Candidate extends BaseModel implements java.io.Serializable {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = 1164868684649963786L;
	
	// Fields
	
	private Long candidateId;
	private String firstname;
	private String middlename;
	private String lastname;
	private Date dateofbirth;
	private String emailAddress;
	private String phone;
	private String mobile;
	private String address;
	private String education;
	private String gender;
	private String image;
	private Set<Nomination> nominations = new HashSet<Nomination>(0);
	private Set<UserCandidateRelation> userCandidateRelations = new HashSet<UserCandidateRelation>(0);
	private Set<Gallary> gallaries = new HashSet<Gallary>(0);
	private Set<CandidateProfileDescription> candidateProfileDescription = new HashSet<CandidateProfileDescription>();
	private Set<CandidateUpdatesEmail> candidateUpdatesEmails = new HashSet<CandidateUpdatesEmail>(0);
	private Set<ElectionMinisters> electionMinisters = new HashSet<ElectionMinisters>(0);
	
	// Constructors

	/** default constructor */
	public Candidate() {
	}

	/** minimal constructor */
	public Candidate(Long candidateId) {
		this.candidateId = candidateId;
	}

	/** full constructor */
	public Candidate(Long candidateId, String firstname, String middlename,
			String lastname, Date dateofbirth, String emailAddress,
			String phone, String mobile, String address, String education,
			String gender, String image,Set<Nomination> nominations) {
		this.candidateId = candidateId;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.emailAddress = emailAddress;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.education = education;
		this.gender = gender;
		this.nominations = nominations;
		this.image = image;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_id", unique = true, nullable = false)
	public Long getCandidateId() {
		return this.candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	@Column(name = "firstname", length = 40)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "middlename", length = 40)
	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@Column(name = "lastname", length = 40)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Column(name = "email_address", length = 60)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "phone", length = 25)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "address", length = 25)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "education", length = 25)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "gender", length = 25)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	public Set<Nomination> getNominations() {
		return this.nominations;
	}

	public void setNominations(Set<Nomination> nominations) {
		this.nominations = nominations;
	}

	@Column(name = "image", length = 50)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserCandidateRelation> getUserCandidateRelations() {
		return userCandidateRelations;
	}

	public void setUserCandidateRelations(
			Set<UserCandidateRelation> userCandidateRelations) {
		this.userCandidateRelations = userCandidateRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Gallary> getGallaries() {
		return gallaries;
	}

	public void setGallaries(Set<Gallary> gallaries) {
		this.gallaries = gallaries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateProfileDescription> getCandidateProfileDescription() {
		return candidateProfileDescription;
	}

	public void setCandidateProfileDescription(
			Set<CandidateProfileDescription> candidateProfileDescription) {
		this.candidateProfileDescription = candidateProfileDescription;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<CandidateUpdatesEmail> getCandidateUpdatesEmails() {
		return candidateUpdatesEmails;
	}

	public void setCandidateUpdatesEmails(
			Set<CandidateUpdatesEmail> candidateUpdatesEmails) {
		this.candidateUpdatesEmails = candidateUpdatesEmails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidate")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<ElectionMinisters> getElectionMinisters() {
		return electionMinisters;
	}

	public void setElectionMinisters(Set<ElectionMinisters> electionMinisters) {
		this.electionMinisters = electionMinisters;
	}

	
}